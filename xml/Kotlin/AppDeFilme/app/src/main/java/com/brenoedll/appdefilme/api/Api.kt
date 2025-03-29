package com.brenoedll.appdefilme.api

import com.brenoedll.appdefilme.model.Categorias
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("/filmes")
    fun listaCategoria(): Call<Categorias>
}