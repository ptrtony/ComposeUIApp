package com.example.composecoursety.launchedeffect

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composecoursety.LaunchedEffectList
import com.example.composecoursety.ScreenRoute
import com.example.composecoursety.TITLE_PARAMS
import com.example.composecoursety.actionbar.ComposeUIContainer

@Composable
fun LaunchedEffectScreen(modifier: Modifier, title: String?, controller: NavHostController) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenRoute.LaunchedEffectScreen.route) {
        composable(ScreenRoute.LaunchedEffectScreen.route) {
            ComposeUIContainer(title = title, navHostController = controller) {
                LaunchedEffectSubScreen(modifier = modifier, navController = navController)
            }
        }

        composable(ScreenRoute.DerivedStateOfScreen.route +  routeParams(),
            arguments = listOf(
                navArgument(TITLE_PARAMS) {
                    defaultValue = TITLE_PARAMS
                    type = NavType.StringType
                }
            )) { entity ->
            val titleParam = entity.arguments?.getString(TITLE_PARAMS)
            ComposeUIContainer(title = titleParam, navHostController = navController) {
                DerivedStateOfDemo()
            }
        }

        composable(ScreenRoute.DisposableEffectScreen.route + routeParams(),
            arguments = listOf(
                navArgument(TITLE_PARAMS) {
                    defaultValue = TITLE_PARAMS
                    type = NavType.StringType
                }
            )) { entity ->
            val titleParam = entity.arguments?.getString(TITLE_PARAMS)
            ComposeUIContainer(title = titleParam, navHostController = navController) {
                DisposableEffectDemo()
            }
        }

        composable(ScreenRoute.LaunchedEffectAnimationScreen.route + routeParams(),
        arguments = listOf(
            navArgument(TITLE_PARAMS) {
                defaultValue = TITLE_PARAMS
                type = NavType.StringType
            }
        )) { entity ->
            val titleParam = entity.arguments?.getString(TITLE_PARAMS)
            ComposeUIContainer(title = titleParam, navHostController = navController) {
                LaunchedEffectAnimation(0)
            }
        }

        composable(ScreenRoute.LaunchedEffectFlowScreen.route + routeParams(),
            arguments = listOf(
                navArgument(TITLE_PARAMS) {
                    defaultValue = TITLE_PARAMS
                    type = NavType.StringType
                }
            )) { entity ->
            val titleParam = entity.arguments?.getString(TITLE_PARAMS)
            ComposeUIContainer(title = titleParam, navHostController = navController) {
                val model = viewModel<LaunchedEffectViewModel>()
                LaunchedEffectFlowDemo(model)
            }
        }

        composable(ScreenRoute.ProduceStateScreen.route + routeParams(),
            arguments = listOf(
                navArgument(TITLE_PARAMS) {
                    defaultValue = TITLE_PARAMS
                    type = NavType.StringType
                }
            )) { entity ->
            val titleParam = entity.arguments?.getString(TITLE_PARAMS)
            ComposeUIContainer(title = titleParam, navHostController = navController) {
                ProduceStateDemo(0)
            }
        }

        composable(ScreenRoute.RememberCoroutineScreen.route + routeParams(),
            arguments = listOf(
                navArgument(TITLE_PARAMS) {
                    defaultValue = TITLE_PARAMS
                    type = NavType.StringType
                }
            )) { entity ->
            val titleParam = entity.arguments?.getString(TITLE_PARAMS)
            ComposeUIContainer(title = titleParam, navHostController = navController) {
                RememberCoroutineScope()
            }
        }
        composable(ScreenRoute.RememberUpdateStateScreen.route + routeParams(),
            arguments = listOf(
                navArgument(TITLE_PARAMS) {
                    defaultValue = TITLE_PARAMS
                    type = NavType.StringType
                }
            )) { entity ->
            val titleParam = entity.arguments?.getString(TITLE_PARAMS)
            ComposeUIContainer(title = titleParam, navHostController = navController) {
                RememberUpdateStateDemo{

                }
            }
        }
        composable(ScreenRoute.SideEffectScreen.route + routeParams(),
            arguments = listOf(
                navArgument(TITLE_PARAMS) {
                    defaultValue = TITLE_PARAMS
                    type = NavType.StringType
                }
            )) { entity ->
            val titleParam = entity.arguments?.getString(TITLE_PARAMS)
            ComposeUIContainer(title = titleParam, navHostController = navController) {
                SideEffectDemo(0)
            }
        }
    }
}

fun routeParams(): String {
    return "/{$TITLE_PARAMS}"
}


@Composable
fun LaunchedEffectSubScreen(modifier: Modifier, navController: NavHostController) {
    LazyColumn(
        modifier = modifier
    ) {
        items(LaunchedEffectList().size) { i ->
            val item = LaunchedEffectList()[i]
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(item.route.route + "/" + item.name)
                    }

            ) {
                Text(
                    text = item.name,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Divider()
            }
        }
    }
}