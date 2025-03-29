package com.brenoedll.appdefilme.model

import com.google.gson.annotations.SerializedName

data class Filme(
    @SerializedName("url_imagem") val capa: String? = null
)