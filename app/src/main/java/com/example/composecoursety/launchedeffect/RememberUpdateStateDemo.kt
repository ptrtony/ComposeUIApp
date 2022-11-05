package com.example.composecoursety.launchedeffect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.*
import kotlinx.coroutines.delay

@Composable
fun RememberUpdateStateDemo(onTimeOut: () -> Unit) {
    val updateOnTimeOut by rememberUpdatedState(newValue = onTimeOut)
    LaunchedEffect(true) {
        delay(1000L)
        updateOnTimeOut()
    }
}