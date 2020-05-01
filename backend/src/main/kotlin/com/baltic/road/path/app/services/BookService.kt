package com.baltic.road.path.app.services

interface BookService {

    fun listAll(): List<Nothing>

    fun create(book: Nothing): Nothing

    fun update(book: Nothing): Nothing

    fun getById(id: String): Nothing

    fun delete(id: String)
}