package com.brenoed.agendadecontatos

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.brenoed.agendadecontatos.dao.ContatosDao
import com.brenoed.agendadecontatos.databinding.ActivityCadastrarContatosBinding
import com.brenoed.agendadecontatos.model.Contatos
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CadastrarContatos : AppCompatActivity() {
    private lateinit var binding: ActivityCadastrarContatosBinding
    private lateinit var contatosDao: ContatosDao
    private var listaContatos: MutableList<Contatos> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastrarContatosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bCadastrar.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val nome = binding.etNome.text.toString()
                val sobrenome = binding.etSobrenome.text.toString()
                val idade = binding.etIdade.text.toString()
                val telefone = binding.etTelefone.text.toString()
                val mensagem:Boolean

                if (nome.isEmpty() || sobrenome.isEmpty() || idade.isEmpty() || telefone.isEmpty()) {
                    mensagem = false
                } else {
                    mensagem = true
                    cadastrarContato(nome, sobrenome, idade, telefone)
                }
                withContext(Dispatchers.Main) {
                    if (mensagem) {
                        Toast.makeText(applicationContext, "Contato cadastrado com sucesso!",  Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(applicationContext, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun cadastrarContato(nome: String, sobrenome: String, idade: String, telefone: String) {
        val contato = Contatos(nome, sobrenome, idade, telefone)
        listaContatos.add(contato)
        contatosDao = AppDatabase.getInstance(this).contatosDao()
        contatosDao.inserir(listaContatos)
    }
}