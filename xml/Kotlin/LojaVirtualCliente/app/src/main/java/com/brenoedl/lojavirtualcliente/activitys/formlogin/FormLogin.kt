package com.brenoedl.lojavirtualcliente.activitys.formlogin

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.brenoedl.lojavirtualcliente.R
import com.brenoedl.lojavirtualcliente.activitys.formcadastro.FormCadastro
import com.brenoedl.lojavirtualcliente.activitys.telaprincipalprodutos.TelaPrincipalProdutos
import com.brenoedl.lojavirtualcliente.databinding.ActivityFormLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.brenoedl.lojavirtualcliente.dialog.DialogCarregando
import com.google.android.gms.common.util.Hex
import com.mercadopago.android.px.internal.features.pay_button.PayButton

class FormLogin : AppCompatActivity() {
    private lateinit var binding: ActivityFormLoginBinding
    private val dialogCarregando = DialogCarregando(this)
    private lateinit var imm: InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

        binding.etEmail.requestFocus()

        binding.tvCadastro.setOnClickListener {
            val intent = Intent(this, FormCadastro::class.java)
            startActivity(intent)
        }

        binding.bEntrar.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val senha = binding.etSenha.text.toString()

            if (email.isEmpty() || senha.isEmpty()) {
                val snackbar = Snackbar.make(it,getString(R.string.erro_campo), Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            } else {
                logarUsuario(email, senha, it)
            }
        }
    }

    override fun onStart() {
        super.onStart()

        val usuarioAtual = FirebaseAuth.getInstance().currentUser
        if (usuarioAtual != null) {
            navegarTelaPrincipal()
        }
    }

    private fun navegarTelaPrincipal() {
        val intent = Intent(this, TelaPrincipalProdutos::class.java)
        startActivity(intent)
        finish()
    }

    private fun logarUsuario(email: String, senha: String, view: View) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    imm.hideSoftInputFromWindow(view.windowToken, 0)

                    val snackbar = Snackbar.make(binding.root, getString(R.string.sucesso_login), Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.BLUE)
                    snackbar.show()
                    dialogCarregando.iniciarCarregamentoAlertDialog()

                    Handler(mainLooper).postDelayed({
                        navegarTelaPrincipal()
                        dialogCarregando.liberarAlertDialog()
                    }, 3000)
                }
            }.addOnFailureListener { exception ->
                val erro = when(exception) {
                    is FirebaseAuthWeakPasswordException -> getString(R.string.erro_senha)
                    is FirebaseAuthInvalidUserException -> getString(R.string.erro_usuario)
                    is FirebaseAuthInvalidCredentialsException -> getString(R.string.erro_email)
                    is FirebaseNetworkException -> getString(R.string.erro_internet)
                    else -> getString(R.string.erro_login)
                }
                val snackbar = Snackbar.make(binding.root, erro, Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            }
    }
}