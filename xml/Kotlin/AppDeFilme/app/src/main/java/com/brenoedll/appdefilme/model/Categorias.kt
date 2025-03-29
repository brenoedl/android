package com.brenoedll.appdefilme.model

import com.google.gson.annotations.SerializedName

data class Categorias(
    @SerializedName("categoria")
    val categorias: MutableList<Categoria> = mutableListOf()
)