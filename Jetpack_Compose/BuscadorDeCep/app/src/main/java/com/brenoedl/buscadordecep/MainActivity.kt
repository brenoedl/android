package com.brenoedl.buscadordecep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.brenoedl.buscadordecep.view.Home
import com.brenoedl.buscadordecep.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController: NavHostController = rememberNavController()
            val viewModel: HomeViewModel = hiltViewModel()

            NavHost(
                navController = navController,
                startDestination = "Home"
            ) {
                composable(route = "Home") {
                    Home(
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}
