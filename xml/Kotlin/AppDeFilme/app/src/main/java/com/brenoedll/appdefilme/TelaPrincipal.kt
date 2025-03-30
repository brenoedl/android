package com.brenoedll.appdefilme

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.brenoedll.appdefilme.adapter.CategoriaAdapter
import com.brenoedll.appdefilme.api.Api
import com.brenoedll.appdefilme.databinding.ActivityTelaPrincipalBinding
import com.brenoedll.appdefilme.model.Categoria
import com.brenoedll.appdefilme.model.Categorias
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TelaPrincipal : AppCompatActivity() {
    private lateinit var binding: ActivityTelaPrincipalBinding
    private lateinit var adapter: CategoriaAdapter
    private val listaCategoria = mutableListOf<Categoria>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        binding.tvSair.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, FormLogin::class.java)
            startActivity(intent)
            finish()
        }
        val recyclerViewFilmes = binding.rvCategoria
        recyclerViewFilmes.layoutManager = LinearLayoutManager(this)
        recyclerViewFilmes.setHasFixedSize(true)
        adapter = CategoriaAdapter(this, listaCategoria)
        recyclerViewFilmes.adapter = adapter

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://stackmobile.com.br/")
            .build().create(Api::class.java)

        retrofit.listaCategoria().enqueue(object : Callback<Categorias>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<Categorias?>,
                response: Response<Categorias?>
            ) {
                if (response.code() == 200) {
                    response.body()?.let { it: Categorias ->
                        adapter.listaCategoria.addAll(it.categorias)
                        adapter.notifyDataSetChanged()
                        binding.vProgressBar.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                        binding.tvProgressBar.visibility = View.GONE
                    }
                }
            }

            override fun onFailure(
                call: Call<Categorias?>,
                t: Throwable
            ) {
                Toast.makeText(this@TelaPrincipal, "Erro na conexão", Toast.LENGTH_SHORT).show()
            }
        })
    }
}