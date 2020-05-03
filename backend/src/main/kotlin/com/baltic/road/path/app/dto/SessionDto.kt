package com.baltic.road.path.app.dto

data class SessionDto(
        val token: String,
        var active: Boolean = false
)