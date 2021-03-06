package com.cevlikalprn.fancybottomnavigationbar

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cevlikalprn.bottombar.FancyBottomNavigation
import com.cevlikalprn.fancybottomnavigationbar.BottomNavItemProvider.Companion.provideBottomNavItemList

class MainActivity : ComponentActivity() {

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val navController = rememberNavController()

            val bottomNavItemList = provideBottomNavItemList()

            Scaffold(
                bottomBar = {
                    FancyBottomNavigation(
                        navController = navController,
                        bottomNavItemList = bottomNavItemList,
                        modifier = Modifier,
                        centerItemIcon = Icons.Outlined.Share,
                        clicks = Pair(
                            first = { bottomNavItem ->
                                navController.navigate(bottomNavItem.route)
                            },
                            second = {
                                Log.i("MainActivity", "Center Item Clicked!")
                            }
                        )
                    )
                }) {
                Navigation(navController = navController)
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            HomeScreen()
        }
        composable(route = "chat") {
            ChatScreen()
        }
    }
}