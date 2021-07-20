package com.kullapat.iam.usecase.user

import com.kullapat.iam.builder.UserBuilder
import com.kullapat.iam.usecase.storage.UserStorage
import io.kotlintest.matchers.collections.shouldContainExactlyInAnyOrder
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class GetAllUsersTest {
    val userStorage = mockk<UserStorage>()
    val getAllUsersQuery = GetAllUsers(userStorage)

    @Nested
    inner class GivenUsersInPlatform {
        val jon = UserBuilder(username = "jon").build()
        val tom = UserBuilder(username = "tom").build()

        @Nested
        inner class WhenGetAllUsers {

            @BeforeEach
            fun beforeEach() {
                coEvery { userStorage.findAll() } returns flowOf(jon, tom)
            }

            @Test
            fun thenAllUsersShouldBeReturned() {
                runBlocking {
                    val s = getAllUsersQuery.execute()
                    s.toList() shouldContainExactlyInAnyOrder listOf(jon, tom)
                }
            }
        }
    }
}