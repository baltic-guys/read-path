package com.baltic.road.path.app.config

import com.baltic.road.path.app.dao.UserDao
import com.baltic.road.path.app.dao.impl.InMemoryUserDao
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DaoConfig {

    @Bean
    fun userDao(): UserDao = InMemoryUserDao()
}