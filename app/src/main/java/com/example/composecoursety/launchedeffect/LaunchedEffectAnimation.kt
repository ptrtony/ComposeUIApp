package com.example.composecoursety.launchedeffect

import androidx.compose.animation.core.Animatable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

@Composable
fun LaunchedEffectAnimation(counter: Int) {
    val animation = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = counter) {
        animation.animateTo(counter.toFloat())
    }
}