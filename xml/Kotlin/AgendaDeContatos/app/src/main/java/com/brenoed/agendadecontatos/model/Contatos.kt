package com.brenoed.agendadecontatos.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("tabela_contatos")
data class Contatos(
    @ColumnInfo("nome") val nome: String,
    @ColumnInfo("sobrenome") val sobrenome: String,
    @ColumnInfo("idade") val idade: String,
    @ColumnInfo("telefone") val telefone: String
){
    @PrimaryKey(autoGenerate = true)
    var uId: Int = 0
}