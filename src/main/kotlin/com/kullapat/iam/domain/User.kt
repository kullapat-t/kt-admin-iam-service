package com.kullapat.iam.domain

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
data class User(
    val username: String,
    val email: String,
    val firstName: String,
    val enabled: Boolean
)