package com.baltic.road.path.app.gr.model.api

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class Work(
    var id: Id,
    @JacksonXmlProperty(localName = "average_rating")
    var averageRating: Float,
    @JacksonXmlProperty(localName = "best_book")
    var book: Book
)
