package com.example.composecoursety.launchedeffect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun LaunchedEffectFlowDemo(viewModel: LaunchedEffectViewModel) {
    LaunchedEffect(key1 = true) {
        viewModel.sharedFlow.collect { events ->
            when(events) {
                is LaunchedEffectViewModel.ScreenEvents.ShowSnackbar -> {

                }

                is LaunchedEffectViewModel.ScreenEvents.Navigate -> {

                }
            }
        }
    }
}