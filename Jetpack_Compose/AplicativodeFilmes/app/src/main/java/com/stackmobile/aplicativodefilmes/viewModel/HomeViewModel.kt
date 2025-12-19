package com.stackmobile.aplicativodefilmes.viewModel

import androidx.lifecycle.ViewModel
import com.stackmobile.aplicativodefilmes.repositorio.HomeRepositorio
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepositorio: HomeRepositorio): ViewModel() {

}