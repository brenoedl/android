package com.brenoedl.lojavirtualcliente.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brenoedl.lojavirtualcliente.activitys.detalhesproduto.DetalhesProduto
import com.brenoedl.lojavirtualcliente.databinding.ProdutoItemBinding
import com.brenoedl.lojavirtualcliente.model.Produto
import com.bumptech.glide.Glide

class AdapterProduto(val context: Context, val lisaProdutos: MutableList<Produto>): RecyclerView.Adapter<AdapterProduto.ProdutoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val itemLista = ProdutoItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ProdutoViewHolder(itemLista)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        Glide.with(context).load(lisaProdutos[position].foto).into(holder.fotoProduto)
        holder.fotoProduto.contentDescription = "imagem do ${lisaProdutos[position].nome}"
        holder.nomeProduto.text = lisaProdutos[position].nome
        holder.precoProduto.text = "R$ ${lisaProdutos[position].preco}"

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetalhesProduto::class.java)
            intent.putExtra("foto", lisaProdutos[position].foto)
            intent.putExtra("nome", lisaProdutos[position].nome)
            intent.putExtra("preco", lisaProdutos[position].preco)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = lisaProdutos.size

    inner class ProdutoViewHolder(binding: ProdutoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val fotoProduto = binding.ivProduto
        val nomeProduto = binding.tvNomeProduto
        val precoProduto = binding.tvPrecoProduto
    }
}