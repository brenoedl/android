package com.brenoedl.testecomponentes.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldCustom(
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable (() -> Unit)?,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions()
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        modifier = modifier,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            focusedTextColor = Color.Blue,
            unfocusedTextColor = Color.Blue,
            unfocusedIndicatorColor = Color.Blue,
            focusedIndicatorColor = Color.Blue,
            unfocusedLabelColor = Color.Blue,
            focusedLabelColor = Color.Blue
        ),
        keyboardOptions = keyboardOptions
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