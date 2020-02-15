package com.kullapat.iam.usecase.user

import com.kullapat.iam.usecase.storage.UserStorage
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList

class CreateUserTest: BehaviorSpec({

    val userStorage = mockk<UserStorage>()
    val useCase = CreateUser(userStorage)

    given("create a user with invalid input") {
        `when`("username is empty") {
            val input = CreateUserInput(username = "", email = "nitch001@mail.com", firstName = "nitch", enabled = true)
            then("should throw invalid input exception") {
                val exception = shouldThrow<Exception> { useCase.execute(input) }
                exception.message shouldBe "username cannot be empty"
            }
        }

        `when`("email is empty") {
            val input = CreateUserInput(username = "nitch001", email = "", firstName = "nitch", enabled = true)
            then("should throw invalid input exception") {
                val exception = shouldThrow<Exception> { useCase.execute(input) }
                exception.message shouldBe "email cannot be empty"
            }
        }
    }

    given("create a user") {
        val input = CreateUserInput(username = "nitch001", email = "nitch001@mail.com", firstName = "nitch", enabled = true)
        then("should save and return a user") {
            val expectedUser = input.toUser()
            every { userStorage.save(expectedUser) } returns flowOf(expectedUser)

            val actual = useCase.execute(input)
            verify(exactly = 1) { userStorage.save(expectedUser) }
            actual.toList()[0] shouldBe expectedUser
        }
    }
})