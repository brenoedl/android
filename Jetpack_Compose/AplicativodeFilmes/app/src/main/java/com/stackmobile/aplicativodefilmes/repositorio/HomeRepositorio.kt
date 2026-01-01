package com.stackmobile.aplicativodefilmes.repositorio

import com.stackmobile.aplicativodefilmes.model.Categoria
import com.stackmobile.aplicativodefilmes.servidor.Servidor
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class HomeRepositorio @Inject constructor(private val servidor: Servidor){
    suspend fun getCateggooria(
        respostaDoServidor: (MutableList<Categoria>) -> Unit,
        respostaErro: (String) -> Unit
    ){
        servidor.getCateggooria(respostaDoServidor, respostaErro)
    }
}