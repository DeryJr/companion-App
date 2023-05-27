package com.example.companion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.companion.ui.theme.CompanionTheme

sealed class Destination(val route: String) {
    object Intro : Destination("Intro")
    object Login : Destination("Login")
    object Signup : Destination("Signup")
}

class IntroActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompanionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    val navController = rememberNavController()
                    Intro(navController = navController)
                }
            }
        }
    }
}

@Composable
fun Intro(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Destination.Intro.route) {
        composable(Destination.Intro.route) { IntroScreen(navController = navController) }
        composable(Destination.Signup.route) { SignUpScreen(navController = navController) }
        composable(Destination.Login.route) { LoginScreen(navController = navController) }
    }

}