package com.brenoedl.conversordetemperaturaemcompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.brenoedl.conversordetemperaturaemcompose.views.CeusiusFahrenheit
import com.brenoedl.conversordetemperaturaemcompose.views.CeusiusKelvin
import com.brenoedl.conversordetemperaturaemcompose.views.FahrenheitCeusius
import com.brenoedl.conversordetemperaturaemcompose.views.FahrenheitKelvin
import com.brenoedl.conversordetemperaturaemcompose.views.Home
import com.brenoedl.conversordetemperaturaemcompose.views.KelvinCeusius
import com.brenoedl.conversordetemperaturaemcompose.views.KelvinFahrenheit

@Composable
fun SetupNavigation(){
    val backStack = remember { mutableStateListOf<View>(View.Home) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<View.Home>{
                Home(backStack)
            }
            entry<View.CeusiusFahrenheit>{
                CeusiusFahrenheit(backStack)
            }
            entry<View.CeusiusKelvin>{
                CeusiusKelvin(backStack)
            }
            entry<View.FahrenheitCeusius>{
                FahrenheitCeusius(backStack)
            }
            entry<View.FahrenheitKelvin>{
                FahrenheitKelvin(backStack)
            }
            entry<View.KelvinCeusius>{
                KelvinCeusius(backStack)
            }
            entry<View.KelvinFahrenheit>{
                KelvinFahrenheit(backStack)
            }
        }
    )
}