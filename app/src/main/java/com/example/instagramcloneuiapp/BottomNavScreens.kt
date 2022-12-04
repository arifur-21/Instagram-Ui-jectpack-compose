package com.example.instagramcloneuiapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home

sealed class BottomNavScreens(
    val title: String,
    val icon: Int = R.drawable.ic_baseline_home_24,
    val route: String
){

    object Home: BottomNavScreens(title = "home", icon = R.drawable.ic_baseline_home_24, route = BottomNavRoute.HOME.route)
    object Search: BottomNavScreens(title = "Search", icon = R.drawable.ic_baseline_home_24, route = BottomNavRoute.SEARCH.route)
    object Reels: BottomNavScreens(title = "Reels", icon = R.drawable.ic_baseline_home_24, route = BottomNavRoute.REELS.route)
    object Notification: BottomNavScreens(title = "Notification", icon = R.drawable.ic_baseline_home_24, route = BottomNavRoute.NOTIFICATION.route)
    object Profile: BottomNavScreens(title = "Profile",  route = BottomNavRoute.PROFILE.route)
}

enum class BottomNavRoute(val route: String){
    HOME("home"),
    SEARCH("search"),
    REELS("reels"),
    NOTIFICATION("notification"),
    PROFILE("profile")
}