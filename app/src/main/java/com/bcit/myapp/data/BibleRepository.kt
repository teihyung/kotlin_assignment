package com.bcit.myapp.data

import com.google.gson.Gson
import com.google.gson.JsonObject
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class BibleRepository(
    private val client: HttpClient
){

    suspend fun getBible() : Bible{
        val response = client.get(ApiEndPoints.FIELDS.url)

        val json = response.body<JsonObject>().toString()

        return deserializeJson(json)
    }

    private fun deserializeJson(json: String): Bible {
        return Gson().fromJson(json, Bible::class.java)
    }

}