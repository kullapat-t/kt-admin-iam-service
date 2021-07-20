package com.kullapat.iam.builder

import com.kullapat.iam.domain.User

class UserBuilder(
    private var username: String = "jon",
    private var email: String = "jon",
    private var firstName: String = "jon",
    private var enabled: Boolean = true
) {
    fun build(): User {
        return User(
            username = username,
            email = email,
            firstName = firstName,
            enabled = enabled
        )
    }
}