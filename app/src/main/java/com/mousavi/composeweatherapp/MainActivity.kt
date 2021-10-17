package com.mousavi.composeweatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mousavi.composeweatherapp.presentation.main.components.MainScreen
import com.mousavi.composeweatherapp.presentation.sevenDaysScreen.components.SevenDaysScreen
import com.mousavi.composeweatherapp.ui.theme.ComposeWeatherAppTheme
import com.mousavi.composeweatherapp.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeWeatherAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screen.MainScreen.route){
                    composable(
                        route = Screen.MainScreen.route
                    ){
                        MainScreen(navController)
                    }
                    composable(
                        route = Screen.SevenDaysScreen.route
                    ){
                        SevenDaysScreen(navController)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeWeatherAppTheme {
        MainScreen(rememberNavController())
    }
}