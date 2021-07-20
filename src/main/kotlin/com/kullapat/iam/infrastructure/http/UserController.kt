package com.kullapat.iam.infrastructure.http

import com.kullapat.iam.usecase.user.CreateUserInput
import com.kullapat.iam.domain.User
import com.kullapat.iam.usecase.storage.UserStorage
import com.kullapat.iam.usecase.user.CreateUser
import com.kullapat.iam.usecase.user.GetAllUsers
import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/users")
class UserController(userStorage: UserStorage) {

    val createUserCommand: CreateUser = CreateUser(userStorage)
    val getAllUsersQuery: GetAllUsers = GetAllUsers(userStorage)

    @PostMapping
    suspend fun createUser(@RequestBody input: CreateUserInput): User {
        return createUserCommand.execute(input)
    }

    @GetMapping
    fun getAllUsers(): Flow<User> {
        return getAllUsersQuery.execute()
    }
}