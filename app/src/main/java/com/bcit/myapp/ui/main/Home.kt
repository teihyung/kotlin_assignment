package com.bcit.myapp.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier


@Composable
fun Home(bibleState: BibleState) {
    var showResult by remember { mutableStateOf(false) }
    var textFieldValue by remember { mutableStateOf("") }
    var buttonClicked by remember { mutableStateOf(false) }

    // Triggered when textFieldValue changes and button has been clicked
    LaunchedEffect(key1 = textFieldValue, key2 = buttonClicked) {
        if (buttonClicked) {
            bibleState.getBibleVerse(textFieldValue)
            buttonClicked = false  // Reset the button click state
        }
    }

    Column {
        CustomTextField(
            value = textFieldValue,
            onValueChanged = { textFieldValue = it },
            textFieldTitle = "Bible verse:"
        )

        if (showResult) {
            result(bibleState)
        }

        Button(onClick = {
            showResult = true
            buttonClicked = true  // Indicate that the button has been clicked
        }) {
            Text(text = "Submit")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(value:String,
                    onValueChanged: (String) -> Unit,
                    textFieldTitle:String)
{
    Text(textFieldTitle)
    TextField(
        value = value,
        onValueChange = { onValueChanged(it) },
        modifier = Modifier.fillMaxWidth()
    )
}


@Composable
fun result(bibleState: BibleState) {
        LazyColumn(content = {
        items(bibleState.bibleWork.value.size) {
            Text(
                bibleState.bibleWork.value[it].book_name + "\n" +
                        bibleState.bibleWork.value[it].chapter + "\n" +
                        bibleState.bibleWork.value[it].verse + "\n" +
                        bibleState.bibleWork.value[it].text
            )
        }
    })

}