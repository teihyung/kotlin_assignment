package com.bcit.myapp.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.List
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bcit.myapp.NavItem
import com.bcit.myapp.Screen

@Composable
fun BottomNavBar(navController: NavController) {
    val navItems = listOf(
        NavItem(Icons.Rounded.Home, Screen.HOME.route)
    )

    NavigationBar(containerColor = Color(0xFF8F60DB)) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        navItems.forEach { item ->
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {

                IconButton(onClick = { navController.navigate(item.route) }) {
                    Icon(
                        item.icon,
                        contentDescription = null,
                    )
                }
            }
        }
    }
}

