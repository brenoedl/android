package com.stackmobile.aplicativodefilmes.ui.theme.servidor

import io.ktor.client.HttpClient
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import javax.inject.Inject


class Servidor @Inject constructor() {

    companion object{
        const val URL = "https://stackmobile.com.br/filmes"
    }

    private val httpClient = HttpClient{
        install(ContentNegotiation){
            json()
        }
        install(HttpCache)
    }

}