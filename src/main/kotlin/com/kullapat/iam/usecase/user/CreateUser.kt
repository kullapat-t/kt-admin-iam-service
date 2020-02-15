package com.kullapat.iam.usecase.user

import com.kullapat.iam.domain.User
import com.kullapat.iam.usecase.storage.UserStorage
import kotlinx.coroutines.flow.Flow

class CreateUser(private val userStorage: UserStorage) {

    fun execute(input: CreateUserInput): Flow<User> {
        require(input.username.isNotEmpty()) { "username cannot be empty" }
        require(input.email.isNotEmpty()) { "email cannot be empty" }
        return userStorage.save(input.toUser())
    }
}