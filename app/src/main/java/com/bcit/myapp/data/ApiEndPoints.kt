package com.bcit.myapp.data

enum class ApiEndPoints
    (
    val url: String
) {
    BASE_URL("https://bible-api.com/"),
    ARTWORK("${BASE_URL.url}/artworks"),
    FIELDS("${ARTWORK.url}?fields=id,title,image_id")
}