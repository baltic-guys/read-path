package com.baltic.road.path.app.dao.impl

import com.baltic.road.path.app.dao.UserDao
import com.baltic.road.path.app.dto.UserDto
import org.springframework.util.Base64Utils
import java.util.function.Predicate

class InMemoryUserDao : UserDao {

    var userList = mutableListOf<UserDto>()

    override fun getUser(user: UserDto): UserDto? {
        return userList.find { userDto -> (userDto.username == user.username)
                .and(userDto.password == user.password) }
    }

    override fun addUser(user: UserDto): UserDto {
        user.id = Base64Utils.encodeToString("${user.username}:${user.password}".toByteArray())
        userList.add(user)
        return user
    }

    override fun deleteUser(user: UserDto): Boolean {
        return try {
            userList.removeIf(Predicate {
                (it.username == user.username)
                        .and(it.password == user.password)
                        .and(it.id == user.id)
            })
            true
        } catch (e: Exception) {
            false
        }
    }


}