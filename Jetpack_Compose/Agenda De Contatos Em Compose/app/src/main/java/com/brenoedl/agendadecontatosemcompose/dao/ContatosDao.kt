package com.brenoedl.agendadecontatosemcompose.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.brenoedl.agendadecontatosemcompose.model.Contatos


@Dao
interface ContatosDao {
    @Insert
    fun salvarContatos(contato: Contatos)

    @Query("SELECT * FROM tabela_contatos ORDER BY nome ASC")
    fun getContatos(): List<Contatos>

    @Query("UPDATE tabela_contatos SET nome = :nome, sobrenome = :sobrenome, idade = :idade, celular = :celular WHERE id = :id")
    fun atualizarContatos(id: Int, nome: String, sobrenome: String, idade: String, celular: String)

    @Query("DELETE FROM tabela_contatos WHERE id = :id")
    fun deletarContatos(id: Int)
}