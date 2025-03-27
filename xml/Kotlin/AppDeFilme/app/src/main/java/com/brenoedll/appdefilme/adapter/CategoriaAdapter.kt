package com.brenoedll.appdefilme.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brenoedll.appdefilme.databinding.CategoriaItemBinding
import com.brenoedll.appdefilme.model.Categoria

class CategoriaAdapter(private val context: Context, private val listaCategoria: MutableList<Categoria>):
    RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoriaViewHolder {
        val itemLista = CategoriaItemBinding.inflate(android.view.LayoutInflater.from(context), parent, false)
        return CategoriaViewHolder(itemLista)
    }

    override fun onBindViewHolder(
        holder: CategoriaViewHolder,
        position: Int
    ) {
        holder.titulo.text = listaCategoria[position].titulo
    }

    override fun getItemCount() = listaCategoria.size

    inner class CategoriaViewHolder(binding: CategoriaItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val titulo = binding.tvTituloCategoria
    }
}