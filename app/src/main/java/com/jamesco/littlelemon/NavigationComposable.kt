package com.jamesco.littlelemon

import android.content.Context
import android.content.SharedPreferences
import android.view.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(navController: NavHostController){
    val context = LocalContext.current
    val sharedPreferences: SharedPreferences = remember{context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)}
    val startDestination = if (sharedPreferences.getBoolean("isLoggedIn", false))
        Home.route else OnBoarding.route
    NavHost(navController = navController, startDestination = startDestination){
            composable(OnBoarding.route){
                Onboarding(navController)
            }
            composable(Home.route){
                Home(navController)
            }
            composable(Profile.route){
                Profile(navController)
            }
    }
}