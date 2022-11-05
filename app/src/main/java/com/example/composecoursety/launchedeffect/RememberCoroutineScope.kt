package com.example.composecoursety.launchedeffect

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RememberCoroutineScope() {
    val scope = rememberCoroutineScope()
    Button(onClick = {
        scope.launch { 
            delay(1000)
            println("Hello World!")
        }
    }) {
        Text(text = "Print Hello world")
    }
}