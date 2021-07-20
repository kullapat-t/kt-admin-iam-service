package com.kullapat.iam.domain

data class User(
    val username: String,
    val email: String,
    val firstName: String,
    val enabled: Boolean
)