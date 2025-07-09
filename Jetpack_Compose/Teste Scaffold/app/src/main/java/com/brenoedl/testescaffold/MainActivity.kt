package com.brenoedl.testescaffold

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brenoedl.testescaffold.ui.theme.TesteScaffoldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Home()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Teste Scaffold",
                        modifier = Modifier.padding(10.dp),
                        color = Color.White
                    )
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue),
                actions = {
                    Icon(
                        imageVector = Icons.Rounded.Menu,
                        contentDescription = "Menu", tint = Color.White,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).background(Color.White).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Breno",
                fontSize = 30.sp
            )

            Text(
                text = "Elis",
                fontSize = 30.sp
            )

            Text(
                text = "Renam",
                fontSize = 30.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    TesteScaffoldTheme {
        Home()
    }
}