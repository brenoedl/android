package com.brenoedl.agendadecontatosemcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.brenoedl.agendadecontatosemcompose.vviews.AtualizarContato
import com.brenoedl.agendadecontatosemcompose.vviews.ListaContatos
import com.brenoedl.agendadecontatosemcompose.vviews.SalvarContato

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "ListaContatos") {
                composable("ListaContatos") {
                    ListaContatos(navController)
                }
                composable("SalvarContato") {
                    SalvarContato(navController)
                }
                composable(
                    route = "AtualizarContato/{id}/{nome}/{sobrenome}/{idade}/{celular}",
                    arguments = listOf(
                        navArgument("id"){},
                        navArgument("nome"){},
                        navArgument("sobrenome"){},
                        navArgument("idade"){},
                        navArgument("celular"){}
                    )
                    ) {
                    AtualizarContato(navController, it.arguments?.getString("id").toString(),  it.arguments?.getString("nome").toString(), it.arguments?.getString("sobrenome").toString(), it.arguments?.getString("idade").toString(), it.arguments?.getString("celular").toString())
                }
            }
        }
    }
}