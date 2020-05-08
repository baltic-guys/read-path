package com.baltic.road.path.app.gr.service

import com.baltic.road.path.app.gr.client.SimpleGRClient
import com.baltic.road.path.app.gr.client.SimpleGRClient.SearchType.ALL
import com.baltic.road.path.app.gr.model.api.GoodreadsResponse
import com.baltic.road.path.app.gr.model.api.Work
import com.baltic.road.path.app.model.Book
import com.baltic.road.path.app.model.BookSearchResponse
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

// TODO(interface for other datagate)
class GRService {
    private val grApiKey = "qF3rQniJYU52xvIHtbYFA"
    private val mapper = XmlMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setPropertyNamingStrategy(PropertyNamingStrategy.KEBAB_CASE)
            .registerKotlinModule()

    fun searchBooksByISBN(isbn: String, page: Int): BookSearchResponse {
        val xml = SimpleGRClient.search(grApiKey, isbn, page, ALL)
        val goodreadsResponse = mapper.readValue(xml, GoodreadsResponse::class.java)
        return extractBooks(goodreadsResponse)
    }

    private fun extractBooks(resposne: GoodreadsResponse): BookSearchResponse {
        val bookSearchResponse = BookSearchResponse()
        for (work: Work in resposne.search.results.work) {
            val book = Book(work.book.title)
            bookSearchResponse.books.add(book)
        }
        return bookSearchResponse
    }
}
