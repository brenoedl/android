package com.brenoedl.lojavirtualcliente.activitys.detalhesproduto

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.brenoedl.lojavirtualcliente.R
import com.brenoedl.lojavirtualcliente.activitys.telafinalizarpedidos.TelaFinalizarPedidos
import com.brenoedl.lojavirtualcliente.databinding.ActivityDetalhesProdutoBinding
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

class DetalhesProduto : AppCompatActivity() {
    private lateinit var binding: ActivityDetalhesProdutoBinding
    private var tamanho: String = ""

    @SuppressLint("SetTextI18n", "ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nome = intent.extras!!.getString("nome")
        val preco = intent.extras!!.getString("preco")
        val foto = intent.extras!!.getString("foto")

        Glide.with(this).load(foto).into(binding.ivProduto)
        binding.ivProduto.contentDescription = "imagem do $nome"
        binding.tvNomeProduto.text = nome
        binding.tvPrecoProduto.text = "R$ $preco"

        binding.bFinalizar.setOnClickListener {
            tamanho = when {
                binding.rbNumero38.isChecked -> "38"
                binding.rbNumero39.isChecked -> "39"
                binding.rbNumero40.isChecked -> "40"
                binding.rbNumero41.isChecked -> "41"
                binding.rbNumero42.isChecked -> "42"
                else -> ""
            }

            if (tamanho.isEmpty()) {
                val snackbar = Snackbar.make(it, getString(R.string.frase_escolha_tamanho), Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(getColor(R.color.dark_blue))
                snackbar.setTextColor(getColor(R.color.white))
                snackbar.show()
            } else {
                val intent = Intent(this, TelaFinalizarPedidos::class.java)
                intent.putExtra("nome", nome)
                intent.putExtra("preco", preco)
                intent.putExtra("tamanho", tamanho)
                startActivity(intent)
            }
        }
    }
}