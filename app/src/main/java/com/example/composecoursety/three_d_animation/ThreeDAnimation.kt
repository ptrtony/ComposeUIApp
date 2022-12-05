package com.example.composecoursety.three_d_animation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ThreeDAnimationMain() {
    Surface(
        color = Color(0xFF010101),
        modifier = Modifier
            .fillMaxSize()
    ) {
        DownUp(text = "Test", modifier = Modifier.padding(15.dp)) {
            Text(
                modifier = Modifier.align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.Green),
                text = "Hello world!",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
            )
        }
    }
}


@Composable
fun DownUp(
    text: String,
    initiallyOpen: Boolean = true,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    var isOpen by remember {
        mutableStateOf(initiallyOpen)
    }
    val alpha = animateFloatAsState(targetValue = if (isOpen) 1f else 0f,
    animationSpec = tween(durationMillis = 300, easing = LinearEasing))

    val rotateX = animateFloatAsState(targetValue = if (isOpen) 0f else -90f,
    animationSpec = tween(durationMillis = 300, easing = LinearEasing))
    Column(modifier = modifier
        .fillMaxWidth()) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White)

            Icon(imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Open or close the drop down value",
                tint = Color.White,
                modifier = Modifier
                    .clickable {
                        isOpen = !isOpen
                    }
                    .scale(1f, if (isOpen) -1f else 1f)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Box(modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                transformOrigin = TransformOrigin(0.5f, 0f)
                rotationX = rotateX.value
            }
            .alpha(alpha.value)
        ) {
            content()
        }
    }
}