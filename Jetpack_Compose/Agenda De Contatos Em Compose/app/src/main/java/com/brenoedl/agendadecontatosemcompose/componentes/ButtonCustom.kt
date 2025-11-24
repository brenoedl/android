package com.brenoedl.agendadecontatosemcompose.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import  androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brenoedl.agendadecontatosemcompose.ui.theme.Purple500
import com.brenoedl.agendadecontatosemcompose.ui.theme.White

@Composable
fun ButtonCustom(
    onClick: () -> Unit,
    tezto: String = ""
) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().padding(20.dp).background(
            color = Purple500,
            shape = RoundedCornerShape(10.dp)
        )
    ){
        Text(
            text = tezto,
            fontSize = 18.sp,
            color = White
        )
    }
}