package com.brenoedl.conversordetemperaturaemcompose.navigation

sealed class View {
    data object Home : View()
    data object CeusiusFahrenheit : View()
    data object CeusiusKelvin : View()
    data object FahrenheitCeusius : View()
    data object FahrenheitKelvin : View()
    data object KelvinCeusius : View()
    data object KelvinFahrenheit : View()

}