package com.example.composecoursety.performanceoptimization

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class PerformanceOptimizationViewModel : ViewModel() {

    var color by mutableStateOf(Color.Green)
        private set
    fun changeColor(it: Color) {
        this.color = it
    }
}