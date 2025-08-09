package com.brenoedl.geradordesenhasemcompose.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brenoedl.geradordesenhasemcompose.ui.theme.Black
import com.brenoedl.geradordesenhasemcompose.ui.theme.DarkGray
import com.brenoedl.geradordesenhasemcompose.ui.theme.LightGray
import com.brenoedl.geradordesenhasemcompose.ui.theme.White
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home() {

    val arraySinbolos = arrayOf("!","$","%","(",")",",",":","<",">","/","]","~","[","@","?","|")
    val arrayLetrasMinusculas = arrayOf("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z")
    val arrayLetrasMaiusculas = arrayOf("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z")

    var password by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Gerador de Senhas em Compose")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = LightGray,
                    titleContentColor = Black
                )
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).background(color = White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    textStyle = TextStyle(
                        color = DarkGray,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    label = { Text(text = "Senha") },
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth(0.6f)
                )

                IconButton(
                    onClick = {
                        val numeros = Random.nextInt(100000, 999999)

                        password ="${arraySinbolos.random()}${arrayLetrasMinusculas.random()}" +
                                "$numeros" +
                                "${arrayLetrasMinusculas.random()}${arraySinbolos.random()}" +
                                "${arrayLetrasMaiusculas.random()}${arrayLetrasMinusculas.random()}"
                              },
                    modifier = Modifier.size(50.dp).border(
                        width = 1.dp,
                        color =DarkGray,
                        shape = RoundedCornerShape(10.dp)
                    )
                ){
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Gerar Senha",
                        tint = DarkGray
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home()
}