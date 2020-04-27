package com.baltic.road.path.app.service

interface CredentialService {
    fun exist(token: String): Boolean

    fun checkCredentional(token: String?): Boolean
}