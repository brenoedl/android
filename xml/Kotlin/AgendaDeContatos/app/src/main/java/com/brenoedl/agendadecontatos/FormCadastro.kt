package com.brenoedl.agendadecontatos

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.brenoedl.agendadecontatos.dao.ContatoDao
import com.brenoedl.agendadecontatos.database.AppDatabase
import com.brenoedl.agendadecontatos.databinding.ActivityFormCadastroBinding
import com.brenoedl.agendadecontatos.model.Contato
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FormCadastro : AppCompatActivity() {
    private lateinit var binding: ActivityFormCadastroBinding
    private lateinit var contatoDao: ContatoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicialize a instância do banco de dados e o DAO
        val database = AppDatabase.getIntance(applicationContext)
        contatoDao = database.contatoDao()

        binding.bCadastrar.setOnClickListener {
            salvarContato()
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun salvarContato() {
        val nome = binding.etNomeCadastro.text.toString().trim()
        val sobrenome = binding.etSobrenomeCadastro.text.toString().trim()
        val idade = binding.etIdadeCadastro.text.toString().trim()
        val telefone = binding.etTelefoneCadastro.text.toString().trim()

        if (nome.isNotEmpty() && sobrenome.isNotEmpty() && idade.isNotEmpty() && telefone.isNotEmpty()) {
            val novoContato = Contato(nome = nome, sobrenome = sobrenome, idade = idade, telefone = telefone)
                lifecycleScope.launch(Dispatchers.IO) {
                    val idInserido = contatoDao.inserir(novoContato) // Insere um único contato
                    withContext(Dispatchers.Main) {
                        if (idInserido > 0) {
                            Toast.makeText(
                                this@FormCadastro,
                                "Contato salvo com sucesso!",
                                Toast.LENGTH_SHORT
                            ).show()
                            finish() // Encerra a Activity após salvar
                        } else {
                            Toast.makeText(
                                this@FormCadastro,
                                "Erro ao salvar o contato.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
        } else {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
        }
    }
}