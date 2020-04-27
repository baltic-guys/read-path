package com.baltic.road.path.app.gr

class SimpleGRClient {
    companion object {
        @JvmStatic
        fun request(query: String): String {
            return khttp.get(query).text
        }
    }
}