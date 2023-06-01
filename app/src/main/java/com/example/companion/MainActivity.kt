package com.example.companion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.companion.ui.theme.theme.CompanionTheme
import kotlinx.coroutines.launch


sealed class Destinations(val route: String) {
    object Home : Destinations("home")
    object Detail : Destinations("detail")
    object Map : Destinations("map")
    object Hotels : Destinations("hotels")
    object Flights : Destinations("flights")
    object Guides : Destinations("guides")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                moveTaskToBack(true)
            }
        })
        setContent {
            CompanionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White,
                ) {
                    val navController = rememberNavController()
                    CompanionScaffold(
                        navController = navController,
                    )
                }
            }
        }
    }
}

@Composable
fun CompanionScaffold(
    navController: NavHostController,
) {

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val onDrawerIconClick: () -> Unit = {
        scope.launch {
            scaffoldState.drawerState.open()
        }
    }

    val backCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val currentRoute = navController.currentBackStackEntry?.destination?.route
                val homeRoute = Destinations.Home.route
                val detailRoute = Destinations.Detail.route

                if (currentRoute != homeRoute && currentRoute != detailRoute) {
                    // Navigate back to Home screen if not on Home or Details screen
                    navController.popBackStack(homeRoute, inclusive = false)
                } else if (currentRoute == detailRoute) {
                    // If on Details screen, navigate back to Home screen
                    navController.navigate(homeRoute) {
                        popUpTo(homeRoute) {
                            inclusive = true
                        }
                    }
                } else {
                    // Double press back within a certain timeout to exit the app
                    // Implement your desired behavior here (e.g., show a toast, prompt for confirmation)
                }
            }
        }
    }

    val backPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    DisposableEffect(backPressedDispatcher) {
        backPressedDispatcher?.addCallback(backCallback)
        onDispose {
            backCallback.remove()
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = { BottomNav(navController) },
        topBar = { TopNav(navController = navController, onDrawerIconClick) },
        drawerContent = { NavigationDrawer() },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
    ) { paddingValues ->
        Modifier
            .padding(bottom = paddingValues.calculateBottomPadding())
            .background(Color(0xffcccccc))

        NavHost(navController = navController, startDestination = Destinations.Home.route) {
            composable(Destinations.Home.route) {
                HomePageScreen(navController = navController)
            }
            composable(Destinations.Map.route) {
                MapScreen(navController)
            }
            composable(Destinations.Detail.route) {
                DetailsScreen(navController = navController)
            }
            composable(Destinations.Flights.route) {
                FlightsScreen(navController = navController)
            }
            composable(Destinations.Hotels.route) {
                HotelScreen(navController = navController)
            }
            composable(Destinations.Guides.route) {
                GuideScreen(navController = navController)
            }
        }
    }
}