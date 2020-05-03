package com.baltic.road.path.app.dao

import com.baltic.road.path.app.dto.BookDto

interface BookDao {
    fun listAll(): List<BookDto>

    fun create(book: BookDto): BookDto

    fun update(book: BookDto): BookDto

    fun getById(id: String): BookDto?

    fun delete(id: String)
}
