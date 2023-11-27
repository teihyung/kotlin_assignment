package com.bcit.myapp.data

enum class ApiEndPoints
    (
    val url: String
) {
    BASE_URL("https://api.artic.edu/api/v1"),
    ARTWORK("${BASE_URL.url}/artworks"),
    FIELDS("${ARTWORK.url}?fields=id,title,image_id")
}