package com.brenoedl.agendadecontatos.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "tabela_contatos")
data class Contato (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "nome") val nome: String,
    @ColumnInfo(name = "sobrenome") val sobrenome: String,
    @ColumnInfo(name = "idade") val idade: String,
    @ColumnInfo(name = "telefone") val telefone: String
)
