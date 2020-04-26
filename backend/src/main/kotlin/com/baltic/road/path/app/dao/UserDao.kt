package com.baltic.road.path.app.dao

import com.baltic.road.path.app.dto.UserDto

interface UserDao {

    fun getUser(user: UserDto): UserDto?

    fun addUser(user: UserDto): UserDto

    fun deleteUser(user: UserDto): Boolean
}