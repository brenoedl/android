package com.brenoedl.lojavirtualcliente.activitys.formcadastro

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.brenoedl.lojavirtualcliente.R
import com.brenoedl.lojavirtualcliente.activitys.formlogin.FormLogin
import com.brenoedl.lojavirtualcliente.databinding.ActivityFormCadastroBinding
import com.brenoedl.lojavirtualcliente.model.BancoDados
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class FormCadastro : AppCompatActivity() {
    private lateinit var binding: ActivityFormCadastroBinding
    private val bancoDados = BancoDados()
    private lateinit var imm: InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

        binding.etNome.requestFocus()

        binding.bCadastrar.setOnClickListener {
            val nome = binding.etNome.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val senha = binding.etSenha.text.toString()

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                val snackbar = Snackbar.make(it,getString(R.string.erro_campo), Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            } else {
                cadastrarUsuario(nome, email, senha, it)
            }
        }
    }

    private fun navegarTelaLogin() {
        val intent = Intent(this, FormLogin::class.java)
        startActivity(intent)
        finish()
    }

    private fun cadastrarUsuario(nome: String, email: String, senha: String, view: View) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener {
            if (it.isSuccessful) {
                bancoDados.salvarDadosUsuario(nome)
                imm.hideSoftInputFromWindow(view.windowToken, 0)

                val snackbar = Snackbar.make(binding.root,getString(R.string.sucesso_cadastro), Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.BLUE)
                snackbar.show()
                navegarTelaLogin()
            }
        }.addOnFailureListener { exepetion ->
            var erro = when (exepetion){
                is FirebaseAuthWeakPasswordException -> getString(R.string.erro_senha)
                is FirebaseAuthInvalidCredentialsException -> getString(R.string.erro_email)
                is FirebaseAuthUserCollisionException -> getString(R.string.erro_conta_duplicada)
                is FirebaseNetworkException -> getString(R.string.erro_internet)
                else -> getString(R.string.erro_cadastro)
            }
            val snackbar = Snackbar.make(binding.root,erro, Snackbar.LENGTH_SHORT)
            snackbar.setBackgroundTint(Color.RED)
            snackbar.show()
        }
    }
}
