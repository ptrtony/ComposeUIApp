@file:OptIn(ExperimentalMotionApi::class, ExperimentalPermissionsApi::class)

package com.example.composecoursety

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composecoursety.allscreensize.AllScreenDemo
import com.example.composecoursety.animation.animationDpAsStateDemo
import com.example.composecoursety.bottomsheet.BottomSheetDemo
import com.example.composecoursety.deeplink.DeepLinkDemo
import com.example.composecoursety.drag.Output
import com.example.composecoursety.drawer.DrawerSceneDemo
import com.example.composecoursety.launchedeffect.LaunchedEffectScreen
import com.example.composecoursety.lazyverticalgrid.LazyVerticalGridDemo
import com.example.composecoursety.loadmorelist.ListItemsScene
import com.example.composecoursety.motionlayout.ProfileHeadScene
import com.example.composecoursety.multiselect.MultiSelectedDemo
import com.example.composecoursety.navigation.Navigation
import com.example.composecoursety.parallaxscroll.ParallaxScrollScreen
import com.example.composecoursety.performanceoptimization.RgbSelectorDemo
import com.example.composecoursety.permission.PermissionDemo
import com.example.composecoursety.splash.Main
import com.example.composecoursety.three_d_animation.ThreeDAnimationMain
import com.example.composecoursety.timer.CountDownTimer
import com.google.accompanist.permissions.ExperimentalPermissionsApi

data class ComposeUIBean(val name: String, val route: ScreenRoute)

sealed class ScreenRoute(val route: String) {
    object AllScreenSizeScreen: ScreenRoute(route = "allScreenSize")
    object AnimationScreen: ScreenRoute(route = "animation")
    object BottomSheetScreen: ScreenRoute(route = "bottomSheet")
    object DeepLinkScreen: ScreenRoute(route = "deepLink")
    object DragViewScreen: ScreenRoute(route = "dragView")
    object DrawerScreen: ScreenRoute(route = "drawer")
    object LaunchedEffectScreen: ScreenRoute(route = "launchedEffect")
    object LazyVerticalGridScreen: ScreenRoute(route = "lazyVerticalGrid")
    object LoadMoreListScreen: ScreenRoute(route = "loadMoreList")
    object MotionLayoutScreen: ScreenRoute(route = "motionLayout")
    object MultiSelectScreen: ScreenRoute(route = "multiSelect")
    object NavigationScreen: ScreenRoute(route = "navigation")
    object ParallaxscrollScreen: ScreenRoute(route = "parallaxscroll")
    object PerformanceOptimizationScreen: ScreenRoute(route = "performanceOptimization")
    object PermissionScreen: ScreenRoute(route = "permission")
    object SplashScreen: ScreenRoute(route = "splash")
    object ThreeDAnimationScreen: ScreenRoute(route = "threeDAnimation")
    object TimerScreen: ScreenRoute(route = "timer")
    object DerivedStateOfScreen: ScreenRoute(route = "derivedStateOf")
    object DisposableEffectScreen: ScreenRoute(route = "disposableEffect")
    object LaunchedEffectAnimationScreen: ScreenRoute(route = "launchedEffectAnimation")
    object LaunchedEffectFlowScreen: ScreenRoute(route = "launchedEffectFlow")
    object ProduceStateScreen: ScreenRoute(route = "produceState")
    object RememberCoroutineScreen: ScreenRoute(route = "rememberCoroutine")
    object RememberUpdateStateScreen: ScreenRoute(route = "rememberUpdateState")
    object SideEffectScreen: ScreenRoute(route = "sideEffect")
    object SnapShotFlowScreen: ScreenRoute(route = "snapShotFlow")
}


