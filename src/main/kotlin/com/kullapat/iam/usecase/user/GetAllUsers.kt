package com.kullapat.iam.usecase.user

import com.kullapat.iam.domain.User
import com.kullapat.iam.usecase.storage.UserStorage
import kotlinx.coroutines.flow.Flow

class GetAllUsers(private val userStorage: UserStorage) {

    fun execute(): Flow<User>  {
        return userStorage.findAll()
    }
}