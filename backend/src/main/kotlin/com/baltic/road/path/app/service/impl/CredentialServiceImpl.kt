package com.baltic.road.path.app.service.impl

import com.baltic.road.path.app.dao.SessionDao
import com.baltic.road.path.app.service.CredentialService

class CredentialServiceImpl(val sessionDao: SessionDao) : CredentialService {

    override fun exist(token: String): Boolean = sessionDao.exist(token)

    override fun checkCredentional(token: String?): Boolean {
        if (token != null && exist(token)) {
            return true
        }
        return false
    }
}
