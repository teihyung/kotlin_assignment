package com.bcit.myapp.data

import com.google.gson.Gson
import com.google.gson.JsonObject
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class BibleRepository(private val client: HttpClient) {

    suspend fun getBibleVerse(reference: String): Bible {
        val url = ApiEndPoints.BASE_URL.verseUrl(reference)
        val response = client.get(url)
        val json = response.body<String>()

        return deserializeJson(json)
    }
    private fun deserializeJson(json: String): Bible {
        val jsonObject = Gson().fromJson(json, JsonObject::class.java)

        // Check if "verses" key exists and is a JsonArray, otherwise return an empty Bible
        val versesArray = if (jsonObject.has("verses") && jsonObject.get("verses").isJsonArray) {
            jsonObject.getAsJsonArray("verses")
        } else {
            return Bible(emptyList())  // Return an empty Bible object
        }

        // Deserialize each verse in the array
        val verses = versesArray.map { verseElement ->
            Gson().fromJson(verseElement, BibleContext::class.java)
        }

        return Bible(verses)
    }

}