package com.bcit.myapp

import android.app.Application
import com.bcit.myapp.data.BibleRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.gson.gson

class MyApp : Application(){
    private val client by lazy {
        HttpClient {
            install(ContentNegotiation) {
                gson()
            }
        }
    }

    val bibleRepository by lazy{
        BibleRepository(client)
    }
}