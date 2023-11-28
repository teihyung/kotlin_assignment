package com.bcit.myapp.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bcit.myapp.data.FormState

@Composable
fun Home(bibleState: BibleState, formState: FormState, verseChanged: (String) -> Unit) {


    var showResult by remember { mutableStateOf(false) }


    Column {
        Column(modifier = Modifier.padding(16.dp)) {
            // Keep track of the text field's value
            var textFieldValue by remember { mutableStateOf(formState.verse) }

            CustomTextField(
                value = textFieldValue,
                onValueChanged = { newValue ->
                    // Update the state with the new value when the user types in the text field
                    textFieldValue = newValue
                },
                textFieldTitle = "Bible verse:"
            )
            Button(
                onClick = {
                    // Here we call the verseChanged() function with the current text field value
                    verseChanged(textFieldValue)
                    // Since we're simulating, we simply set showResult to true
                    showResult = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text("Submit")
            }
        }
        if (showResult) {
            result(bibleState)
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