package com.example.composecoursety.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationBarItem(val route: String, val name: String, val icon: ImageVector, val badgeCount: Int = 0)
