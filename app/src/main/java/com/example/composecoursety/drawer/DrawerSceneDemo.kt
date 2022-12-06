package com.example.composecoursety.drawer

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.example.composecoursety.actionbar.ComposeUIContainer
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DrawerSceneDemo(title: String?, navController: NavHostController) {
    ComposeUIContainer(title = title, navHostController = navController) {
        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                AppBar(onNavigationIconClick = {
                    scope.launch {
                        if (scaffoldState.drawerState.isClosed) {
                            scaffoldState.drawerState.open()
                        } else {
                            scaffoldState.drawerState.close()
                        }
                    }
                })
            },
            drawerContent = {
                DrawerHead()
                DrawerBody(
                    items = listOf(
                        MenuItem(
                            id = "home",
                            title = "Home",
                            contentDescription = "Go to home screen",
                            icon = Icons.Default.Home
                        ),
                        MenuItem(
                            id = "settings",
                            title = "Settings",
                            contentDescription = "Go to settings screen",
                            icon = Icons.Default.Settings
                        ),
                        MenuItem(
                            id = "help",
                            title = "Help",
                            contentDescription = "Go to home screen",
                            icon = Icons.Default.Info
                        )
                    ),
                    onItemClick = {
                        when(it.id) {
                            "home" -> {

                            }
                            "settings" -> {

                            }
                            "help" -> {

                            }
                        }
                        println("Clicked on ${it.title}")
                    }
                )
            },
            drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
            content = {

            }
        )
    }
}