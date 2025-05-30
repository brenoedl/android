package com.brenoedl.previsaodotempo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.brenoedl.previsaodotempo.constantes.Const
import com.brenoedl.previsaodotempo.databinding.ActivityMainBinding
import com.brenoedl.previsaodotempo.mmodel.Main
import com.brenoedl.previsaodotempo.services.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Suppress("DUPLICATE_BRANCH_CONDITION_IN_WHEN")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var imm: InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        setContentView(binding.root)

        binding.sTema.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding.telaMain.setBackgroundResource(R.color.black)
                binding.vInfo.setBackgroundResource(R.drawable.comteiner_info_escuro_shape)
                binding.tvTituloInfo.setTextColor(getColor(R.color.black))
                binding.tvInfo1.setTextColor(getColor(R.color.black))
                binding.tvInfo2.setTextColor(getColor(R.color.black))
                window.statusBarColor = getColor(R.color.black)
            } else {
                binding.telaMain.setBackgroundResource(R.color.dark_blue_300)
                binding.vInfo.setBackgroundResource(R.drawable.comteiner_info_claro_shape)
                binding.tvTituloInfo.setTextColor(getColor(R.color.white))
                binding.tvInfo1.setTextColor(getColor(R.color.white))
                binding.tvInfo2.setTextColor(getColor(R.color.white))
                window.statusBarColor = getColor(R.color.dark_blue_300)
            }
        }

        binding.btBuscar.setOnClickListener {
            if (binding.etBuscar.text.toString().trim().isEmpty()) {
                Toast.makeText(applicationContext, "Digite uma cidade", Toast.LENGTH_SHORT).show()
                binding.etBuscar.requestFocus()
                imm.showSoftInput(binding.etBuscar, InputMethodManager.SHOW_IMPLICIT)
            } else {
                val cidade = binding.etBuscar.text.toString().trim()
                apiPrevisaoTempo(cidade)
                imm.hideSoftInputFromWindow(binding.etBuscar.windowToken, 0)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        apiPrevisaoTempo("São Paulo")
    }

    private fun apiPrevisaoTempo(decide: String) {
        binding.pbBuscar.visibility = View.VISIBLE

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .build()
            .create(Api::class.java)

        retrofit.weatherMap(decide, Const.API_KEY).enqueue(object: Callback<Main>{
            override fun onResponse(call: Call<Main?>, response: Response<Main?>) {
                if (response.isSuccessful) {
                    respostaServidor(response)
                } else {
                    Toast.makeText(applicationContext, "Erro cidade inválida", Toast.LENGTH_SHORT).show()
                    binding.pbBuscar.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<Main?>, t: Throwable) {
                Toast.makeText(applicationContext, "Erro fatal do servidor", Toast.LENGTH_SHORT).show()
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun respostaServidor(resposta: Response<Main?>){
        val main = resposta.body()!!.main
        val temp = main.get("temp").toString().toDouble()
        val tempMin = main.get("temp_min").toString().toDouble()
        val tempMax = main.get("temp_max").toString().toDouble()
        val umidade = main.get("humidity").toString().toInt()

        val sys = resposta.body()!!.sys
        val country = sys.get("country").asString

        val weather = resposta.body()!!.weather
        val mainWeather = weather[0].main.toString()
        val description = weather[0].description.toString()

        val name = resposta.body()!!.name
        val cidade = name

        val temp_c = (temp - 273.15).toInt()
        val tempMin_c = (tempMin - 273.15).toInt()
        val tempMax_c = (tempMax - 273.15).toInt()

        var pais = ""

        when(country){
            "BR" -> pais = "Brasil"
            "US" -> pais = "Estados Unidos"
            "MX" -> pais = "México"
            "AR" -> pais = "Argentina"
            "CL" -> pais = "Chile"
            "CO" -> pais = "Colômbia"
            "PE" -> pais = "Peru"
            "EC" -> pais = "Equador"
            "PY" -> pais = "Paraguai"
            "UY" -> pais = "Uruguai"
            "VE" -> pais = "Venezuela"
            "CR" -> pais = "Costa Rica"
            "GT" -> pais = "Guatemala"
            "HN" -> pais = "Honduras"
            "NI" -> pais = "Nicarágua"
            "PA" -> pais = "Panamá"
            "DO" -> pais = "República Dominicana"
            "SV" -> pais = "El Salvador"
            "BO" -> pais = "Bolívia"
            "PR" -> pais = "Porto Rico"
            "JM" -> pais = "Jamaica"
            "CU" -> pais = "Cuba"
            "DM" -> pais = "Dominica"
            "GD" -> pais = "Granada"
            "MS" -> pais = "Montserrat"
            "AW" -> pais = "Aruba"
            "BB" -> pais = "Barbados"
            "BZ" -> pais = "Belize"
            "CA" -> pais = "Canadá"
        }

        if (mainWeather == "Clouds" && description == "few clouds") {
            binding.ivClima.setImageResource(R.drawable.flewclouds)
        } else if (mainWeather == "Clouds" && description == "scattered clouds") {
            binding.ivClima.setImageResource(R.drawable.clouds)
        } else if (mainWeather == "Clouds" && description == "broken clouds") {
            binding.ivClima.setImageResource(R.drawable.brokenclouds)
        } else if (mainWeather == "Clouds" && description == "overcast clouds") {
            binding.ivClima.setImageResource(R.drawable.brokenclouds)
        } else if (mainWeather == "Clear" && description == "clear sky") {
            binding.ivClima.setImageResource(R.drawable.clearsky)
        } else if (mainWeather == "Snow") {
            binding.ivClima.setImageResource(R.drawable.snow)
        } else if (mainWeather == "Rain") {
            binding.ivClima.setImageResource(R.drawable.rain)
        } else if (mainWeather == "Drizzle") {
            binding.ivClima.setImageResource(R.drawable.rain)
        } else if (mainWeather == "Thunderstorm") {
            binding.ivClima.setImageResource(R.drawable.trunderstorm)
        } else {
            binding.ivClima.setImageResource(R.drawable.brokenclouds)
        }

        var descricao = ""

        when(description) {
            "clear sky" -> descricao = "Céu limpo"
            "few clouds" -> descricao = "Poucas nuvens"
            "scattered clouds" -> descricao = "Nuvens dispersas"
            "broken clouds" -> descricao = "Nuvens quebradas"
            "shower rain" -> descricao = "Chuva de banho"
            "rain" -> descricao = "Chuva"
            "thunderstorm" -> descricao = "Trovoada"
            "snow" -> descricao = "Nevendoo"
            "mist" -> descricao = "Névoa"
            "tornado" -> descricao = "Tornado"
            "drizzle" -> descricao = "Chuviscando"
            "fog" -> descricao = "Névoa"
        }

        binding.tvTemperatura.text = "${temp_c}°C"
        binding.tvPaisCidade.text = "$pais - $cidade"
        binding.tvInfo1.text = "Clima\n$descricao\n\nUmidade\n$umidade%"
        binding.tvInfo2.text = "Temp min\n${tempMin_c}°C\n\ntemp max\n$tempMax_c°C"

        binding.pbBuscar.visibility = View.GONE
    }
}