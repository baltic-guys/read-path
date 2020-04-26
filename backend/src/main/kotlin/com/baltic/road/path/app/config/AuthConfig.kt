package com.baltic.road.path.app.config

import com.baltic.road.path.app.dao.UserDao
import com.baltic.road.path.app.service.AuthService
import com.baltic.road.path.app.service.impl.SimpleAuthServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AuthConfig {

    @Bean
    fun authService(userDao: UserDao): AuthService = SimpleAuthServiceImpl(userDao)
}