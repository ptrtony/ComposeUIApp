package com.example.composecoursety.animation

import android.util.Size
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun animationDpAsStateDemo() {
    var sizeState by remember {
        mutableStateOf(200.dp)
    }
    val size = animateDpAsState(targetValue = sizeState,
        tween(durationMillis = 3000, delayMillis = 300, easing = LinearEasing)
    /*spring(
        Spring.DampingRatioHighBouncy
    )*/
    /*keyframes {
        durationMillis = 5000
        sizeState at 0 with LinearEasing
        sizeState * 1.5f at 1000 with LinearOutSlowInEasing
        sizeState * 2f at 5000
    }*/
    )
    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 2000),
            repeatMode = RepeatMode.Reverse
        ))
    Box(modifier = Modifier
        .background(color = color)
        .size(size.value),
    contentAlignment = Alignment.Center) {
        Button(onClick = { sizeState += 50.dp },
        ) {
            Text(text = "reScreen size", color = Color.White)
        }
    }
}



@Composable
fun CircleProgress(
  percent: Float,
  number: Int,
  fontSize: TextUnit = 28.sp,
  radius : Dp = 60.dp,
  color: Color = Color.Green,
  strokeWidth: Float = 25f,
  animationDurationMillis: Int = 3000,
  animationDelayMillis: Int = 300
) {
    val animationPlayed = remember {
        mutableStateOf(false)
    }
    val curPercentage = animateFloatAsState(targetValue = if (animationPlayed.value) percent else 0f,
    animationSpec = tween(animationDurationMillis, delayMillis = animationDelayMillis, easing = FastOutSlowInEasing))
    LaunchedEffect(true) {
        animationPlayed.value = true
    }
    Canvas(modifier = Modifier.size(radius * 2f)) {
        drawArc(color, -90f, curPercentage.value * 360, useCenter = false,
        style = Stroke(strokeWidth, cap = StrokeCap.Round))
    }
    Text(text = ((curPercentage.value * number).toInt()).toString(),
    color = Color.Black,
    fontSize = fontSize,
    fontWeight = FontWeight.Bold)
}

