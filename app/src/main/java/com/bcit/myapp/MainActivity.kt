package com.bcit.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import com.bcit.myapp.data.BibleRepository
import com.bcit.myapp.data.FormState
import com.bcit.myapp.ui.main.BibleState
import com.bcit.myapp.ui.main.BottomNavBar
import com.bcit.myapp.ui.main.Home
import com.bcit.myapp.ui.theme.MyAppTheme


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

            var verse by remember {
                mutableStateOf("romans+12:1")
            }

            val bibleState = BibleState(bibleRepository)

            LaunchedEffect(
                key1 = bibleState,
                block = {
                    bibleState.getBibleVerse(verse)
                })

            MainContent(bibleState, verseChanged = {
                newVerse -> verse = newVerse
            })
        }
    }
}