package com.baltic.road.path.app.dto

data class AuthDto(
        val token: String,
        val expire: Long = 36000
)