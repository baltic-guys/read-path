package com.baltic.road.path.app.gr.model.api

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class Book(
    @JacksonXmlProperty(isAttribute = true)
    var type: String,
    var id: Id,
    var title: String,
    @JacksonXmlElementWrapper(useWrapping = false)
    var author: List<Author> = ArrayList()
)
