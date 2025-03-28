package com.brenoedll.appdefilme

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.brenoedll.appdefilme.adapter.CategoriaAdapter
import com.brenoedll.appdefilme.databinding.ActivityTelaPrincipalBinding
import com.brenoedll.appdefilme.model.Categoria
import com.brenoedll.appdefilme.model.Filme
import com.google.firebase.auth.FirebaseAuth

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
        getCategoria()
    }

    private fun getCategoria() {
        val categoria1 = Categoria("Categoria 1")
        listaCategoria.add(categoria1)
        val categoria2 = Categoria("Categoria 2")
        listaCategoria.add(categoria2)
        val categoria3 = Categoria("Categoria 3")
        listaCategoria.add(categoria3)
        val categoria4 = Categoria("Categoria 4")
        listaCategoria.add(categoria4)
    }
}