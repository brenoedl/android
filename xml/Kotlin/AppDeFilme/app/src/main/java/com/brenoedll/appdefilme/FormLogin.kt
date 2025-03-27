package com.brenoedll.appdefilme

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.brenoedll.appdefilme.databinding.ActivityFormLoginBinding
import com.google.firebase.auth.FirebaseAuth

class FormLogin : AppCompatActivity() {
    private lateinit var binding: ActivityFormLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        binding.tietEmailLogin.requestFocus()

        binding.bEntrar.setOnClickListener {
            val email = binding.tietEmailLogin.text.toString().trim()
            val senha = binding.tietSenhaLogin.text.toString()

            if(email.isEmpty()) {
                binding.tilEmailLogin.helperText = getString(R.string.erro_email1)
                binding.tilEmailLogin.boxStrokeColor = getColor(R.color.orange)
            } else if(senha.isEmpty()) {
                binding.tilSenhaLogin.helperText = getString(R.string.erro_senha1)
                binding.tilSenhaLogin.boxStrokeColor = getColor(R.color.orange)
            }else {
                autenticacaoUsuario(email, senha)
            }
        }

        binding.tvInscrevaSe.setOnClickListener {
            val intent = Intent(this, FormCadatro::class.java)
            startActivity(intent)
        }
    }

    private fun autenticacaoUsuario(email: String, senha: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener { autenticacao ->
            if (autenticacao.isSuccessful){
                binding.tilEmailLogin.helperText = ""
                binding.tilEmailLogin.boxStrokeColor = getColor(R.color.blue)
                binding.tilSenhaLogin.helperText = ""
                binding.tilSenhaLogin.boxStrokeColor = getColor(R.color.blue)
                Toast.makeText(this, getString(R.string.msg_sucesso_login), Toast.LENGTH_SHORT).show()
                navegarTelaPrincipal()
            }
        }.addOnFailureListener { error ->
            Toast.makeText(this, getString(R.string.msg_erro_login), Toast.LENGTH_SHORT).show()
        }
    }

    private fun navegarTelaPrincipal() {
        val intent = Intent(this, TelaPrincipal::class.java)
        startActivity(intent)
        finish()
    }

    override fun onStart() {
        super.onStart()

        val usuarioAtual = FirebaseAuth.getInstance().currentUser
        if(usuarioAtual != null) {
            navegarTelaPrincipal()
        }
    }
}