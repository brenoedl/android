package com.brenoedl.conversordetemperaturaemcompose.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brenoedl.conversordetemperaturaemcompose.R
import com.brenoedl.conversordetemperaturaemcompose.componentes.ButtonCustom
import com.brenoedl.conversordetemperaturaemcompose.navigation.View
import com.brenoedl.conversordetemperaturaemcompose.ui.theme.Blue
import com.brenoedl.conversordetemperaturaemcompose.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(backStack: SnapshotStateList<View>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        fontSize = 18.sp,
                        color = White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Blue
                )
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).background(color = White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ButtonCustom(
                onClick = {
                    backStack.add(View.CeusiusFahrenheit)
                },
                modifier = Modifier.padding(0.dp, 5.dp).width(300.dp),
                texto = stringResource(R.string.button__ceusus_fahrenheit)
            )

            ButtonCustom(
                onClick = {
                    backStack.add(View.CeusiusKelvin)
                },
                modifier = Modifier.padding(0.dp, 5.dp).width(300.dp),
                texto = stringResource(R.string.button__ceusus_kelvin)
            )

            ButtonCustom(
                onClick = {
                    backStack.add(View.FahrenheitCeusius)
                },
                modifier = Modifier.padding(0.dp, 5.dp).width(300.dp),
                texto = stringResource(R.string.button__fahrenheit_ceusus)
            )
            ButtonCustom(
                onClick = {
                    backStack.add(View.FahrenheitKelvin)
                },
                modifier = Modifier.padding(0.dp, 5.dp).width(300.dp),
                texto = stringResource(R.string.button__fahrenheit_kelvin)
            )

            ButtonCustom(
                onClick = {
                    backStack.add(View.KelvinCeusius)
                },
                modifier = Modifier.padding(0.dp, 5.dp).width(300.dp),
                texto = stringResource(R.string.button__kelvin_ceusus)
            )

            ButtonCustom(
                onClick = {
                    backStack.add(View.KelvinFahrenheit)
                },
                modifier = Modifier.padding(0.dp, 5.dp).width(300.dp),
                texto = stringResource(R.string.button__kelvin_fahrenheit)
            )
        }
    }
}