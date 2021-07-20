package com.kullapat.iam.usecase.user

import com.kullapat.iam.domain.User

data class CreateUserInput(
    val username: String,
    val email: String,
    val firstName: String,
    val enabled: Boolean
) {
    fun toUser(): User {
        return User(username, email, firstName, enabled)
    }
}
