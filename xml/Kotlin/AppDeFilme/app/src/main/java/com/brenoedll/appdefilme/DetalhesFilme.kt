package com.brenoedll.appdefilme

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.brenoedll.appdefilme.databinding.ActivityDetalhesFilmeBinding
import com.bumptech.glide.Glide

class DetalhesFilme : AppCompatActivity() {
    private lateinit var binding: ActivityDetalhesFilmeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesFilmeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        val capa = intent.extras!!.getString("capa")
        val nome = intent.extras!!.getString("nome")
        val descricao = intent.extras!!.getString("descricao")
        val elenco = intent.extras!!.getString("elenco")

        Glide.with(this).load(capa).centerCrop().into(binding.ivCapa)
        binding.tvNome.text = nome
        binding.tvDescricao.text = descricao
        binding.tvElenco.text = elenco

    }
}