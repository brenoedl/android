package com.brenoedl.agendadecontatosemcompose.itemlista

import android.app.AlertDialog
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.brenoedl.agendadecontatosemcompose.AppDatabase
import com.brenoedl.agendadecontatosemcompose.dao.ContatosDao
import com.brenoedl.agendadecontatosemcompose.model.Contatos
import com.brenoedl.agendadecontatosemcompose.ui.theme.White
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private lateinit var contatosDao: ContatosDao

@Composable
fun ContatoItem(contato: Contatos, navController: NavController, list: MutableList<Contatos>, index: Int){
    val id = contato.id
    val nome = contato.nome
    val sobrenome = contato.sobrenome
    val idade = contato.idade
    val celular = contato.celular
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    fun alertDeletar(){
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Deletar contato")
        alertDialog.setMessage("Deseja deletar esse contato?")
        alertDialog.setPositiveButton("Ok") { _, _ ->
            scope.launch(Dispatchers.IO){
                contatosDao = AppDatabase.getDatabase(context).contatosDao()
                contatosDao.deletarContatos(id)
                list.removeAt(index)
            }
            scope.launch(Dispatchers.Main){
                Toast.makeText(context, "Contato deletado com sucesso!", Toast.LENGTH_LONG).show()
                navController.navigate("ListaContatos")
            }
        }
        alertDialog.setNegativeButton("Cancelar") { _, _ ->
        }
        alertDialog.show()
    }

    Card(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = White,
            contentColor = White
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(20.dp)
        ) {
            Text(
                text = "Contato: $nome $sobrenome",
                fontSize = 18.sp,
                color = Color.Black
            )
            Text(
                text = "Idade: $idade",
                fontSize = 18.sp,
                color = Color.Black
            )
            Text(
                text = "Celular: $celular",
                fontSize = 18.sp,
                color = Color.Black
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = {
                        navController.navigate("AtualizarContato/$id/$nome/$sobrenome/$idade/$celular")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Icone de Editar",
                        tint = Color.Black
                    )
                }

                IconButton(
                    onClick = {
                        alertDeletar()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Icone de excluir",
                        tint = Color.Red
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ContatoItemPreview(){
    ContatoItem(
        contato = Contatos(
            nome = "Breno Emanuel",
            sobrenome = "Dantas de Lira",
            idade = "23",
            celular = "(11) 99999-9999"
        ),
        navController = NavController(LocalContext.current),
        list = mutableListOf(),
        index = 0
    )
}