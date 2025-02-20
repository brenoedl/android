package com.brenoedl.filmejava;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class DetalhesFilme extends AppCompatActivity {
    private ImageView ivDetalhesCapaFilme, ivDetalhesPlay;
    private TextView tvDetalhesTituloFilme, tvDetalhesDescricaoFilme, tvDetalhesElenco;
    private Toolbar tbDetalhesFilme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_filme);
        getSupportActionBar().hide();
        iniciarComponentes();

        String capa = getIntent().getStringExtra("capa");
        String titulo = getIntent().getStringExtra("titulo");
        String descricao = getIntent().getStringExtra("descricao");
        String elenco = getIntent().getStringExtra("elenco");
        String trailer = getIntent().getStringExtra("trailer");

        String stTrailer =  trailer;

        Glide.with(this).load(capa).into(ivDetalhesCapaFilme);
        tvDetalhesTituloFilme.setText(titulo);
        tvDetalhesDescricaoFilme.setText(descricao);
        tvDetalhesElenco.setText(elenco);

        tbDetalhesFilme.setNavigationOnClickListener(view -> finish());

        ivDetalhesPlay.setOnClickListener(view -> {
            Intent intent = new Intent(DetalhesFilme.this, Video.class);
            intent.putExtra("trailer", stTrailer);
            startActivity(intent);});
    }

    private void iniciarComponentes(){
        tbDetalhesFilme = findViewById(R.id.tbDetalhesFilme);
        ivDetalhesCapaFilme = findViewById(R.id.ivDetalhesCapaFilme);
        ivDetalhesPlay = findViewById(R.id.ivDetalhesPlay);
        tvDetalhesTituloFilme = findViewById(R.id.tvDetalhesTituloFilme);
        tvDetalhesDescricaoFilme = findViewById(R.id.tvDetalhesDescricaoFilme);
        tvDetalhesElenco = findViewById(R.id.tvDetalhesElenco);
    }
}