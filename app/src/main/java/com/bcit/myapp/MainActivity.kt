package com.bcit.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import com.bcit.myapp.ui.main.BibleState



data class NavItem(val icon: ImageVector, val route: String, val action: (() -> Unit)? = null)
enum class Screen(val route:String)
{
    HOME("home"),
    BACK("back"),
    LIST("list")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bibleRepository = (application as MyApp).bibleRepository

        setContent {
            val bibleState = BibleState(bibleRepository)



            LaunchedEffect(key1 = bibleState,
                block ={
                bibleState.getBibleVerse("")
            })

            MainContent(bibleState)
        }
    }
}