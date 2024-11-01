package com.brenoedl.whatsapplist.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brenoedl.whatsapplist.Model.Contatos;
import com.brenoedl.whatsapplist.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterContatos extends RecyclerView.Adapter<AdapterContatos.ContatosViewHolder> {

    private List<Contatos> contatosList = new ArrayList<>();

    public AdapterContatos(List<Contatos> contatosList) { 
        this.contatosList = contatosList;
    }

    @NonNull
    @Override
    public ContatosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        item = layoutInflater.inflate(R.layout.contato_item, parent, false);
        return new ContatosViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ContatosViewHolder holder, int position) {
        holder.foto.setImageResource(contatosList.get(position).getFotto());
        holder.nome.setText(contatosList.get(position).getNome());
        holder.mensagem.setText(contatosList.get(position).getMensagem());
    }

    @Override
    public int getItemCount() {
        return contatosList.size();
    }

    public class ContatosViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView foto;
        private TextView nome;
        private TextView mensagem;
        public ContatosViewHolder(@NonNull View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.civFoto);
            nome = itemView.findViewById(R.id.tvNome);
            mensagem = itemView.findViewById(R.id.tvMensagem);
        }
    }
}
