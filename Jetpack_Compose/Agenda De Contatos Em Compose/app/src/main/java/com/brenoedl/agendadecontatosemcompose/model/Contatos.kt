package com.brenoedl.agendadecontatosemcompose.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.brenoedl.agendadecontatosemcompose.constantes.Constantes

@Entity(tableName = Constantes.TABELA_CONTATOS)
data class Contatos(
    @ColumnInfo(name = "nome") val nome: String?,
    @ColumnInfo(name = "sobrenome") val sobrenome: String?,
    @ColumnInfo(name = "idade") val idade: String?,
    @ColumnInfo(name = "celular") val celular: String?
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
