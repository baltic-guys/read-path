package com.baltic.road.path.app.service.impl

import com.baltic.road.path.app.dao.SessionDao
import com.baltic.road.path.app.dao.UserDao
import com.baltic.road.path.app.dto.AuthDto
import com.baltic.road.path.app.dto.SessionDto
import com.baltic.road.path.app.dto.UserDto
import com.baltic.road.path.app.service.AuthService
import org.springframework.util.Base64Utils

class SimpleAuthServiceImpl(
    val userDao: UserDao,
    val sessionDao: SessionDao,
    val secret: String
) : AuthService {

    override fun login(user: UserDto): AuthDto? {
        if (userDao.getUser(user) != null) {
            val token = getBasicHeader(arrayOf(user.username, user.password))
            val secretKey = getBasicHeader(arrayOf(secret))
            sessionDao.addSession(SessionDto(token, true))
            return AuthDto(token, secretKey)
        }
        return null
    }

    override fun exist(user: UserDto): UserDto? = userDao.exist(user)

    override fun registration(user: UserDto): AuthDto? = login(userDao.addUser(user))

    private fun getBasicHeader(str: Array<String>): String {
        val result = str.joinToString(separator = ":")
        return Base64Utils.encodeToString(result.toByteArray())
    }
}
