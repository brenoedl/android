package com.brenoedl.agendadecontatos.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.brenoedl.agendadecontatos.database.AppDatabase
import com.brenoedl.agendadecontatos.model.Contato
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ContatoViewModel(application: Application) : AndroidViewModel(application) {

    private val contatoDao = AppDatabase.getIntance(application).contatoDao()
    val todosContatos: Flow<List<Contato>> = contatoDao.obterTodos()

    fun inserir(contato: Contato) {
        viewModelScope.launch {
            contatoDao.inserir(contato)
        }
    }

    fun excluir(contato: Contato) {
        viewModelScope.launch {
            contatoDao.deletar(contato)
        }
    }

    // Função para obter um contato por ID (para edição)
    fun obterPorId(id: Int): Flow<Contato?> {
        return contatoDao.obterPorId(id)
    }

    // Função para atualizar um contato
    fun atualizar(contato: Contato) {
        viewModelScope.launch {
            contatoDao.atualizar(contato)
        }
    }
}