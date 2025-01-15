package com.brenoedl.appdelivery;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class DetalhesProduto extends AppCompatActivity {
    private ImageView ivFotoProduto;
    private TextView tvTituloProduto;
    private TextView tvDescricaoProduto;
    private TextView tvPrecoProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_produto);
        inicializarComponentes();

        String foto = getIntent().getStringExtra("foto");
        String nome = getIntent().getStringExtra("nome");
        String descricao = getIntent().getStringExtra("descricao");
        String preco = getIntent().getStringExtra("preco");

        Glide.with(getApplicationContext()).load(foto).into(ivFotoProduto);
        tvTituloProduto.setText(nome);
        tvDescricaoProduto.setText(descricao);
        tvPrecoProduto.setText(preco);
    }

    private void inicializarComponentes(){
        ivFotoProduto = findViewById(R.id.ivFotoProduto);
        tvTituloProduto = findViewById(R.id.tvTituloProduto);
        tvDescricaoProduto = findViewById(R.id.tvDescricaoProduto);
        tvPrecoProduto = findViewById(R.id.tvPrecoProduto);
    }
}