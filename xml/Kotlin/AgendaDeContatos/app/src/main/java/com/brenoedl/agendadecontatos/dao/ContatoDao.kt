package com.brenoedl.agendadecontatos.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.brenoedl.agendadecontatos.model.Contato
import kotlinx.coroutines.flow.Flow

@Dao
interface ContatoDao {
    @Insert
    suspend fun inserir(contato: Contato): Long

    @Query("SELECT * FROM tabela_contatos ORDER BY nome ASC")
    fun obterTodos(): Flow<List<Contato>>

    @Query("SELECT * FROM tabela_contatos WHERE id = :id")
    fun obterPorId(id: Int): Flow<Contato?>

    @Delete
    suspend fun deletar(contato: Contato)

    @Update
    suspend fun atualizar(contato: Contato)
}