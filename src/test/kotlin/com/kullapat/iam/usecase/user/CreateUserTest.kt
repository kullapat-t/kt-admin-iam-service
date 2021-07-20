package com.kullapat.iam.usecase.user

import com.kullapat.iam.domain.User
import com.kullapat.iam.usecase.storage.UserStorage
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.BehaviorSpec
import io.mockk.*
import kotlinx.coroutines.runBlocking

class CreateUserTest: BehaviorSpec({

    val userStorage = mockk<UserStorage>()
    val useCase = CreateUser(userStorage)

    given("Create user input") {
        var input = CreateUserInput(username = "nitch", email = "nitch001@mail.com", firstName = "nitch", enabled = true)

        `when`("create a user") {
            then("should save and return a user") {
                runBlocking {
                    val expectedUser = input.toUser()
                    val userSlot = slot<User>()
                    coEvery { userStorage.save(any()) } answers { firstArg() }

                    useCase.execute(input)

                    coVerify(exactly = 1) { userStorage.save(capture(userSlot)) }
                    userSlot.captured shouldBe expectedUser
                }
            }
        }

        `when`("username is empty") {
            val input = input.copy(username = "")
            then("should throw invalid input exception") {
                val exception = shouldThrow<Exception> {
                    runBlocking { useCase.execute(input) }
                }
                exception.message shouldBe "username cannot be empty"
            }
        }

        `when`("email is empty") {
            val input = input.copy(email = "")
            then("should throw invalid input exception") {
                val exception = shouldThrow<Exception> {
                    runBlocking { useCase.execute(input) }
                }
                exception.message shouldBe "email cannot be empty"
            }
        }
    }
})