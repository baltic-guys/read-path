package com.baltic.road.path.app.gr.model.api

data class Search(
    var resultsStart: Int,
    var resultsEnd: Int,
    var totalResults: Int,
    var results: Results
)
