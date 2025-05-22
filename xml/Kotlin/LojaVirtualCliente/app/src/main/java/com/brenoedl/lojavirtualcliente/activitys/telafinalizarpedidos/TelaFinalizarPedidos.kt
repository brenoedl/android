package com.brenoedl.lojavirtualcliente.activitys.telafinalizarpedidos

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import com.brenoedl.lojavirtualcliente.R
import com.brenoedl.lojavirtualcliente.databinding.ActivityTelaFinalizarPedidosBinding
import com.google.android.material.snackbar.Snackbar
import androidx.core.net.toUri

class TelaFinalizarPedidos : AppCompatActivity() {
    private lateinit var binding: ActivityTelaFinalizarPedidosBinding

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaFinalizarPedidosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nome = intent.extras!!.getString("nome")
        val preco = intent.extras!!.getString("preco")
        val tamanho = intent.extras!!.getString("tamanho")

        binding.bPagamento.setOnClickListener {
            val bairro = binding.etBairro.text.toString()
            val ruaNumero = binding.etRuaNumero.text.toString()
            val cidadeEstado = binding.etCidadeEstado.text.toString()
            val celular = binding.etCelular.text.toString()

            if (bairro.isEmpty() || ruaNumero.isEmpty() || cidadeEstado.isEmpty() || celular.isEmpty()) {
                val snackbar = Snackbar.make(it, getString(R.string.erro_campo), Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.setTextColor(getColor(R.color.white))
                snackbar.show()
            } else {
            }
        }
    }
}