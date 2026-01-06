package com.brenoedl.conversordetemperaturaemcompose.views

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brenoedl.conversordetemperaturaemcompose.R
import com.brenoedl.conversordetemperaturaemcompose.componentes.ButtonCustom
import com.brenoedl.conversordetemperaturaemcompose.componentes.OutlinedTextFieldCustom
import com.brenoedl.conversordetemperaturaemcompose.navigation.View
import com.brenoedl.conversordetemperaturaemcompose.ui.theme.Blue
import com.brenoedl.conversordetemperaturaemcompose.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FahrenheitCeusius(backStack: SnapshotStateList<View>) {
    var fahrenheitString by remember { mutableStateOf("") }
    var fahrenheit by remember { mutableDoubleStateOf(0.0) }
    var isVasiu by remember { mutableStateOf(true) }
    var celsius by remember { mutableDoubleStateOf(0.0) }
    var formula by remember { mutableStateOf("") }
    val context = LocalContext.current
    val mensagem = stringResource(id = R.string.mendagem_erro_campo_vazio)
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.button__ceusus_fahrenheit),
                        color = White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Blue
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            backStack.removeLastOrNull()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = White
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).background(color = White),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextFieldCustom(
                value = fahrenheitString,
                onValueChange = {
                    fahrenheitString = it
                },
                label = { Text(text = stringResource(id = R.string.label_temperatura_fahrenheit))},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.fillMaxWidth().padding(16.dp, 16.dp, 16.dp, 8.dp)
            )

            ButtonCustom(
                onClick = {
                    if (fahrenheitString.isEmpty()){
                        isVasiu = true
                        Toast.makeText(context, mensagem, Toast.LENGTH_SHORT ).show()
                    }else {
                        isVasiu = false
                        fahrenheit = fahrenheitString.toDouble()
                        celsius = (fahrenheit - 32) * 5 / 9
                        formula = "(${String.format("%.2f",  fahrenheit)}°F - 32) * 5 / 9 = ${String.format("%.2f",  celsius)}°C"
                        keyboardController?.hide()
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(16.dp, 8.dp, 16.dp, 16.dp),
                texto = stringResource(id = R.string.button_calcular)
            )

            if (isVasiu){
                Text(
                    text = "",
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
            }else{
                Text(
                    text = stringResource(id = R.string.formula),
                    modifier = Modifier.fillMaxWidth().padding(16.dp, 16.dp, 16.dp, 8.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                Text(
                    text = formula,
                    modifier = Modifier.fillMaxWidth().padding(16.dp, 16.dp, 16.dp, 8.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )

                Text(
                    text = stringResource(id = R.string.sensacao),
                    modifier = Modifier.fillMaxWidth().padding(16.dp, 8.dp, 16.dp, 8.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                if (celsius <= 20.0){
                    Text(
                        text = stringResource(id = R.string.frio),
                        modifier = Modifier.fillMaxWidth().padding(16.dp, 8.dp, 16.dp, 8.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )

                    Image(
                        painter = painterResource(id = R.drawable.img_frio),
                        contentDescription = stringResource(id = R.string.imgFrio),
                        modifier = Modifier.size(200.dp).padding(16.dp, 8.dp, 16.dp, 8.dp)
                    )
                }else if (celsius <= 30.0){
                    Text(
                        text = stringResource(id = R.string.agradavel),
                        modifier = Modifier.fillMaxWidth().padding(16.dp, 8.dp, 16.dp, 8.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )

                    Image(
                        painter = painterResource(id = R.drawable.img_agradavel),
                        contentDescription = stringResource(id = R.string.imgAgradavel),
                        modifier = Modifier.size(200.dp).padding(16.dp, 8.dp, 16.dp, 8.dp)
                    )
                }else{
                    Text(
                        text = stringResource(id = R.string.calor),
                        modifier = Modifier.fillMaxWidth().padding(16.dp, 8.dp, 16.dp, 8.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )

                    Image(
                        painter = painterResource(id = R.drawable.img_calor),
                        contentDescription = stringResource(id = R.string.imgCalor),
                        modifier = Modifier.size(200.dp).padding(16.dp, 8.dp, 16.dp, 8.dp)
                    )
                }
            }
        }
    }
}