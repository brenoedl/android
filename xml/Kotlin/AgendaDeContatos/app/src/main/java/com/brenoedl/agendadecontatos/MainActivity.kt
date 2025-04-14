package com.brenoedl.agendadecontatos

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.brenoedl.agendadecontatos.adapter.ContatoAdapter
import com.brenoedl.agendadecontatos.adapter.OnContatoClickListener
import com.brenoedl.agendadecontatos.databinding.ActivityMainBinding
import com.brenoedl.agendadecontatos.model.Contato
import com.brenoedl.agendadecontatos.viewmodel.ContatoViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), OnContatoClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var contatoViewModel: ContatoViewModel
    private lateinit var adapter: ContatoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvContatos.layoutManager = LinearLayoutManager(this)

        contatoViewModel = ViewModelProvider(this).get(ContatoViewModel::class.java)
        adapter = ContatoAdapter(this, this)
        binding.rvContatos.adapter = adapter

        lifecycleScope.launch {
            contatoViewModel.todosContatos.collectLatest { contatos ->
                adapter.submitList(contatos)
            }
        }

        binding.bCadastrar.setOnClickListener {
            val intent = Intent(this, FormCadastro::class.java)
            startActivity(intent)
        }
    }

    override fun onEditarClick(contato: Contato) {
        val intent = Intent(this, FormEditar::class.java)
        intent.putExtra("CONTATO_ID", contato.id)
        startActivity(intent)
    }

    override fun onExcluirClick(contato: Contato) {
        lifecycleScope.launch {
            contatoViewModel.excluir(contato)
        }
    }
}