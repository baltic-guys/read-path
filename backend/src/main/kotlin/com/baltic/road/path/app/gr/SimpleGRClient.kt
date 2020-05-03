package com.baltic.road.path.app.gr

object SimpleGRClient {
    @JvmStatic
    fun request(query: String): String {
        return khttp.get(query).text
    }
}
