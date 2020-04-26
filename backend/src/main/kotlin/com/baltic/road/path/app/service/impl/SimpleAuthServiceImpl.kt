package com.baltic.road.path.app.service.impl

import com.baltic.road.path.app.dao.UserDao
import com.baltic.road.path.app.dto.UserDto
import com.baltic.road.path.app.service.AuthService
import org.springframework.util.Base64Utils

class SimpleAuthServiceImpl(val userDao: UserDao): AuthService {

    override fun login(user: UserDto): String? {
        if (userDao.getUser(user) != null) {
            return getBasicHeader(user.username, user.password)
        }
        return null
    }

    override fun registration(user: UserDto): String? = login(userDao.addUser(user))

    private fun getBasicHeader(userName: String, password: String) =
            Base64Utils.encodeToString("$userName:$password".toByteArray())
}