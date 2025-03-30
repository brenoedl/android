package com.brenoedll.appdefilme.model

import com.google.gson.annotations.SerializedName

data class Filme(
    @SerializedName("url_imagem") val capa: String? = null,
    val nome: String? = null,
    val descricao: String? = null,
    val elenco: String? = null
)