package com.example.composecoursety.launchedeffect

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*

@Composable
fun DerivedStateOfDemo() {
    var counter by remember {
        mutableStateOf(0)
    }
    val counterText by derivedStateOf {
        "The counter is $counter"
    }
    Button(onClick = { counter ++ }) {
        Text(text = counterText)
    }
}