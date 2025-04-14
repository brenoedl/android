package com.brenoedl.agendadecontatos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.brenoedl.agendadecontatos.databinding.ItemContatoBinding
import com.brenoedl.agendadecontatos.model.Contato
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking

interface OnContatoClickListener {
    fun onEditarClick(contato: Contato)
    fun onExcluirClick(contato: Contato)
}

class ContatoAdapter(
    private val context: Context,
    private val listener: OnContatoClickListener
) : ListAdapter<Contato, ContatoAdapter.ContatoViewHolder>(ContatoDiffCallback()) {

    init {
        // No Adapter, você não deve coletar o Flow diretamente.
        // O envio da lista deve ser feito pela Activity/ViewModel.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatoViewHolder {
        val itemLista = ItemContatoBinding.inflate(LayoutInflater.from(context), parent, false)
        return ContatoViewHolder(itemLista)
    }

    override fun onBindViewHolder(holder: ContatoViewHolder, position: Int) {
        val contatoAtual = getItem(position)
        holder.bind(contatoAtual)
    }

    inner class ContatoViewHolder(private val binding: ItemContatoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contato: Contato) {
            binding.tvNome.text = contato.nome
            binding.tvSobrenome.text = contato.sobrenome
            binding.tvIdade.text = contato.idade.toString() // Certifique-se de converter para String
            binding.tvTelefone.text = contato.telefone

            binding.ivEdiar.setOnClickListener {
                listener.onEditarClick(contato)
            }

            binding.ivExcluir.setOnClickListener {
                listener.onExcluirClick(contato)
            }
        }
    }
}

class ContatoDiffCallback : DiffUtil.ItemCallback<Contato>() {
    override fun areItemsTheSame(oldItem: Contato, newItem: Contato): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Contato, newItem: Contato): Boolean {
        return oldItem == newItem
    }
}