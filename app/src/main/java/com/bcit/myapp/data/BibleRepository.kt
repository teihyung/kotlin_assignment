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

        println(json)

        return deserializeJson(json)
    }

    private fun deserializeJson(json: String): Bible {
        if (json == "" || json == null) {
            println("Fuck")
            return null!!
        }
        val jsonObject = Gson().fromJson(json, JsonObject::class.java)
        val versesArray = jsonObject.getAsJsonArray("verses") ?: throw IllegalArgumentException("No verses found")

        val verses = versesArray.map { verseElement ->
            Gson().fromJson(verseElement, BibleContext::class.java)
        }

        return Bible(verses)
    }

//    suspend fun getBibleVerse(reference: String): Bible
//    {
//        val response = client.get(ApiEndPoints.BASE_URL.verseUrl(reference))
//
//        val json = response.body<JsonObject>().toString()
//
//        val result = deserializeJson(json)
//        println(result)
//
//        return result
//    }
//
//    private fun deserializeJson(json:String) : Bible
//    {
//        return Gson().fromJson(json, Bible::class.java)
//    }
}