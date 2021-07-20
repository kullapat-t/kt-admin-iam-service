package com.kullapat.iam.infrastructure.http

import com.kullapat.iam.builder.UserBuilder
import com.kullapat.iam.domain.User
import com.kullapat.iam.usecase.storage.UserStorage
import com.kullapat.iam.usecase.user.CreateUserInput
import io.kotlintest.shouldBe
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.hasItems
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import org.springframework.web.reactive.function.BodyInserters

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest(
    @Autowired val client: WebTestClient,
    @Autowired val userStorage: UserStorage
) {

    @Test
    fun `create user, should return a new user`() {
        val createUserInput = CreateUserInput(username = "nitch", email = "nitch@mail.com", firstName = "kt", enabled = true)
        val result = client.post()
                .uri("/api/v1/users")
                .body(BodyInserters.fromValue(createUserInput))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk
                .expectBody<User>()
                .returnResult()
                .responseBody!!

        result.username shouldBe createUserInput.username
        result.email shouldBe createUserInput.email
        result.firstName shouldBe createUserInput.firstName
        result.enabled shouldBe createUserInput.enabled
    }

    @Test
    fun `get all users, should return all users`() {
        val jon = UserBuilder(username = "jon", email = "jon@mail.com", firstName = "jOn", enabled = true).build()
        val tom = UserBuilder(username = "tom", email = "tom@mail.com", firstName = "TOm", enabled = false).build()
        runBlocking {
            userStorage.save(jon)
            userStorage.save(tom)
        }

        client.get()
            .uri("/api/v1/users")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.length()").isEqualTo(2)
            .jsonPath("$..username").value(hasItems(jon.username, tom.username))
            .jsonPath("$..email").value(hasItems(jon.email, tom.email))
            .jsonPath("$..firstName").value(hasItems(jon.firstName, tom.firstName))
            .jsonPath("$..enabled").value(hasItems(jon.enabled, tom.enabled))
    }
}