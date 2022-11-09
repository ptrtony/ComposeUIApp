package com.example.composecoursety.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScaffoldMain() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.DarkGray),
                items = listOf(
                    NavigationBarItem(
                        "home",
                        "Home",
                        Icons.Default.Home
                    ),
                    NavigationBarItem(
                        "chat",
                        "Chat",
                        Icons.Default.Notifications,
                        99
                    ),
                    NavigationBarItem(
                        "settings",
                        "Settings",
                        Icons.Default.Settings,
                        199
                    )
                ),
                navController = navController
            ) {
                navController.navigate(it.route)
            }
        },
        content = {
            Navigation(navController = navController)
        }
    )
}


@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    items: List<NavigationBarItem>,
    navController: NavHostController,
    onItemClick: (NavigationBarItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        elevation = 5.dp,
        backgroundColor = Color.DarkGray
    ) {
        items.forEach { item ->
            val selected = backStackEntry.value?.destination?.route == item.route
            BottomNavigationItem(
                selected = selected,
                onClick = {
                    onItemClick(item)
                },
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        if (item.badgeCount > 0) {
                            BadgedBox(badge = {
                                Badge {
                                    Text(text = item.badgeCount.toString())
                                }
                            }) {
                                Icon(imageVector = item.icon, contentDescription = item.route)
                            }
                        } else {
                            Icon(imageVector = item.icon, contentDescription = item.route)
                        }

                        if (selected) {
                            Text(text = item.name)
                        }
                    }
                },
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.Gray
            )
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen()
        }

        composable("chat") {
            ChatScreen()
        }

        composable("settings") {
            SettingsScreen()
        }
    }
}

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "HomeScreen")
    }
}

@Composable
fun ChatScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "ChatScreen")
    }
}

@Composable
fun SettingsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "SettingsScreen")
    }
}