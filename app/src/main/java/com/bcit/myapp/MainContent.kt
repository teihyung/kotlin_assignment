package com.bcit.myapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bcit.myapp.ui.main.BibleState
import com.bcit.myapp.ui.main.BottomNavBar
import com.bcit.myapp.ui.main.Home

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(bibleState: BibleState) {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavBar(navController)
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = Screen.HOME.route,
            builder = {
                composable(Screen.HOME.route){
                    Home(bibleState)
                }

                composable(Screen.LIST.route){

                }
                composable(Screen.BACK.route){
                    navController.popBackStack()
                }
            })
    }

}