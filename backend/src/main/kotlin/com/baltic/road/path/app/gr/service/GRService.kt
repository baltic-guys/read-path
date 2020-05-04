package com.baltic.road.path.app.gr.service

import com.baltic.road.path.app.gr.client.SimpleGRClient
import com.baltic.road.path.app.gr.client.SimpleGRClient.SearchType.ALL
import com.baltic.road.path.app.model.Book
import com.baltic.road.path.app.model.BookSearchResponse
import com.baltic.road.path.app.gr.model.api.GoodreadsResponse
import com.baltic.road.path.app.gr.model.api.Work
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.dataformat.xml.XmlMapper

// todo: interface for other datagate
class GRService {
    private var grApiKey = "qF3rQniJYU52xvIHtbYFA"
    private var mapper = XmlMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setPropertyNamingStrategy(PropertyNamingStrategy.KEBAB_CASE)

    fun searchBooksByISBN(isbn: String, page: Int): BookSearchResponse {
        val xml = SimpleGRClient.search(grApiKey, isbn, page, ALL)
        val goodreadsResponse = mapper.readValue(xml, GoodreadsResponse::class.java)
        return extractBooks(goodreadsResponse)
    }

    private fun extractBooks(resposne: GoodreadsResponse): BookSearchResponse {
        val bookSearchResponse = BookSearchResponse()
        for(work: Work in resposne.search?.results?.work!!) {
            var book = Book()
            book.title = work.book?.title
            bookSearchResponse.books.add(book)
        }
        return bookSearchResponse
    }
}