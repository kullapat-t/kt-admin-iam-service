package com.kullapat.iam.usecase.storage

import com.kullapat.iam.domain.User
import kotlinx.coroutines.flow.Flow

interface UserStorage {
    suspend fun save(user: User): User
    fun findAll(): Flow<User>
}
