package com.kullapat.iam.infrastructure.http

import com.kullapat.iam.domain.User
import com.kullapat.iam.usecase.storage.UserStorage
import com.kullapat.iam.usecase.user.CreateUserInput
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @Autowired
    private lateinit var client: WebTestClient

    @Test
    fun `create user, should return a new user`() {
        val createUserInput = CreateUserInput(username = "nitch", email = "nitch@mail.com", firstName = "kt", enabled = true)
        val result = client.post()
                .uri("/api/v1/users")
                .body(BodyInserters.fromValue(createUserInput))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk
                .expectBodyList(User::class.java)
                .returnResult()
                .responseBody!!

        result.size shouldBe 1
        result[0].username shouldBe createUserInput.username
        result[0].email shouldBe createUserInput.email
        result[0].firstName shouldBe createUserInput.firstName
        result[0].enabled shouldBe createUserInput.enabled
    }
}