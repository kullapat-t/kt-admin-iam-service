package com.kullapat.iam.infrastructure.persistence

import com.kullapat.iam.domain.User
import com.kullapat.iam.usecase.storage.UserStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import org.springframework.stereotype.Component

@Component
class SpringUserStorage(private val userRepository: UserRepository): UserStorage {
    override fun save(user: User): Flow<User> {
        return userRepository.save(user).asFlow()
    }
}