package com.brenoedl.calculadoradenotasemcompose.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.brenoedl.calculadoradenotasemcompose.componentes.TextFieldCustom
import com.brenoedl.calculadoradenotasemcompose.ui.theme.Blue
import com.brenoedl.calculadoradenotasemcompose.ui.theme.White

@SuppressLint("DefaultLocale")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home() {
    val textNota1 = remember { mutableStateOf("") }
    val textNota2 = remember { mutableStateOf("") }
    val textNota3 = remember { mutableStateOf("") }
    val textNota4 = remember { mutableStateOf("") }
    val textFaltas = remember { mutableStateOf("") }
    val erro = remember { mutableStateOf(false) }
    val mensagem = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Calculadora de Notas em Compose")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Blue,
                    titleContentColor = White
                )
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).background(White).verticalScroll(
                rememberScrollState()
            ),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextFieldCustom(
                    value = textNota1.value,
                    onValueChange = {
                        if (textNota1.value.length <= 4) {
                            textNota1.value = it
                        }
                    },
                    modifier = Modifier.padding(10.dp, 20.dp).fillMaxWidth(0.5f),
                    label = {
                        Text("Nota 1")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    isError = erro.value
                )

                TextFieldCustom(
                    value = textNota2.value,
                    onValueChange = {
                        if (textNota2.value.length <= 4) {
                            textNota2.value = it
                        }
                    },
                    modifier = Modifier.padding(10.dp, 20.dp).fillMaxWidth(),
                    label = {
                        Text("Nota 2")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    isError = erro.value
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextFieldCustom(
                    value = textNota3.value,
                    onValueChange = {
                        if (textNota3.value.length <= 4) {
                            textNota3.value = it
                        }
                    },
                    modifier = Modifier.padding(10.dp, 0.dp).fillMaxWidth(0.5f),
                    label = {
                        Text("Nota 3")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    isError = erro.value
                )

                TextFieldCustom(
                    value = textNota4.value,
                    onValueChange = {
                        if (textNota4.value.length <= 4) {
                            textNota4.value = it
                        }
                    },
                    modifier = Modifier.padding(10.dp, 0.dp).fillMaxWidth(),
                    label = {
                        Text("Nota 4")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    isError = erro.value
                )
            }

            TextFieldCustom(
                value = textFaltas.value,
                onValueChange = {
                    textFaltas.value = it
                },
                modifier = Modifier.padding(10.dp, 20.dp).fillMaxWidth(),
                label = {
                    Text("Faltas")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword
                ),
                isError = erro.value
            )

            Button(
                onClick = {
                    val nota1 = textNota1.value.replace(",", ".").toDoubleOrNull()
                    val nota2 = textNota2.value.replace(",", ".").toDoubleOrNull()
                    val nota3 = textNota3.value.replace(",", ".").toDoubleOrNull()
                    val nota4 = textNota4.value.replace(",", ".").toDoubleOrNull()
                    val faltas = textFaltas.value.toIntOrNull()

                    if (nota1 != null && nota2 != null && nota3 != null && nota4 != null && faltas != null) {
                        val media = (nota1 + nota2 + nota3 + nota4) / 4
                        val textMedia = String.format("%.1f", media)

                        erro.value = false
                        mensagem.value = "Média final do aluno: ${textMedia.replace(".", ",")}"

                        if (faltas <= 20) {
                            if (media < 5) {
                                mensagem.value += "\nAluno reprovado"
                            } else if (media < 7) {
                                mensagem.value += "\nAluno recuperação"
                            } else {
                                mensagem.value += "\nAluno aprovado"
                            }
                        } else {
                            mensagem.value += "\nAluno reprovado por faltas"
                        }
                    } else {
                        erro.value = true
                        mensagem.value = "Preencha todos os campos corretamente"
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                shape = RoundedCornerShape(10.dp),
                colors = buttonColors(
                    containerColor = Blue,
                    contentColor = White
                )
            ) {
                Text(
                    text =  "Calcular",
                    modifier = Modifier.padding(10.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            Text(
                text = mensagem.value,
                modifier = Modifier.padding(10.dp).fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home()
}