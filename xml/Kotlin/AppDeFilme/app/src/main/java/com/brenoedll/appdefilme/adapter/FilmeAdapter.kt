package com.brenoedll.appdefilme.adapter

import android.content.Context
import android.content.Intent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brenoedll.appdefilme.DetalhesFilme
import com.brenoedll.appdefilme.databinding.FilmeItemBinding
import com.brenoedll.appdefilme.model.Filme
import com.bumptech.glide.Glide

class FilmeAdapter(private val context: Context, private val listaFilmes: MutableList<Filme>) : RecyclerView.Adapter<FilmeAdapter.FilmeViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilmeViewHolder {
        val itemLista = FilmeItemBinding.inflate(android.view.LayoutInflater.from(context), parent, false)
        return FilmeViewHolder(itemLista)
    }

    override fun onBindViewHolder(
        holder: FilmeViewHolder,
        position: Int
    ) {
        Glide.with(context).load(listaFilmes[position].capa).centerCrop().into(holder.capa)
        holder.capa.setOnClickListener {
            val intent = Intent(context, DetalhesFilme::class.java)
            intent.putExtra("capa", listaFilmes[position].capa)
            intent.putExtra("nome", listaFilmes[position].nome)
            intent.putExtra("descricao", listaFilmes[position].descricao)
            intent.putExtra("elenco", listaFilmes[position].elenco)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = listaFilmes.size

    inner class FilmeViewHolder(binding: FilmeItemBinding): RecyclerView.ViewHolder(binding.root) {
        val capa = binding.ivCapa
    }

}