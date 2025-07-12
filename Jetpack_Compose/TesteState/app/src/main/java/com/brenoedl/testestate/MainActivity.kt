package com.brenoedl.testestate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brenoedl.testestate.ui.theme.TesteStateTheme

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
    var frase = remember { mutableStateOf("Aperte o botão abaixo") }
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = frase.value,
            fontSize = 18.sp,
            color = Color.Black
        )

        Button(
            onClick = {
                frase.value = "Olá, Mundo!"
            },
            modifier = Modifier.padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(Color.Blue)
        ) {
            Text(text = "Clique aqui")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home()
}