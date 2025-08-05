package com.brenoedl.calculadoradeiimcemcompose.veiw

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brenoedl.calculadoradeiimcemcompose.componentes.TextFieldCustom
import com.brenoedl.calculadoradeiimcemcompose.ui.theme.Blue
import com.brenoedl.calculadoradeiimcemcompose.ui.theme.White


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home() {
    val altura = remember { mutableStateOf("") }
    val peso = remember { mutableStateOf("") }
    val mensagem = remember { mutableStateOf("") }
    val erro = remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Calculadora de IMC em Compose")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Blue,
                    titleContentColor = White
                )
            )
        }
    ) {padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).background(
                color = White
            ).verticalScroll(rememberScrollState())
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Altura (cm)",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(0.dp, 100.dp, 0.dp, 0.dp)
                )

                Text(
                    text = "Peso (Kg)",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(0.dp, 100.dp, 0.dp, 0.dp),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TextFieldCustom(
                    value = altura.value,
                    onValueChange = {
                        if (it.length <= 3){
                            altura.value = it
                        }
                    },
                    label = {
                        Text("Ex: 180")
                    },
                    modifier = Modifier.width(150.dp).padding(0.dp, 0.dp, 0.dp, 20.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    isError = erro.value
                )

                TextFieldCustom(
                    value = peso.value,
                    onValueChange = {
                        if (it.length <= 7) {
                            peso.value = it
                        }
                    },
                    label = {
                        Text("Ex: 80,63")
                    },
                    modifier = Modifier.width(150.dp).padding(0.dp, 0.dp, 0.dp, 20.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    isError = erro.value
                )
            }

            Button(
                onClick = {

                    if (altura.value.isNotEmpty() && peso.value.isNotEmpty()) {
                        val pesoFormatado = peso.value.replace(",", ".").toDoubleOrNull()
                        val alturaFormatada = altura.value.toDoubleOrNull()

                        if (pesoFormatado != null && alturaFormatada != null) {
                            val imc = pesoFormatado / ((alturaFormatada / 100) * (alturaFormatada / 100))

                            erro.value = false
                            mensagem.value = "IMC: %.2f".format(imc)

                            when {
                                imc < 18.5 -> mensagem.value += "\nAbaixo do peso"
                                imc in 18.5 .. 24.9 -> mensagem.value += "\nPeso normal"
                                imc in 25.0 .. 29.9 -> mensagem.value += "\nSobrepeso"
                                imc in 30.0 .. 34.9 -> mensagem.value += "\nObesidade (grau 1)"
                                imc in 35.0 .. 39.9 -> mensagem.value += "\nObesidade severa (grau 2)"
                                else -> mensagem.value += "\nObesidade morbida (grau 3)"
                            }

                            focusManager.clearFocus()

                        } else {
                            erro.value = true
                            mensagem.value = "Altura ou peso inválidos"
                        }

                    } else {
                        erro.value = true
                        mensagem.value = "Preencha todos os campos"
                    }
                },
                modifier = Modifier.fillMaxWidth().height(150.dp).padding(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue
                )
            ) {
                Text(
                    text = "CALCULAR",
                    fontSize = 18.sp,
                    color = White,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = mensagem.value,
                fontSize = 18.sp,
                color = Blue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth().padding(10.dp),
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