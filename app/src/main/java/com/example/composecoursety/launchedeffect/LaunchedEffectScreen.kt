package com.example.composecoursety.launchedeffect

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composecoursety.LaunchedEffectList
import com.example.composecoursety.ScreenRoute

@Composable
fun LaunchedEffectScreen(modifier: Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenRoute.DisposableEffectScreen.route) {
        composable(ScreenRoute.DerivedStateOfScreen.route) {
            DerivedStateOfDemo()
        }

        composable(ScreenRoute.DisposableEffectScreen.route) {
            ScreenRoute.DisposableEffectScreen.route
        }

        composable(ScreenRoute.LaunchedEffectAnimationScreen.route) {
            LaunchedEffectAnimation(0)
        }

        composable(ScreenRoute.LaunchedEffectFlowScreen.route) {
            val model = viewModel<LaunchedEffectViewModel>()
            LaunchedEffectFlowDemo(model)
        }

        composable(ScreenRoute.ProduceStateScreen.route) {
            ProduceStateDemo(0)
        }

        composable(ScreenRoute.RememberCoroutineScreen.route) {
            RememberCoroutineScope()
        }
        composable(ScreenRoute.RememberUpdateStateScreen.route) {
            RememberUpdateStateDemo{

            }
        }
        composable(ScreenRoute.SideEffectScreen.route) {
            SideEffectDemo(0)
        }
    }

    LazyColumn(
        modifier = modifier
    ) {
        items(LaunchedEffectList().size) { i ->
            val item = LaunchedEffectList()[i]
            Text(
                text = item.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp, horizontal = 16.dp)
                    .clickable {
                        navController.navigate(item.route.route)
                    }
            )
        }
    }
}