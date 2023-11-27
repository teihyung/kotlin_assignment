package com.bcit.myapp.data

enum class ApiEndPoints(val url: String) {
    BASE_URL("https://bible-api.com/");

    fun verseUrl(reference: String): String {
        return "${BASE_URL.url}$reference"
    }
}