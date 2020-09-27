package com.baltic.road.path.app.gr.service

import com.baltic.road.path.app.model.BookSearchResponse
import spock.lang.Specification

class GRServiceSpec extends Specification {

    def 'GRService should search books by ISBN'() {
        given:
        def service = new GRService()
        def isbn = '1617294942'

        when:
        BookSearchResponse bookSearchResponse = service.searchBooksByISBN(isbn, 1)

        then:
        noExceptionThrown()
        bookSearchResponse.books.size() > 0
    }
}