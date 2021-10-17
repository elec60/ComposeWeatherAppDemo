package com.mousavi.composeweatherapp.util

sealed class Screen(val route: String){
    object MainScreen : Screen("main_screen")
    object SevenDaysScreen : Screen("seven_days_screen")
}
