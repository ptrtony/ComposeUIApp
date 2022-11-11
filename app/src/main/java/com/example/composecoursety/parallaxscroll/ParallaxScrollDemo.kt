package com.example.composecoursety.parallaxscroll

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.composecoursety.R

@Composable
fun ParallaxScrollScreen() {
    val moonScrollSpeed = 0.08f
    val midBigScrollSpeed = 0.03f
    val imageHeight = (LocalConfiguration.current.screenWidthDp * (2f / 3f)).dp
    val lazyListState = rememberLazyListState()
    var moonOffset by remember {
        mutableStateOf(0f)
    }
    var midBigOffset by remember {
        mutableStateOf(0f)
    }
    val nestedScrollConnection = object : NestedScrollConnection {
        override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
            val delta = available.y
            val layoutInfo = lazyListState.layoutInfo
            if (lazyListState.firstVisibleItemIndex == 0) {
                return Offset.Zero
            }

            if (layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1) {
                return Offset.Zero
            }
            moonOffset = delta * moonScrollSpeed
            midBigOffset = delta * midBigScrollSpeed
            return Offset.Zero
        }
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .nestedScroll(nestedScrollConnection),
        state = lazyListState
    ) {
        items(10) {
            Text(
                text = "Sample item",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)

            )
        }

        item {
            Box(
                modifier = Modifier
                    .clipToBounds()
                    .fillMaxWidth()
                    .height(imageHeight + midBigOffset.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color(0xFFF36B21),
                                Color(0xFFF9A521)
                            )
                        )
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_moonbg),
                    contentDescription = "moon",
                    contentScale = ContentScale.FillWidth,
                    alignment = Alignment.BottomCenter,
                    modifier = Modifier.matchParentSize()
                        .graphicsLayer {
                            translationY = moonOffset
                        }
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_midbg),
                    contentDescription = "moon",
                    contentScale = ContentScale.FillWidth,
                    alignment = Alignment.BottomCenter,
                    modifier = Modifier.matchParentSize()
                        .graphicsLayer {
                            translationY = moonOffset
                        }
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_outerbg),
                    contentDescription = "moon",
                    contentScale = ContentScale.FillWidth,
                    alignment = Alignment.BottomCenter,
                    modifier = Modifier.matchParentSize()
                        .graphicsLayer {
                            translationY = moonOffset
                        }
                )
            }
        }

        items(20) {
            Text(
                text = "Sample item",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)

            )
        }
    }
}