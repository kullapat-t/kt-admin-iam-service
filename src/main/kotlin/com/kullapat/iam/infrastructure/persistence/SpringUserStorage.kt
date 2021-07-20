package com.kullapat.iam.infrastructure.persistence

import com.kullapat.iam.domain.User
import com.kullapat.iam.usecase.storage.UserStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.stereotype.Component

@Component
class SpringUserStorage(private val userRepository: UserRepository): UserStorage {
    override suspend fun save(user: User): User {
        return userRepository.save(user).awaitFirst()
    }

    override fun findAll(): Flow<User> {
        return userRepository.findAll().asFlow()
    }
}