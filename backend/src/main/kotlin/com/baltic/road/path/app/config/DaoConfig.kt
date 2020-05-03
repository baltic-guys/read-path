package com.baltic.road.path.app.config

import com.baltic.road.path.app.dao.BookDao
import com.baltic.road.path.app.dao.impl.InMemoryBookDao
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DaoConfig {

    @Bean
    fun bookDao(): BookDao = InMemoryBookDao()
}