package com.brenoedl.buscadordecep.datasource

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import javax.inject.Inject

class Servidor @Inject constructor() {
    val httpClient = HttpClient{
        install(ContentNegotiation){
            json()
        }
    }

    suspend fun buscarCep(
        cep: String,
        respostaServidor: (String, String, String, String) -> Unit
    ){
        val response = httpClient.get(urlString = "https://viacep.com.br/ws/$cep/json/")
        try {
            if(response.status.value == 200){
                val endereco = Json.parseToJsonElement(response.bodyAsText()).jsonObject
                val logradouro = endereco["logradouro"]!!.jsonPrimitive.content
                val bairro = endereco["bairro"]!!.jsonPrimitive.content
                val cidade = endereco["localidade"]!!.jsonPrimitive.content
                val estado = endereco["estado"]!!.jsonPrimitive.content

                respostaServidor(logradouro, bairro, cidade, estado)
            }
        }catch (e: Exception){
            Log.e("Erro", e.toString())

        }
    }
}