package com.brenoedl.testecomponentes

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brenoedl.testecomponentes.componentes.TextFieldCustom

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
    val email: MutableState<String> = remember { mutableStateOf("") }
    val senha: MutableState<String> = remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Login",
            modifier = Modifier.padding(30.dp),
            fontSize = 25.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        TextFieldCustom(
            value = email.value,
            onValueChange = {
                email.value = it
            },
            label = {
                Text(text = "Email")
            },
            modifier = Modifier.fillMaxWidth().padding(20.dp, 0.dp, 20.dp, 0.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        TextFieldCustom(
            value = senha.value,
            onValueChange = {
                senha.value = it
            },
            label = {
                Text(text = "Senha")
            },
            modifier = Modifier.fillMaxWidth().padding(20.dp, 16.dp, 20.dp, 0.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Button(
            onClick = {
                if (email.value.isNotEmpty() && senha.value.isNotEmpty()) {
                    Toast.makeText(context, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Logar",
                modifier = Modifier.padding(8.dp),
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home()
}