package com.kullapat.iam.infrastructure.persistence

import com.kullapat.iam.domain.User
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface UserRepository: ReactiveMongoRepository<User, String>