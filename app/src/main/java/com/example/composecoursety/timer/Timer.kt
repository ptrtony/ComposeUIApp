package com.example.composecoursety.timer

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun CountDownTimer(
    totalTime: Int,
    handleColor: Color,
    inactiveBarColor: Color,
    activeBarColor: Color,
    modifier: Modifier = Modifier,
    initialValue: Float = 1f,
    strokeWidth: Dp = 5.dp
) {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

    var value by remember {
        mutableStateOf(initialValue)
    }

    var currentTime by remember {
        mutableStateOf(totalTime)
    }
    var isTimerRunning by remember {
        mutableStateOf(false)
    }


    LaunchedEffect(key1 = currentTime, key2 = isTimerRunning) {
        if (currentTime > 0 && isTimerRunning) {
            delay(100L)
            currentTime -= 100
            value = currentTime / totalTime.toFloat()
        }
    }

    Box(modifier = Modifier.onSizeChanged {
        size = it
    }, contentAlignment = Alignment.Center) {

        Canvas(modifier = modifier) {
            val centerX = size.center.x
            val centerY = size.center.y
            drawArc(
                color = inactiveBarColor,
                startAngle = -215f,
                sweepAngle = 250f,
                useCenter = false,
                size = size.toSize(),
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )

            drawArc(
                color = activeBarColor,
                startAngle = -215f,
                sweepAngle = 250f * value,
                useCenter = false,
                size = size.toSize(),
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
            val beta = (250f * value + 145f) * PI / 180
            val r = size.width / 2f
            val a = cos(beta) * r
            val b = sin(beta) * r
            drawPoints(
                points = listOf(Offset((a + centerX).toFloat(), (b + centerY).toFloat())),
                pointMode = PointMode.Points,
                color = handleColor,
                strokeWidth = (strokeWidth * 3f).toPx(),
                cap = StrokeCap.Round
            )
        }

        Text(
            text = (currentTime / 1000L).toString(),
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold
        )

        Button(
            onClick = {
                if (currentTime <= 0) {
                    isTimerRunning = true
                    currentTime = totalTime
                } else {
                    isTimerRunning = !isTimerRunning
                }
            }, colors = ButtonDefaults.buttonColors(
                containerColor = if (currentTime <= 0 || !isTimerRunning) {
                    Color.Green
                } else {
                    Color.Red
                }
            ),
            modifier = Modifier.align(Alignment.BottomCenter),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = if (currentTime >= 0 && !isTimerRunning) {
                    "Start"
                } else if (isTimerRunning && currentTime > 0) {
                    "Stop"
                } else {
                    "Restart"
                }
            )
        }
    }
}