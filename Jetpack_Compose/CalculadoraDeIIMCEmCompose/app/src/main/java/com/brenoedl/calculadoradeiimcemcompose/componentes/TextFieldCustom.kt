package com.brenoedl.calculadoradeiimcemcompose.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.brenoedl.calculadoradeiimcemcompose.ui.theme.Blue
import com.brenoedl.calculadoradeiimcemcompose.ui.theme.White

@Composable
fun TextFieldCustom(
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable (() -> Unit)?,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    isError: Boolean = false
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        modifier = modifier,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = White,
            focusedContainerColor = White,
            focusedTextColor = Blue,
            unfocusedTextColor = Blue,
            unfocusedIndicatorColor = Blue,
            focusedIndicatorColor = Blue,
            focusedLabelColor = Blue,
            cursorColor = Blue
        ),
        keyboardOptions = keyboardOptions,
        isError = isError
    )
}

@Preview
@Composable
fun TextFieldCustomPreview() {
    val nome = remember { mutableStateOf("") }

    TextFieldCustom(
        value = nome.value,
        onValueChange = {
            nome.value = it
        },
        label = {
            Text(text = "Nome")
        },
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    )
}