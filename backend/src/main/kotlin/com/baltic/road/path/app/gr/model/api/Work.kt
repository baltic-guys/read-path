package com.baltic.road.path.app.gr.model.api

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

class Work {
    var id: Id? = null
    @JacksonXmlProperty(localName = "average_rating")
    var averageRating: Float? = null
    @JacksonXmlProperty(localName = "best_book")
    var book: Book? = null
}
