package com.example.composecoursety.allscreensize

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.composecoursety.actionbar.ComposeUIContainer


@Composable
fun AllScreenDemo(title: String?, navHostController: NavHostController) {
    ComposeUIContainer(title = title, navHostController) {
        val rememberWindowInfo = rememberWindowInfo()
        if (rememberWindowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(10) {
                    Text(
                        text = "Item $it",
                        fontSize = 25.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Cyan)
                            .padding(16.dp)
                    )
                }
                items(10) {
                    Text(
                        text = "Item $it",
                        fontSize = 25.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Green)
                            .padding(16.dp)
                    )
                }
            }
        } else {
            Row(modifier = Modifier.fillMaxWidth()) {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(10) {
                        Text(
                            text = "Item $it",
                            fontSize = 25.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Green)
                                .padding(16.dp)
                        )
                    }
                }
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(10) {
                        Text(
                            text = "Item $it",
                            fontSize = 25.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Cyan)
                                .padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}