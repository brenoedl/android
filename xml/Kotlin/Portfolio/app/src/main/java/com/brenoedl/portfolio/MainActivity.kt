package com.brenoedl.portfolio

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.brenoedl.portfolio.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fotoPerfil = BitmapFactory.decodeResource(resources, R.drawable.foto_perfil)
        val circulo = RoundedBitmapDrawableFactory.create(resources, fotoPerfil)
        circulo.isCircular = true
        binding.ivFotoPerfil.setImageDrawable(circulo)

        binding.btProjetos.setOnClickListener {
            val intent = Intent(this, Projetos::class.java)
            startActivity(intent)
        }
        binding.btContatos.setOnClickListener {
            val intent = Intent(this, Contatos::class.java)
            startActivity(intent)
        }
    }
}