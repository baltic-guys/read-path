package com.baltic.road.path.app.dto

data class AuthDto(
        val token: String,
        val sk: String,
        val expire: Long = 36000
)