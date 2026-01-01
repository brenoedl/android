package com.stackmobile.aplicativodefilmes.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stackmobile.aplicativodefilmes.model.Categoria
import com.stackmobile.aplicativodefilmes.repositorio.HomeRepositorio
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepositorio: HomeRepositorio): ViewModel() {
    fun getCateggooria(
        respostaDoServidor: (MutableList<Categoria>) -> Unit,
        respostaErro: (String) -> Unit
    ){
        viewModelScope.launch {
            homeRepositorio.getCateggooria(respostaDoServidor, respostaErro)
        }
    }
}