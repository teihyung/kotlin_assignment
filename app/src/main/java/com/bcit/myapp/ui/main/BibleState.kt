package com.bcit.myapp.ui.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.bcit.myapp.data.BibleContext
import com.bcit.myapp.data.BibleRepository

class BibleState(
    private val bibleRepository: BibleRepository
) {

    var bibleWork: MutableState<List<BibleContext>> = mutableStateOf(emptyList())

    suspend fun getBibleVerse(reference: String?) {
        if (!reference.isNullOrBlank()) {
            bibleWork.value = bibleRepository.getBibleVerse(reference).verse
        }

    }

}