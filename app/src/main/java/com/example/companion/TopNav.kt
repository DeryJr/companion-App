package com.example.companion

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun TopNav(navController: NavController, onDrawerIconClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Scaffold navigation") },
        navigationIcon = {
            IconButton(onClick = onDrawerIconClick) {
                Icon(Icons.Default.Menu, contentDescription = null)
            }
        }
    )
}