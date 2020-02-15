package com.kullapat.iam.usecase.storage

import com.kullapat.iam.domain.User
import kotlinx.coroutines.flow.Flow

interface UserStorage {
    fun save(user: User): Flow<User>
}
