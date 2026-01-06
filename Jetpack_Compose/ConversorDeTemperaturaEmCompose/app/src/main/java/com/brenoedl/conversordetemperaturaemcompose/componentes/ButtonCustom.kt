package com.brenoedl.conversordetemperaturaemcompose.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brenoedl.conversordetemperaturaemcompose.R
import com.brenoedl.conversordetemperaturaemcompose.ui.theme.Blue
import com.brenoedl.conversordetemperaturaemcompose.ui.theme.White

@Composable
fun ButtonCustom(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    texto: String = ""
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue
        )
    ){
        Text(
            text = texto,
            modifier = Modifier.padding(5.dp),
            fontSize = 18.sp,
            color = White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonCustomPreview() {
    ButtonCustom(
        onClick = { /*TODO*/ },
        modifier = Modifier.width(300.dp).padding(0.dp),
        texto = stringResource(R.string.button__ceusus_fahrenheit)

    )
}