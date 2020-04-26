package com.baltic.road.path.app.service

import com.baltic.road.path.app.dto.UserDto

interface AuthService {

    fun login(user: UserDto): String?

    fun registration(user: UserDto): String?
}