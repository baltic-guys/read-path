package com.baltic.road.path.app.service

import com.baltic.road.path.app.dto.AuthDto
import com.baltic.road.path.app.dto.UserDto

interface AuthService {

    fun login(user: UserDto): AuthDto?

    fun registration(user: UserDto): AuthDto?
}