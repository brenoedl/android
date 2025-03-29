package com.brenoedll.appdefilme.model

import com.google.gson.annotations.SerializedName

data class Categoria(
    @SerializedName("titulo") val titulo: String? = null,
    @SerializedName("capas") val filmes: MutableList<Filme> = mutableListOf()
)