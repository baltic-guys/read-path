package com.baltic.road.path.app.gr.model.api

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper

class Results {
    @JacksonXmlElementWrapper(useWrapping = false)
    var work: List<Work> = ArrayList()
}