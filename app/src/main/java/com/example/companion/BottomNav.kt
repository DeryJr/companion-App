package com.example.companion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNav(navController: NavController) {
    BottomNavigation {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry.value?.destination

        BottomNavigationItem(
            selected = currentDestination?.route == Destinations.Home.route,
            onClick = { navController.navigate(Destinations.Home.route) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_home_white),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color(0xFFFF9800))
                        .width(50.dp)
                        .height(50.dp)
                )
            },
        )
        BottomNavigationItem(
            selected = currentDestination?.route == Destinations.Map.route,
            onClick = { navController.navigate(Destinations.Map.route) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_map_black),
                    contentDescription = null,
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                )
            },
        )
        BottomNavigationItem(
            selected = currentDestination?.route == Destinations.Hotels.route,
            onClick = { navController.navigate(Destinations.Hotels.route) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bed),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .width(50.dp)
                        .height(50.dp)
                )
            },
        )
        BottomNavigationItem(
            selected = currentDestination?.route == Destinations.Guides.route,
            onClick = { navController.navigate(Destinations.Guides.route) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_guides_notes),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color(0xFFFF9800))
                        .width(45.dp)
                        .height(45.dp)
                )
            },
        )

        BottomNavigationItem(
            selected = currentDestination?.route == Destinations.Flights.route,
            onClick = { navController.navigate(Destinations.Flights.route) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_plain_black),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .width(40.dp)
                        .height(40.dp)
                )
            },
        )
    }
}