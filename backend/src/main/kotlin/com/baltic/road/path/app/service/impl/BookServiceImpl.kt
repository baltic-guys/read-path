package com.baltic.road.path.app.service.impl

import com.baltic.road.path.app.dao.BookDao
import com.baltic.road.path.app.dto.BookDto
import com.baltic.road.path.app.service.BookService
import org.springframework.stereotype.Service

@Service
class BookServiceImpl(val bookDao: BookDao) : BookService {

    override fun listAll(): List<BookDto> = bookDao.listAll()

    override fun create(book: BookDto): BookDto = bookDao.create(book)

    override fun update(book: BookDto): BookDto = bookDao.update(book)

    override fun getById(id: String): BookDto? = bookDao.getById(id)

    override fun delete(id: String) = bookDao.delete(id)
}
