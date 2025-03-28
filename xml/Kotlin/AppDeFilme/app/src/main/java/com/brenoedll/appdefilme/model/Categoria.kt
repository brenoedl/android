package com.brenoedll.appdefilme.model

data class Categoria(val titulo: String? = null, val filmes: MutableList<Filme> = mutableListOf())