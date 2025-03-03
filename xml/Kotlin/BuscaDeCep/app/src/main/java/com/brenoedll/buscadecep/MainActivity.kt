package com.brenoedll.buscadecep

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.brenoedll.buscadecep.api.Api
import com.brenoedll.buscadecep.api.model.Endereco
import com.brenoedll.buscadecep.databinding.ActivityMainBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://viacep.com.br/ws/")
            .build()
            .create(Api::class.java)

        binding.btBuscar.setOnClickListener {
            val cep = binding.tietCep.text.toString()
            if (cep.isEmpty()){
                binding.tietCep.error = getString(R.string.msg_erro1)
            }else{
                retrofit.setEndereco(cep).enqueue(object : Callback<Endereco>{
                    override fun onResponse(call: Call<Endereco>, response: Response<Endereco>) {
                        if (response.code() == 200){
                            val endereco = response.body()
                            val logradouro = endereco?.logradouro.toString()
                            val bairro = endereco?.bairro.toString()
                            val localidade = endereco?.localidade.toString()
                            val uf = endereco?.uf.toString()
                            setFormularios(logradouro, bairro, localidade, uf)
                        }else{
                            binding.tietCep.error = getString(R.string.msg_erro2)
                        }
                    }

                    override fun onFailure(call: Call<Endereco>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })
            }

        }
    }
    private fun setFormularios(logradouro: String, bairro: String, localidade: String, uf: String){
        binding.tietLogradouro.setText(logradouro)
        binding.tietBairro.setText(bairro)
        binding.tietCidade.setText(localidade)
        binding.tietEstado.setText(uf)
    }
}