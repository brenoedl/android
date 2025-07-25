package com.brenoedl.testecanvas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brenoedl.testecanvas.ui.theme.TesteCanvasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Home()
        }
    }
}

@Composable
fun Home() {
    Column(
        modifier = Modifier.fillMaxSize().background(
            color = Color.DarkGray
        ).padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Canvas(
            modifier = Modifier.fillMaxWidth().height(300.dp).background(Color.White)
        ) {
            drawRect(
                color = Color.Green,
                size = Size(400f, 200f),
                topLeft = center.copy(x = 280f, y = 310f)
            )
            drawCircle(
                color = Color.Blue,
                radius = 50f,
                center = center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home()
}