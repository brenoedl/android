package com.brenoedl.lojavirtualcliente.model

import android.annotation.SuppressLint
import android.util.Log
import android.widget.TextView
import com.brenoedl.lojavirtualcliente.adapter.AdapterProduto
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class BancoDados {
    fun salvarDadosUsuario(nome: String) {
        val usuarioId = FirebaseAuth.getInstance().currentUser!!.uid
        val bancoDados = FirebaseFirestore.getInstance()
        val dadosUsuario = hashMapOf("nome" to nome)
        val documentReference: DocumentReference = bancoDados.collection("Usuários").document(usuarioId)

        documentReference.set(dadosUsuario).addOnSuccessListener {
            Log.d("db", "Sucesso ao salvar os dados!")
        }.addOnFailureListener {
            Log.d("db_error", "Erro ao salvar os dados!")
        }
    }

    fun recuperarDadosUsuario(tvNome: TextView, tvEmail: TextView) {
        val usuarioId = FirebaseAuth.getInstance().currentUser!!.uid
        val email = FirebaseAuth.getInstance().currentUser!!.email
        val bancoDados = FirebaseFirestore.getInstance()
        val documentReference: DocumentReference = bancoDados.collection("Usuários").document(usuarioId)
        documentReference.addSnapshotListener { documento, error ->
            if (documento != null) {
                tvNome.text = documento.getString("nome")
                tvEmail.text = email
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun obterDadosProdutos(listaProdutos: MutableList<Produto>, adapterProduto: AdapterProduto) {
        val bancoDados = FirebaseFirestore.getInstance()
        bancoDados.collection("Produtos").get()
            .addOnCompleteListener { it ->
                if (it.isSuccessful) {
                    for (documento in it.result!!) {
                        val produto = documento.toObject(Produto::class.java)
                        listaProdutos.add(produto)
                        adapterProduto.notifyDataSetChanged()
                    }
                }
            }.addOnFailureListener {
                Log.d("db_error", "Erro ao recuperar os dados!")
            }
    }
}