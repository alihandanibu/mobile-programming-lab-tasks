package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.navigation.BottomBarNavigationComponent
import com.example.myapplication.ui.navigation.BottomBarNavigationItems
import com.example.myapplication.ui.navigation.NavGraph
import com.example.myapplication.ui.navigation.Screen
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val bottomBarItems = BottomBarNavigationItems.items
                val showBottomBar = currentRoute in Screen.bottomNavRoutes()

                val selectedItemIndex = bottomBarItems.indexOfFirst {
                    it.route == currentRoute
                }.coerceAtLeast(0)

                Scaffold(
                    bottomBar = {
                        if (showBottomBar) {
                            BottomBarNavigationComponent(
                                items = bottomBarItems,
                                selectedItemIndex = selectedItemIndex,
                                onItemSelected = { index ->
                                    val item = bottomBarItems[index]
                                    navController.navigate(item.route) {
                                        launchSingleTop = true
                                        restoreState = true
                                        popUpTo(Screen.HomeShortcut.route) {
                                            saveState = true
                                        }
                                    }
                                }
                            )
                        }
                    }
                ) { innerPadding ->
                    NavGraph(
                        navController = navController,
                        startDestination = Screen.Register.route,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}