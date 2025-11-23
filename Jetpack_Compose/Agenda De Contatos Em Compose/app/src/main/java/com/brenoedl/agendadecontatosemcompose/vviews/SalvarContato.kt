package com.brenoedl.agendadecontatosemcompose.vviews

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.brenoedl.agendadecontatosemcompose.AppDatabase
import com.brenoedl.agendadecontatosemcompose.componentes.ButtonCustom
import com.brenoedl.agendadecontatosemcompose.componentes.OutlinedTextFieldCustom
import com.brenoedl.agendadecontatosemcompose.dao.ContatosDao
import com.brenoedl.agendadecontatosemcompose.model.Contatos
import com.brenoedl.agendadecontatosemcompose.ui.theme.Purple500
import com.brenoedl.agendadecontatosemcompose.ui.theme.White
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private lateinit var contatosDao: ContatosDao

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalvarContato(navController: NavController) {
    var nome by remember { mutableStateOf("") }
    var sobrenome by remember { mutableStateOf("") }
    var idade by remember { mutableStateOf("") }
    var celular by remember { mutableStateOf("") }
    var mensagem by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Salvar novo contato",
                        color = White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Purple500
                )
            )
        }
    ){ paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).background(color = White).verticalScroll(
                rememberScrollState()
            ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextFieldCustom(
                value = nome,
                label = {
                    Text(text = "Nome")
                },
                onValueChange = {
                    nome = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth().padding(start = 20.dp, top = 0.dp, end = 20.dp, bottom = 10.dp)
            )

            OutlinedTextFieldCustom(
                value = sobrenome,
                label = {
                    Text(text = "Sobrenome")
                },
                onValueChange = {
                    sobrenome = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth().padding(start = 20.dp, top = 0.dp, end = 20.dp, bottom = 10.dp)
            )

            OutlinedTextFieldCustom(
                value = idade,
                label = {
                    Text(text = "Idade")
                },
                onValueChange = {
                    idade = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                modifier = Modifier.fillMaxWidth().padding(start = 20.dp, top = 0.dp, end = 20.dp, bottom = 10.dp)
            )

            OutlinedTextFieldCustom(
                value = celular,
                label = {
                    Text(text = "Celular")
                },
                onValueChange = {
                    celular = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth().padding(start = 20.dp, top = 0.dp, end = 20.dp, bottom = 10.dp)
            )

            ButtonCustom(
                onClick = {
                    scope.launch(Dispatchers.IO){
                        if (nome.isNotEmpty() && sobrenome.isNotEmpty() && idade.isNotEmpty() && celular.isNotEmpty()){
                            mensagem = true

                            val contato = Contatos(nome, sobrenome, idade, celular)
                            contatosDao = AppDatabase.getDatabase(context).contatosDao()
                            contatosDao.salvarContatos(contato)
                        }else{
                            mensagem = false
                        }
                    }
                    scope.launch(Dispatchers.Main){
                        if (mensagem){
                            Toast.makeText(context, "Contato salvo com sucesso!", Toast.LENGTH_SHORT).show()
                            navController.navigate("ListaContatos")
                        }else{
                            Toast.makeText(context, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                tezto = "Salvar"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SalvarContatoPreview() {
    SalvarContato(navController = rememberNavController())
}