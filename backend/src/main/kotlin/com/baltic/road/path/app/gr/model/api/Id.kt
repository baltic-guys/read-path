package com.baltic.road.path.app.gr.model.api

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText

data class Id(
    @JacksonXmlProperty(isAttribute = true)
    var type: String
) {
    @JacksonXmlText
    lateinit var value: String private set
}
