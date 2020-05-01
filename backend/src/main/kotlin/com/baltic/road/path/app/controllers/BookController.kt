package com.baltic.road.path.app.controllers

import com.baltic.road.path.app.services.BookService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/book")
class BookController(private val bookService: BookService) {

    @GetMapping("/list")
    fun listBooks() = ResponseEntity.ok().body(bookService.listAll())

    @PostMapping("/add")
    fun addBook(book: Nothing) = ResponseEntity.ok().body(bookService.create(book))

    @PostMapping("/update")
    fun updateBook(book: Nothing) = ResponseEntity.ok().body(bookService.update(book))

    @GetMapping("/{id}")
    fun getBook(@PathVariable("id") id: String) = ResponseEntity.ok().body(bookService.getById(id))

    @GetMapping("/delete/{id}")
    fun deleteBook(@PathVariable("id") id: String) = ResponseEntity.ok().body(bookService.delete(id))
}