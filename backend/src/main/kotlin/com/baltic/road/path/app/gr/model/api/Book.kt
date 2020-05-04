package com.baltic.road.path.app.gr.model.api

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

class Book {
    @JacksonXmlProperty(isAttribute = true)
    var type: String? = null
    var id: Id? = null
    var title: String? = null
    @JacksonXmlElementWrapper(useWrapping = false)
    var author: List<Author> = ArrayList()
}