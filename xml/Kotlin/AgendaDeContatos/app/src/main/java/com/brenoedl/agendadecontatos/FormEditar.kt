package com.brenoedl.agendadecontatos

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.brenoedl.agendadecontatos.databinding.ActivityFormEditarBinding
import com.brenoedl.agendadecontatos.model.Contato
import com.brenoedl.agendadecontatos.viewmodel.ContatoViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FormEditar : AppCompatActivity() {
    private lateinit var binding: ActivityFormEditarBinding
    private lateinit var contatoViewModel: ContatoViewModel
    private var contatoId: Int = -1
    private var contatoAtual: Contato? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormEditarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contatoViewModel = ViewModelProvider(this).get(ContatoViewModel::class.java)

        // Obtenha o ID do contato passado pela Intent
        contatoId = intent.getIntExtra("CONTATO_ID", -1)

        if (contatoId != -1) {
            carregarDadosContato(contatoId)
        }

        binding.bSalvarEdicao.setOnClickListener {
            salvarEdicaoContato()
        }
    }

    private fun carregarDadosContato(id: Int) {
        lifecycleScope.launch {
            contatoViewModel.obterPorId(id).collectLatest { contato ->
                contato?.let {
                    contatoAtual = it
                    binding.etNomeEditar.setText(it.nome)
                    binding.etSobrenomeEditar.setText(it.sobrenome)
                    binding.etIdadeEditar.setText(it.idade)
                    binding.etTelefoneEditar.setText(it.telefone)
                } ?: run {
                    Toast.makeText(this@FormEditar, "Contato não encontrado.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }

    private fun salvarEdicaoContato() {
        val nome = binding.etNomeEditar.text.toString().trim()
        val sobrenome = binding.etSobrenomeEditar.text.toString().trim()
        val idade = binding.etIdadeEditar.text.toString().trim()
        val telefone = binding.etTelefoneEditar.text.toString().trim()

        if (nome.isNotEmpty() && sobrenome.isNotEmpty() && idade.isNotEmpty() && telefone.isNotEmpty()) {
            val contatoAtualizado = contatoAtual!!.copy(
                nome = nome,
                sobrenome = sobrenome,
                idade = idade,
                telefone = telefone
            )
            lifecycleScope.launch {
                contatoViewModel.atualizar(contatoAtualizado)
                Toast.makeText(this@FormEditar, "Contato atualizado!", Toast.LENGTH_SHORT).show()
                finish()
            }
        } else {
            Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show()
        }
    }
}