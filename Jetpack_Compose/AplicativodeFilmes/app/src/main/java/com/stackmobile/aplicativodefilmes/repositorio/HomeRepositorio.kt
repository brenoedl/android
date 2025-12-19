package com.stackmobile.aplicativodefilmes.repositorio

import com.stackmobile.aplicativodefilmes.servidor.Servidor
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class HomeRepositorio @Inject constructor(private val servidor: Servidor){

}