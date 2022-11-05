package com.example.composecoursety.drag

import android.util.Log
import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import com.example.composecoursety.R
import java.util.logging.Logger
import kotlin.math.PI
import kotlin.math.atan2

@ExperimentalComposeUiApi
@Composable
fun MusicKnob(
    modifier: Modifier = Modifier,
    limitAngle: Float = 25f,
    onValueChange: (Float) -> Unit
) {
    val rotate = remember {
        mutableStateOf(limitAngle)
    }
    val touchX = remember {
        mutableStateOf(0f)
    }
    val touchY = remember {
        mutableStateOf(0f)
    }
    val centerX = remember {
        mutableStateOf(0f)
    }
    val centerY = remember {
        mutableStateOf(0f)
    }
    Image(painter = painterResource(id = R.drawable.music),
        contentDescription = "Music knob",
    modifier = modifier
        .fillMaxSize()
        .onGloballyPositioned {
            val windowBounds = it.boundsInWindow()
            centerX.value = windowBounds.size.width / 2f
            centerY.value = windowBounds.size.height / 2f
        }
        .pointerInteropFilter { event ->
            touchX.value = event.x
            touchY.value = event.y
            val angle = -atan2(centerX.value - touchX.value, centerY.value - touchY.value) * 180f / PI
            Log.d("xxx", "angle :$angle event.action ${event.action}")
            when (event.action) {
                MotionEvent.ACTION_MOVE -> {
                    if (angle !in -limitAngle..limitAngle) {
                        val fixedAngle = if (angle in -180f..-limitAngle) {
                            360f + angle
                        } else {
                            angle
                        }
                        rotate.value = fixedAngle.toFloat()
                        val percent = (fixedAngle - limitAngle) / (360f - 2 * limitAngle)
                        onValueChange(percent.toFloat())
                        Log.d("xxx",  "percent :$percent angle :$angle")
                        return@pointerInteropFilter true
                    } else {
                        Log.d("xxx", "percent :$0 false")
                        return@pointerInteropFilter false
                    }
                }

                else -> {
                    return@pointerInteropFilter false
                }
            }
        }
        .rotate(rotate.value)
    )
}

@Composable
fun VolumeBox(modifier: Modifier,
    barCount: Int = 10,
    actionBar: Int) {
    BoxWithConstraints(contentAlignment = Alignment.Center, modifier = modifier) {
        val barWidth = remember {
            mutableStateOf(0f)
        }
        barWidth.value = constraints.maxWidth / (2f * barCount)
        Canvas(modifier = modifier) {
            for (count in 0 until barCount) {
                drawRoundRect(color = if (count in 0..actionBar) Color.Green else Color.Gray,
                topLeft = Offset(count * 2f * barWidth.value + barWidth.value / 2f, 0f),
                size = Size(barWidth.value, constraints.maxHeight.toFloat()))
            }
        }
    }
}