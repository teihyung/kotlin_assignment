package com.bcit.myapp.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class FormState {

    var verse by mutableStateOf("")

    fun onVerseChanged(newVerse: String) {
        verse = newVerse
    }

}