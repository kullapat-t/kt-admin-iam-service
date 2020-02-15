package com.kullapat.iam.infrastructure.http

import com.kullapat.iam.usecase.user.CreateUserInput
import com.kullapat.iam.domain.User
import com.kullapat.iam.usecase.storage.UserStorage
import com.kullapat.iam.usecase.user.CreateUser
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController(userStorage: UserStorage) {

    val createUser: CreateUser = CreateUser(userStorage)

    @PostMapping
    fun createUser(@RequestBody input: CreateUserInput): Flow<User> {
        return createUser.execute(input)
    }
}