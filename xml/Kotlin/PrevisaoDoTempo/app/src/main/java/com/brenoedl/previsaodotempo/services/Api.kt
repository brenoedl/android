package com.brenoedl.previsaodotempo.services

import com.brenoedl.previsaodotempo.mmodel.Main
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
//9cd2e95541bfa6c033b55146bcba8564

interface Api {
    @GET("weather")

    fun weatherMap(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String
    ): Call<Main>
}