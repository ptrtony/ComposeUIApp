package com.example.composecoursety

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecoursety.drag.MusicKnob
import com.example.composecoursety.drag.VolumeBox
import com.example.composecoursety.ui.theme.ComposeCourseTYTheme
import kotlin.math.roundToInt
import kotlin.random.Random

@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fontFamily = FontFamily(
            Font(R.font.merriweather_italic, FontWeight.Thin),
            Font(R.font.merriweather_black, FontWeight.Black),
            Font(R.font.merriweather_bold, FontWeight.Bold),
            Font(R.font.merriweather_light, FontWeight.Light),
            Font(R.font.merriweather_regular, FontWeight.Normal)
        )
        setContent {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                , contentAlignment = Alignment.Center) {
                /*CircleProgress(0.8f, 100)*/
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .border(
                        5.dp,
                        color = Color.Green,
                        shape = RoundedCornerShape(corner = CornerSize(5.dp))
                    ).padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start) {
                    val percent = remember {
                        mutableStateOf(0f)
                    }
                    MusicKnob(modifier = Modifier.size(100.dp)) {
                        percent.value = it
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    VolumeBox(modifier = Modifier.height(100.dp), actionBar = (percent.value * 10).roundToInt())
                }
            }
        }
    }

    @Composable
    fun LazyListItemDemo() {
        LazyColumn(content = {
            items(500) {
                Text(text = "Item $it",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp))
            }
        })
    }

    @Composable
    fun ListItemDemo() {
        val scrollState = rememberScrollState()
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            for (i in 0..100) {
                Text(text = "Item $i",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp))
            }
        }
    }


    @Composable
    fun ScaffoldDemo() {
        var textFieldState by remember {
            mutableStateOf("")
        }
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
            TextField(value = textFieldState, onValueChange = {
                textFieldState = it
            },
            label = {
                Text(text = "Pls enter your message")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true)
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                Toast.makeText(this@MainActivity, "me:$textFieldState", Toast.LENGTH_SHORT).show()
            }) {
                Text(text = "send your message")
            }
        }
    }
    
    @Composable
    fun ColorBoxDemo() {
        val color = remember {
            mutableStateOf(Color.Yellow)
        }
        Column {
            ColorBox(modifier = Modifier
                .fillMaxSize()
                .weight(1f)) {
                color.value = it
            }

            Box(modifier = Modifier
                .fillMaxSize()
                .fillMaxSize()
                .weight(1f)
                .background(color = color.value)) {
            }
        }
    }

    @SuppressLint("RememberReturnType")
    @Composable
    fun ColorBox(modifier: Modifier = Modifier, updateColor:(Color) -> Unit) {
        Box(modifier = modifier
            .background(Color.Red)
            .clickable {
                updateColor(
                    Color(
                        Random.nextFloat(),
                        Random.nextFloat(),
                        Random.nextFloat(),
                        1f
                    )
                )
            }) {

        }
    }

    @Composable
    fun TextAlignAndSpanDemo(fontFamily: FontFamily) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Green,
                            fontSize = 50.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("J")
                    }
                    append("etpack")
                    withStyle(
                        style = SpanStyle(
                            color = Color.Green,
                            fontSize = 50.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("C")
                    }
                    append("ompose")
                },
                fontSize = 23.sp,
                fontFamily = fontFamily,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center,
                textDecoration = TextDecoration.Underline
            )
        }
    }


    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello $name!")
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ComposeCourseTYTheme {
            Greeting("Android")
        }
    }
}