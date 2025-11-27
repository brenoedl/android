package com.brenoedl.buscadordecep.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brenoedl.buscadordecep.repositorio.Repositorio
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repositorio: Repositorio) : ViewModel() {
    fun buscarCep(
        cep: String,
        respostaServidor: (String, String, String, String) -> Unit
    ) {
        viewModelScope.launch {
            repositorio.buscarCep(cep, respostaServidor)
        }
    }
}