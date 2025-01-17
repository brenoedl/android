package com.brenoedl.filmejava.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brenoedl.filmejava.Model.Filme;
import com.brenoedl.filmejava.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterFilme extends RecyclerView.Adapter<AdapterFilme.FilmeViewHolder>{
    private Context context;
    private List<Filme> listaFilme;

    public AdapterFilme(Context context, List<Filme> listaFilme) {
        this.context = context;
        this.listaFilme = listaFilme;
    }

    @NonNull
    @Override
    public FilmeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        itemLista = layoutInflater.inflate(R.layout.filme_item, parent, false);
        return  new FilmeViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmeViewHolder holder, int position) {
        Filme filme = listaFilme.get(position);
        Glide.with(context).load(filme.getCapa()).into(holder.ivCapaFilme);
        holder.tvTituloFilme.setText(filme.getTitulo());
    }

    @Override
    public int getItemCount() {
        return listaFilme.size();
    }

    public class FilmeViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivCapaFilme;
        private TextView tvTituloFilme, tvDescricaoFilme, tvElenco;
        public FilmeViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCapaFilme = itemView.findViewById(R.id.ivCapaFilme);
            tvTituloFilme = itemView.findViewById(R.id.tvTituloFilme);
            tvDescricaoFilme = itemView.findViewById(R.id.tvDetalhesDescricaoFilme);
            tvElenco = itemView.findViewById(R.id.tvDetalhesElenco);
        }
    }
}
