package com.baltic.road.path.app.config

import com.baltic.road.path.app.dao.SessionDao
import com.baltic.road.path.app.dao.UserDao
import com.baltic.road.path.app.dao.impl.InMemorySessionDao
import com.baltic.road.path.app.service.AuthService
import com.baltic.road.path.app.service.CredentialService
import com.baltic.road.path.app.service.impl.CredentialServiceImpl
import com.baltic.road.path.app.service.impl.SimpleAuthServiceImpl
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AuthConfig {

    @Value("\${secret}")
    lateinit var secret: String

    @Bean
    fun authService(userDao: UserDao, sessionDao: SessionDao): AuthService =
            SimpleAuthServiceImpl(userDao, sessionDao, secret)

    @Bean
    fun sessionDao(): SessionDao = InMemorySessionDao()

    @Bean
    fun credentialService(sessionDao: SessionDao): CredentialService = CredentialServiceImpl(sessionDao)
}