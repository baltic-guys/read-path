package com.baltic.road.path.app.gr.model.api

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText


class Id {
    @JacksonXmlProperty(isAttribute = true)
    var type: String? = null

    @JacksonXmlText
    private val value: Int? = null
}
