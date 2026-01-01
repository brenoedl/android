package com.stackmobile.aplicativodefilmes.servidor

import com.stackmobile.aplicativodefilmes.model.Categoria
import com.stackmobile.aplicativodefilmes.model.Filme
import io.ktor.client.HttpClient
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
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

    suspend fun getCateggooria(
        respostaDoServidor: (MutableList<Categoria>) -> Unit,
        respostaErro: (String) -> Unit
    ){
        try {
            val respostaServidor = httpClient.get(urlString = URL)

            if (respostaServidor.status.value == 200){
                val jsonObject = Json.parseToJsonElement(respostaServidor.bodyAsText()).jsonObject
                val categorias = parseJson(jsonObject)
                respostaDoServidor(categorias)
            }else{
                respostaErro("Erro ao carregar os dados!")
            }
        }catch (response: ResponseException){
            respostaErro("Erro: ${response.response.status.description}")
        }catch (t: Throwable){
            respostaErro("Erro: ${t.message}")
        }
    }

    private fun parseJson(jsonObject: JsonObject): MutableList<Categoria>{
        val listaaCategorias = mutableListOf<Categoria>()
        val categorias = jsonObject["categoria"]!!.jsonArray

        for (categoriaJson in categorias) {
            val titulo = categoriaJson.jsonObject["titulo"]!!.jsonPrimitive.content
            val filmesJsonArray = categoriaJson.jsonObject["capas"]!!.jsonArray

            val listaFilmes = mutableListOf<Filme>()

            for (filmeJson in filmesJsonArray) {
                val filme = Filme(
                    capa = filmeJson.jsonObject["url_imagem"]!!.jsonPrimitive.content,
                    id = filmeJson.jsonObject["id"]!!.jsonPrimitive.int,
                    nome = filmeJson.jsonObject["nome"]!!.jsonPrimitive.content,
                    descricao = filmeJson.jsonObject["descricao"]!!.jsonPrimitive.content,
                    elenco = filmeJson.jsonObject["elenco"]!!.jsonPrimitive.content
                )

                listaFilmes.add(filme)
            }
            val categoria = Categoria(
                titulo = titulo,
                filmes = listaFilmes
            )
            listaaCategorias.add(categoria)
        }
        return listaaCategorias
    }
}