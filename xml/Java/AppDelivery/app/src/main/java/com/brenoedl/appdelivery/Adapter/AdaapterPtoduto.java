package com.brenoedl.appdelivery.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.brenoedl.appdelivery.Model.Produto;
import com.brenoedl.appdelivery.R;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdaapterPtoduto extends RecyclerView.Adapter<AdaapterPtoduto.ProdutoViewHolder> {
    private Context context;
    private List<Produto> produtoList;

    public AdaapterPtoduto(Context context, List<Produto> produtoList) {
        this.context = context;
        this.produtoList = produtoList;
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista;
        itemLista = LayoutInflater.from(context).inflate(R.layout.produto_item, parent, false);
        return new ProdutoViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {
        Glide.with(context).load(produtoList.get(position).getFoto()).into(holder.fotoProduto);
        holder.nomeProduto.setText(produtoList.get(position).getNome());
        holder.precoProduto.setText(produtoList.get(position).getPreco());
        //holder.descricaoProduto.setText(produtoList.get(position).getDescricao());
    }

    @Override
    public int getItemCount() {
        return produtoList.size();
    }

    public class ProdutoViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView fotoProduto;
        private TextView nomeProduto;
        private TextView precoProduto;
        private TextView descricaoProduto;

        public ProdutoViewHolder(@NonNull View itemView) {
            super(itemView);
            fotoProduto = itemView.findViewById(R.id.fotoProduto);
            nomeProduto = itemView.findViewById(R.id.nomeProduto);
            precoProduto = itemView.findViewById(R.id.precoProduto);
            descricaoProduto = itemView.findViewById(R.id.tvDescricaoProduto);
        }
    }
}