fun ComposeUIList(): List<ComposeUIBean> {
    return listOf(
        ComposeUIBean(name = "All Screen Size", route = ScreenRoute.AllScreenSizeScreen),
        ComposeUIBean(name = "Animation", route = ScreenRoute.AnimationScreen),
        ComposeUIBean(name = "Bottom Sheet", route = ScreenRoute.BottomSheetScreen),
        ComposeUIBean(name = "Deep Link", route = ScreenRoute.DeepLinkScreen),
        ComposeUIBean(name = "Drag View", route = ScreenRoute.DragViewScreen),
        ComposeUIBean(name = "Drawer", route = ScreenRoute.DrawerScreen),
        ComposeUIBean(name = "Launched Effect", route = ScreenRoute.LaunchedEffectScreen),
        ComposeUIBean(name = "Lazy Vertical Grid", route = ScreenRoute.LazyVerticalGridScreen),
        ComposeUIBean(name = "Load More List", route = ScreenRoute.LoadMoreListScreen),
        ComposeUIBean(name = "Motion Layout", route = ScreenRoute.MotionLayoutScreen),
        ComposeUIBean(name = "Multi Selected", route = ScreenRoute.MultiSelectScreen),
        ComposeUIBean(name = "Navigation", route = ScreenRoute.NavigationScreen),
        ComposeUIBean(name = "ParallaxScroll", route = ScreenRoute.ParallaxscrollScreen),
        ComposeUIBean(name = "Performance Optimization", route = ScreenRoute.PerformanceOptimizationScreen),
        ComposeUIBean(name = "Permission", route = ScreenRoute.PermissionScreen),
        ComposeUIBean(name = "Splash", route = ScreenRoute.SplashScreen),
        ComposeUIBean(name = "Three D Animation", route = ScreenRoute.ThreeDAnimationScreen),
        ComposeUIBean(name = "Timer", route = ScreenRoute.TimerScreen)
    )
}


fun LaunchedEffectList(): List<ComposeUIBean> {
    return listOf(
        ComposeUIBean(name = "Derived State ", route = ScreenRoute.DerivedStateOfScreen),
        ComposeUIBean(name = "Disposable Effect", route = ScreenRoute.DisposableEffectScreen),
        ComposeUIBean(name = "Launched Effect Animation", route = ScreenRoute.LaunchedEffectAnimationScreen),
        ComposeUIBean(name = "Launched Effect Flow", route = ScreenRoute.LaunchedEffectFlowScreen),
        ComposeUIBean(name = "Produce State", route = ScreenRoute.ProduceStateScreen),
        ComposeUIBean(name = "Remember Coroutine", route = ScreenRoute.RememberCoroutineScreen),
        ComposeUIBean(name = "Remember Update State", route = ScreenRoute.RememberUpdateStateScreen),
        ComposeUIBean(name = "Side Effect", route = ScreenRoute.SideEffectScreen),
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LazyColumnList(modifier: Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenRoute.AllScreenSizeScreen.route) {
        composable(ScreenRoute.AllScreenSizeScreen.route) {
            AllScreenDemo()
        }

        composable(ScreenRoute.AnimationScreen.route) {
            animationDpAsStateDemo()
        }

        composable(ScreenRoute.BottomSheetScreen.route) {
            BottomSheetDemo()
        }

        composable(ScreenRoute.DeepLinkScreen.route) {
            DeepLinkDemo()
        }

        composable(ScreenRoute.DragViewScreen.route) {
            Output()
        }

        composable(ScreenRoute.DrawerScreen.route) {
            DrawerSceneDemo()
        }

        composable(ScreenRoute.LaunchedEffectScreen.route) {
            LaunchedEffectScreen(
                modifier = Modifier.fillMaxSize()
                    .background(color = Color.White)
            )
        }

        composable(ScreenRoute.LazyVerticalGridScreen.route) {
            LazyVerticalGridDemo()
        }

        composable(ScreenRoute.LoadMoreListScreen.route) {
            ListItemsScene()
        }

        composable(ScreenRoute.MotionLayoutScreen.route) {
            ProfileHeadScene()
        }

        composable(ScreenRoute.MultiSelectScreen.route) {
            MultiSelectedDemo()
        }

        composable(ScreenRoute.NavigationScreen.route) {
            Navigation()
        }

        composable(ScreenRoute.ParallaxscrollScreen.route) {
            ParallaxScrollScreen()
        }

        composable(ScreenRoute.PerformanceOptimizationScreen.route) {
            RgbSelectorDemo()
        }

        composable(ScreenRoute.PermissionScreen.route) {
            PermissionDemo()
        }

        composable(ScreenRoute.SplashScreen.route) {
            Main()
        }

        composable(ScreenRoute.ThreeDAnimationScreen.route) {
            ThreeDAnimationMain()
        }

        composable(ScreenRoute.TimerScreen.route) {
            CountDownTimer(
                totalTime = 60 * 1000,
                handleColor = Color.Green,
                inactiveBarColor = Color.DarkGray,
                activeBarColor = Color.Green,
                modifier = Modifier.size(300.dp)
            )
        }
    }

    LazyColumn(
        modifier = modifier
            .background(color = Color.White)
    ) {
        items(ComposeUIList().size) { i ->
            val item = ComposeUIList()[i]
            Text(
                text = item.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 5.dp)
                    .clickable {
                        navController.navigate(item.route.route)
                    }
            )
        }
    }
}
