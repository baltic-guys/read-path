package com.baltic.road.path.app.gr.client

object SimpleGRClient {

    private const val baseUrl = "https://www.goodreads.com"

    @JvmStatic
    fun request(query: String): String {
        return khttp.get(query).text
    }

    @JvmStatic
    fun search(key: String, text: String, page: Int, searchType: SearchType): String {
        val query = "$baseUrl/search/index.xml?q=$text&page=$page&search=${searchType.type}&key=$key"
        return request(query)
    }


    enum class SearchType(val type: String) {
        TITLE("title"),
        AUTHOR("author"),
        ALL("all")
    }
}
