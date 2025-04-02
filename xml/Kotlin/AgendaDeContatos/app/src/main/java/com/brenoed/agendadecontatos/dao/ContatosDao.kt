package com.brenoed.agendadecontatos.dao

import androidx.room.Dao
import androidx.room.Insert
import com.brenoed.agendadecontatos.model.Contatos

@Dao
interface ContatosDao {

    @Insert
    fun inserir(listaContatos: MutableList<Contatos>)
}