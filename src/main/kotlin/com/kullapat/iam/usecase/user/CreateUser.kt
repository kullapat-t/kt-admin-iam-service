package com.kullapat.iam.usecase.user

import com.kullapat.iam.domain.User
import com.kullapat.iam.usecase.storage.UserStorage
import kotlinx.coroutines.coroutineScope

class CreateUser(private val userStorage: UserStorage) {

    suspend fun execute(input: CreateUserInput): User = coroutineScope {
        require(input.username.isNotEmpty()) { "username cannot be empty" }
        require(input.email.isNotEmpty()) { "email cannot be empty" }

        return@coroutineScope userStorage.save(input.toUser())
    }
}
