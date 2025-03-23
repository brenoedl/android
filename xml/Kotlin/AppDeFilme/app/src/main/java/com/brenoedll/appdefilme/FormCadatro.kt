package com.brenoedll.appdefilme

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.brenoedll.appdefilme.databinding.ActivityFormCadatroBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class FormCadatro : AppCompatActivity() {
    private lateinit var binding: ActivityFormCadatroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCadatroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        binding.tietEmailCadastro.requestFocus()

        binding.btVamosLa.setOnClickListener {
            val email = binding.tietEmailCadastro.text.toString().trim()

            if (email.isNotEmpty()) {
                binding.tilEmailCadastro.helperText = ""
                binding.tilEmailCadastro.boxStrokeColor = getColor(R.color.blue)

                binding.llMenu.visibility = View.VISIBLE
                binding.tvMensagem1.text = getString(R.string.frase_mundo)
                binding.tvMensagem2.text = getString(R.string.frase_criiar)
                binding.tilSenhaCadastro.visibility = View.VISIBLE
                binding.tietSenhaCadastro.requestFocus()
                binding.btVamosLa.visibility = View.GONE
                binding.btCadastrar.visibility = View.VISIBLE
            } else {
                binding.tilEmailCadastro.helperText = getString(R.string.erro_email1)
                binding.tilEmailCadastro.boxStrokeColor = getColor(R.color.red)
            }
        }

        binding.btCadastrar.setOnClickListener {
            val email = binding.tietEmailCadastro.text.toString().trim()
            val senha = binding.tietSenhaCadastro.text.toString()

            if (email.isNotEmpty() && senha.isNotEmpty()) {
                cadastro(email, senha)
            } else if (senha.isEmpty()) {
                binding.tilSenhaCadastro.helperText = getString(R.string.erro_senha1)
                binding.tilSenhaCadastro.boxStrokeColor = getColor(R.color.red)
                binding.tilEmailCadastro.boxStrokeColor = getColor(R.color.blue)
                binding.tietSenhaCadastro.requestFocus()
            } else if (email.isEmpty()) {
                binding.tilEmailCadastro.helperText = getString(R.string.erro_email1)
                binding.tilEmailCadastro.boxStrokeColor = getColor(R.color.red)
                binding.tietEmailCadastro.requestFocus()
            }
        }

        binding.tvEntrar.setOnClickListener {
            val intent = Intent(this, FormLogin::class.java)
            startActivity(intent)
        }
    }

    private fun cadastro(email: String, senha: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener { cadastro ->
            if (cadastro.isSuccessful) {
                Toast.makeText(this, getString(R.string.msg_sucesso_cadastro), Toast.LENGTH_SHORT).show()
                binding.tilEmailCadastro.helperText = ""
                binding.tilEmailCadastro.boxStrokeColor = getColor(R.color.blue)
                binding.tilSenhaCadastro.helperText = ""
                binding.tilSenhaCadastro.boxStrokeColor = getColor(R.color.blue)
            }
        }.addOnFailureListener { exception ->

            val erro = exception
            when {
                erro is FirebaseAuthWeakPasswordException -> {
                    binding.tilSenhaCadastro.helperText = getString(R.string.erro_senha2)
                    binding.tilSenhaCadastro.boxStrokeColor = getColor(R.color.red)
                }
                erro is FirebaseAuthUserCollisionException -> {
                    binding.tilEmailCadastro.helperText = getString(R.string.erro_conta)
                    binding.tilEmailCadastro.boxStrokeColor = getColor(R.color.red)
                }
                erro is FirebaseNetworkException -> {
                    Toast.makeText(this, getString(R.string.erro_internet), Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this, getString(R.string.msg_erro_cadastro), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}