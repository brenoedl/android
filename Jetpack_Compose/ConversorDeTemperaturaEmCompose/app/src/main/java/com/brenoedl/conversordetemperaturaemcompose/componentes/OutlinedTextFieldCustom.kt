package com.brenoedl.conversordetemperaturaemcompose.componentes

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.brenoedl.conversordetemperaturaemcompose.ui.theme.Blue

@Composable
fun OutlinedTextFieldCustom(
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable () -> Unit,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        keyboardOptions = keyboardOptions,
        modifier = modifier,
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = Blue,
            focusedBorderColor = Blue,
            focusedLabelColor = Blue
        ),
        maxLines = 1
    )
}

@Preview(showBackground = true)
@Composable
fun OutlinedTextFieldCustomPreview(){
    OutlinedTextFieldCustom(
        value = "",
        onValueChange = {},
        label = { Text("Temperatura em °C") },
        keyboardOptions = KeyboardOptions.Default
    )
}