package com.brenoedl.testecolumrow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brenoedl.testecolumrow.ui.theme.TesteColumRowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TesteColumRowTheme {
                Home()
            }
        }
    }
}

@Composable
fun Home() {
    Column(modifier = Modifier.fillMaxSize().background(color = Color.White), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        Surface(modifier = Modifier.size(100.dp), color = Color.Blue) {}
        Surface(modifier = Modifier.size(100.dp), color = Color.Red) {}
        Surface(modifier = Modifier.size(100.dp), color = Color.Green) {}
    }
    Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        Surface(modifier = Modifier.size(100.dp), color = Color.Blue) {}
        Surface(modifier = Modifier.size(100.dp), color = Color.Red) {}
        Surface(modifier = Modifier.size(100.dp), color = Color.Green) {}
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    TesteColumRowTheme {
        Home()
    }
}