package com.baltic.road.path.app.dao.impl

import com.baltic.road.path.app.dao.BookDao
import com.baltic.road.path.app.dto.BookDto
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.Predicate

class InMemoryBookDao: BookDao {

    var bookList = mutableListOf<BookDto>()

    var counter = AtomicInteger()

    override fun listAll(): List<BookDto> = bookList

    override fun create(book: BookDto): BookDto {
        val id = counter.getAndIncrement()
        book.id = id.toString()
        bookList.add(book)
        return book
    }

    override fun update(book: BookDto): BookDto {
        TODO("Not yet implemented")
    }

    override fun getById(id: String): BookDto? {
        return bookList.find { it.id == id }
    }

    override fun delete(id: String) {
        bookList.removeIf(Predicate { it.id == id })
    }
}
