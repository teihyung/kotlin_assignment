package com.bcit.myapp.data

import com.google.gson.annotations.SerializedName

data class Bible(
    @SerializedName("data")
    val verse: List<BibleContext>
)

data class BibleContext(
    val id: String,
    val book_name: String,
    val chapter:String,
    val verse:String,
    val text:String
)