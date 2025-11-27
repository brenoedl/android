package com.brenoedl.buscadordecep.repositorio

import com.brenoedl.buscadordecep.datasource.Servidor
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class Repositorio @Inject constructor(private val servidor: Servidor) {
    suspend fun buscarCep(
        cep: String,
        respostaServidor: (String, String, String, String) -> Unit
    ) {
        servidor.buscarCep(cep, respostaServidor)
    }
}