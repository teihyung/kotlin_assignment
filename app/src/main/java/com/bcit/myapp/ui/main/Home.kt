package com.bcit.myapp.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.format.TextStyle


@Composable
fun Home(bibleState: BibleState) {
    var showResult by remember { mutableStateOf(false) }
    var textFieldValue by remember { mutableStateOf("") }
    var buttonClicked by remember { mutableStateOf(false) }


    LaunchedEffect(key1 = textFieldValue, key2 = buttonClicked) {
        if (buttonClicked) {
            bibleState.getBibleVerse(textFieldValue)
            buttonClicked = false
        }
    }



    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(20.dp))
        CustomTextField(
            value = textFieldValue,
            onValueChanged = { textFieldValue = it },
            textFieldTitle = "Bible Verse Search"
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            showResult = true
            buttonClicked = true
        }) {
            Text(text = "Submit")
        }

        if (showResult) {
            result(bibleState)
        }


    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    textFieldTitle: String
) {
    Text(textFieldTitle,
        style = androidx.compose.ui.text.TextStyle(
            fontSize = 20.sp // Specify your desired font size here
        )

    )
    TextField(
        value = value,
        onValueChange = { onValueChanged(it) },
        modifier = Modifier.fillMaxWidth()
    )
}


@Composable
fun result(bibleState: BibleState) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.primaryContainer) // Use MaterialTheme to get a color from your theme
    ) {
        Column {
            Text(
                text = "Bible Verses",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            )

            Divider(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            LazyColumn {
                items(bibleState.bibleWork.value.size) { index ->
                    Text(
                        text = "${bibleState.bibleWork.value[index].book_name}, " +
                                "Chapter: ${bibleState.bibleWork.value[index].chapter} " +
                                "Verse: ${bibleState.bibleWork.value[index].verse}\n" +
                                "${bibleState.bibleWork.value[index].text}",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}
