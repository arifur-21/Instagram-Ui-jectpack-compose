package com.example.instagramcloneuiapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.instagramcloneuiapp.screeens.*

@Composable
fun  InstagramNavGrap(
    navController: NavHostController
){

NavHost(navController = navController, startDestination = BottomNavRoute.HOME.route ){

    composable(route = BottomNavRoute.HOME.route){ HomeScreen()}
    composable(route = BottomNavRoute.SEARCH.route){ SearchScreen()}
    composable(route = BottomNavRoute.REELS.route){ ReelsScreen()}
    composable(route = BottomNavRoute.NOTIFICATION.route){ NotificationScreen()}
    composable(route = BottomNavRoute.PROFILE.route){ ProfileScreen()}

}

}