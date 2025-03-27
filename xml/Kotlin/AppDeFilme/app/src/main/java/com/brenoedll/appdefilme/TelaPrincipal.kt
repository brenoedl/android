package com.brenoedll.appdefilme

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.brenoedll.appdefilme.adapter.CategoriaAdapter
import com.brenoedll.appdefilme.databinding.ActivityTelaPrincipalBinding
import com.brenoedll.appdefilme.model.Categoria
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
        val recyclerViewFilmes = binding.rvFilmes
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
        val categoria5 = Categoria("Categoria 5")
        listaCategoria.add(categoria5)
        val categoria6 = Categoria("Categoria 6")
        listaCategoria.add(categoria6)
        val categoria7 = Categoria("Categoria 7")
        listaCategoria.add(categoria7)
        val categoria8 = Categoria("Categoria 8")
        listaCategoria.add(categoria8)
        val categoria9 = Categoria("Categoria 9")
        listaCategoria.add(categoria9)
        val categoria10 = Categoria("Categoria 10")
        listaCategoria.add(categoria10)
        val categoria11 = Categoria("Categoria 11")
        listaCategoria.add(categoria11)
        val categoria12 = Categoria("Categoria 12")
        listaCategoria.add(categoria12)
        val categoria13 = Categoria("Categoria 13")
        listaCategoria.add(categoria13)
        val categoria14 = Categoria("Categoria 14")
        listaCategoria.add(categoria14)
    }
}