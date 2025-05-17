package com.brenoedl.lojavirtualcliente.activitys.telaprincipalprodutos

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.brenoedl.lojavirtualcliente.R
import com.brenoedl.lojavirtualcliente.activitys.formlogin.FormLogin
import com.brenoedl.lojavirtualcliente.activitys.telapedidos.TelaPedidos
import com.brenoedl.lojavirtualcliente.adapter.AdapterProduto
import com.brenoedl.lojavirtualcliente.databinding.ActivityTelaPrincipalProdutosBinding
import com.brenoedl.lojavirtualcliente.dialog.DialogDetalhesUsuario
import com.brenoedl.lojavirtualcliente.model.BancoDados
import com.brenoedl.lojavirtualcliente.model.Produto
import com.google.firebase.auth.FirebaseAuth

class TelaPrincipalProdutos : AppCompatActivity() {
    private lateinit var binding: ActivityTelaPrincipalProdutosBinding
    private lateinit var adaptrwProduto: AdapterProduto
    private var listaProdutos: MutableList<Produto> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaPrincipalProdutosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerProdutos = binding.rvProdutos
        recyclerProdutos.layoutManager = GridLayoutManager(this, 2)
        recyclerProdutos.setHasFixedSize(true)
        adaptrwProduto = AdapterProduto(this, listaProdutos)
        recyclerProdutos.adapter = adaptrwProduto

        val bancoDados = BancoDados()
        bancoDados.obterDadosProdutos(listaProdutos, adaptrwProduto)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuPerfil -> iniciarDialogDetalhesUsuario()
            R.id.menuPedidos -> irParaTelaPedidos()
            R.id.menuSair -> deslogarUsuario()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun iniciarDialogDetalhesUsuario() {
        val dialogDetalhesUsuario = DialogDetalhesUsuario(this)
        dialogDetalhesUsuario.iniciarDetalhesUsuarioAlertDialog()
        dialogDetalhesUsuario.recuperarDadosUsuarioBanco()
    }

    private fun irParaTelaPedidos(){
        val irParaTelaPedidos = Intent(this, TelaPedidos::class.java)
        startActivity(irParaTelaPedidos)
    }

    private fun deslogarUsuario() {
        FirebaseAuth.getInstance().signOut()
        val voltarTelaLogin = Intent(this, FormLogin::class.java)
        startActivity(voltarTelaLogin)
        finish()
    }
}