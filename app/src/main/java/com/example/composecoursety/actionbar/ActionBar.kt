package com.example.composecoursety.actionbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ActionBar(item: ActionBarItem, navHostController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = item.backIcon),
            contentDescription = "back",
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .clickable {
                    navHostController.popBackStack()
                },
            alignment = Alignment.CenterStart
        )

        Text(
            text = item.title ?: "",
            modifier = Modifier.weight(2f),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun ComposeUIContainer(title: String?, navHostController: NavHostController, content: @Composable () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        val item = ActionBarItem(title = title)
        ActionBar(item = item, navHostController = navHostController)
        Divider()
        content()
    }
}