package com.example.instagramcloneuiapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun InstagramApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavBar(navController) },
        backgroundColor = Color.White
    ) {innerPadding->
        Box(modifier = Modifier.padding(innerPadding)) {

            InstagramNavGrap(navController)
        }

    }

}

@Composable
fun BottomNavBar(navController: NavHostController) {

    val navBackScreens by navController.currentBackStackEntryAsState()
    val currentDestination = navBackScreens?.destination

    val screenList = listOf(
        BottomNavScreens.Home,
        BottomNavScreens.Search,
        BottomNavScreens.Reels,
        BottomNavScreens.Notification,
        BottomNavScreens.Profile
    )

    BottomNavigation {
        screenList.forEach { screen ->
            BottomNavigationItem(
                selected = currentDestination?.route == screen.route,
                onClick = {
                    navController.navigate(screen.route)
                },
                icon = {
                    if (screen.route == BottomNavRoute.PROFILE.route){

                        CircularProfileViw()
                    }else{
                        Icon(
                            painter = painterResource(id = screen.icon),
                            contentDescription = "nav icon",
                            modifier = Modifier.size(25.dp)
                        )
                    }

                },
                modifier = Modifier.background(color = Color.White),
                selectedContentColor = Color.Red,
                unselectedContentColor = Color.Black
            )
        }
    }

}

@Composable
fun CircularProfileViw() {
    Image(
        painter = painterResource(id = R.drawable.coffe), contentDescription = "icon",
        modifier = Modifier
            .size(25.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}


@Composable
@Preview(showBackground = true)
fun ProfilePreview(){
CircularProfileViw()
}
