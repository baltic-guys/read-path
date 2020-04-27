package com.baltic.road.path.app.dao.impl

import com.baltic.road.path.app.dao.SessionDao
import com.baltic.road.path.app.dto.SessionDto
import java.util.function.Consumer

class InMemorySessionDao: SessionDao {

    var sessionList = mutableListOf<SessionDto>()

    override fun addSession(sessionDto: SessionDto) {
        sessionDto.active = true
        sessionList.add(sessionDto)
    }

    override fun alive(sessionDto: SessionDto): Boolean {
        val session = sessionList.find { session -> session.token == sessionDto.token } ?: return false
        return session.active
    }

    override fun activateSession(sessionDto: SessionDto) {
        sessionList.forEach(Consumer {
            if (it.token == sessionDto.token) {
                it.active = true
            }
        })
    }

    override fun deactivateSession(sessionDto: SessionDto) {
        sessionList.forEach(Consumer {
            if (it.token == sessionDto.token) {
                it.active = false
            }
        })
    }

    override fun exist(token: String): Boolean {
        sessionList.find { session -> session.token == token } ?: return false
        return true
    }
}