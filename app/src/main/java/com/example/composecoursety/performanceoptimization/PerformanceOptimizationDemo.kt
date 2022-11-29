package com.example.composecoursety.performanceoptimization

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RgbSelectorDemo() {
    val viewModel = viewModel<PerformanceOptimizationViewModel>()
    val changeColorLambada = remember<(Color) -> Unit> {
        {
            viewModel.changeColor(it)
        }
    }
    RgbSelector(viewModel.color, onColorSelector = changeColorLambada, modifier = Modifier
        .fillMaxSize()
        .padding(top = 100.dp))
}

@Composable
fun RgbSelector(
    color: Color,
    onColorSelector: (Color) -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier =
            Modifier
                .size(100.dp)
                .background(color)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
             Button(onClick = {
                 onColorSelector(Color.Red)
             }) {
                 Text(text = "Red")
             }

            Button(onClick = {
                onColorSelector(Color.Green)
            }) {
                Text(text = "Green")
            }

            Button(onClick = {
                onColorSelector(Color.Blue)
            }) {
                Text(text = "Blue")
            }
        }
    }
}