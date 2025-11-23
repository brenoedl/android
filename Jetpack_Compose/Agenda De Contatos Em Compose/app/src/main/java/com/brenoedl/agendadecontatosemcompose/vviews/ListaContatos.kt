package com.brenoedl.agendadecontatosemcompose.vviews

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.brenoedl.agendadecontatosemcompose.AppDatabase
import com.brenoedl.agendadecontatosemcompose.dao.ContatosDao
import com.brenoedl.agendadecontatosemcompose.itemlista.ContatoItem
import com.brenoedl.agendadecontatosemcompose.model.Contatos
import com.brenoedl.agendadecontatosemcompose.ui.theme.Purple500
import com.brenoedl.agendadecontatosemcompose.ui.theme.White
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private lateinit var contatosDao: ContatosDao

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaContatos(navController: NavController) {
    val contatosLista by remember { mutableStateOf<MutableList<Contatos>>(mutableListOf()) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    scope.launch(Dispatchers.IO) {
        contatosDao = AppDatabase.getDatabase(context).contatosDao()
        val contatos = contatosDao.getContatos()

        for (contato in contatos) {
            contatosLista.add(contato)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Agenda de Contatos",
                        color = White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Purple500
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("SalvarContato")
                },
                containerColor = Purple500,
                modifier = Modifier.clip(RoundedCornerShape(15.dp)),
                contentColor = White
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Icone de Adicionar novo contato"
                )
            }
        }

    ){ paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = Color.LightGray)
        ) {
            LazyColumn(
                modifier = Modifier.padding(10.dp)
            ) {
                itemsIndexed(contatosLista) { index, conato ->
                    ContatoItem(contato = conato, navController = navController, list = contatosLista, index = index)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ListaContatosPreview() {
    ListaContatos(navController = rememberNavController())
}