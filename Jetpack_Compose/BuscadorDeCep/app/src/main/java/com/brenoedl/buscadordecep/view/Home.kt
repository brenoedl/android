package com.brenoedl.buscadordecep.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.brenoedl.buscadordecep.componentes.ButtonCustom
import com.brenoedl.buscadordecep.componentes.OutlinedTextFieldCustom
import com.brenoedl.buscadordecep.ui.theme.Teal700
import com.brenoedl.buscadordecep.ui.theme.White
import com.brenoedl.buscadordecep.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    viewModel: HomeViewModel = hiltViewModel()
){
    var cepInput by remember { mutableStateOf("") }
    var logradouroInput by remember { mutableStateOf("") }
    var bairroInput by remember { mutableStateOf("") }
    var cidadeInput by remember { mutableStateOf("") }
    var estadoInput by remember { mutableStateOf("") }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Buscador de CEP",
                        fontSize = 16.sp
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Teal700,
                    titleContentColor = White
                )
            )
        }
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .padding(paddingValue)
                .fillMaxSize()
                .background(White)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextFieldCustom(
                    value = cepInput,
                    onValueChange = {
                        cepInput = it
                    },
                    label = {
                        Text("Cep")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword
                    ),
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(0.5f)
                )

                ButtonCustom(
                    onClick = {
                        if (cepInput.isNotEmpty()) {
                            viewModel.buscarCep(cepInput) { logradouro, bairro, cidade, estado ->
                                logradouroInput = logradouro
                                bairroInput = bairro
                                cidadeInput = cidade
                                estadoInput = estado
                            }
                        }else{
                            Toast.makeText(context, "Digite um cep válido", Toast.LENGTH_SHORT).show()
                        }
                    },
                    tezto = "Buscar cep",
                    modifier = Modifier
                        .padding(0.dp, 18.dp, 16.dp, 0.dp)
                        .fillMaxWidth()
                        .background(
                            color = White
                        )
                )
            }

            OutlinedTextFieldCustom(
                value = logradouroInput,
                onValueChange = {
                    logradouroInput = it
                },
                label = { Text("Logradouro") },
                keyboardOptions = KeyboardOptions.Default,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )

            OutlinedTextFieldCustom(
                value = bairroInput,
                onValueChange = {
                    bairroInput = it

                },
                label = { Text("Bairro")},
                keyboardOptions = KeyboardOptions.Default,
                modifier = Modifier
                    .padding(16.dp, 0.dp, 16.dp, 16.dp)
                    .fillMaxWidth()
            )

            OutlinedTextFieldCustom(
                value = cidadeInput,
                onValueChange = {
                    cidadeInput = it
                },
                label = { Text("Cidade")},
                keyboardOptions = KeyboardOptions.Default,
                modifier = Modifier
                    .padding(16.dp, 0.dp, 16.dp, 16.dp)
                    .fillMaxWidth()
            )

            OutlinedTextFieldCustom(
                value =estadoInput,
                onValueChange = {
                    estadoInput = it
                },
                label = { Text("Estado")},
                keyboardOptions = KeyboardOptions.Default,
                modifier = Modifier
                    .padding(16.dp, 0.dp, 16.dp, 16.dp)
                    .fillMaxWidth()
            )

            ButtonCustom(
                onClick = {
                    cepInput = ""
                    logradouroInput = ""
                    bairroInput = ""
                    cidadeInput = ""
                    estadoInput = ""
                },
                tezto = "Limpar",
                modifier = Modifier
                    .padding(16.dp, 0.dp, 0.dp, 16.dp)
            )
        }
    }
}