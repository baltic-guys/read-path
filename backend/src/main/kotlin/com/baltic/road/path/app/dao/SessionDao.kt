package com.baltic.road.path.app.dao

import com.baltic.road.path.app.dto.SessionDto

interface SessionDao {
    fun addSession(sessionDto: SessionDto)

    fun alive(sessionDto: SessionDto): Boolean

    fun activateSession(sessionDto: SessionDto)

    fun deactivateSession(sessionDto: SessionDto)

    fun exist(token: String): Boolean
}
