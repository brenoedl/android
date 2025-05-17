package com.brenoedl.lojavirtualcliente.dialog

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import com.brenoedl.lojavirtualcliente.activitys.formlogin.FormLogin
import com.brenoedl.lojavirtualcliente.databinding.DialogDetalhesUsuariosBinding
import com.brenoedl.lojavirtualcliente.model.BancoDados
import com.google.firebase.auth.FirebaseAuth

class DialogDetalhesUsuario(private val activity: Activity) {
    lateinit var dialog: AlertDialog
    lateinit var binding: DialogDetalhesUsuariosBinding

    fun iniciarDetalhesUsuarioAlertDialog() {
        val builder = AlertDialog.Builder(activity)
        binding = DialogDetalhesUsuariosBinding.inflate(activity.layoutInflater)
        builder.setView(binding.root)
        builder.setCancelable(true)
        dialog = builder.create()
        dialog.show()
    }

    fun recuperarDadosUsuarioBanco() {
        val nome = binding.tvNome
        val email = binding.tvEmail
        val bancoDados = BancoDados()
        bancoDados.recuperarDadosUsuario(nome, email)

        binding.bSair.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(activity, FormLogin::class.java)
            activity.startActivity(intent)
            activity.finish()
        }
    }
}