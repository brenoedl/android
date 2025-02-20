package com.brenoedl.filmejava;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.brenoedl.filmejava.Adapter.AdapterFilme;
import com.brenoedl.filmejava.Model.Filme;

import com.brenoedl.filmejava.Model.FilmeApi;
import com.brenoedl.filmejava.RecyclerItemClickListener.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvFilme;
    private AdapterFilme adapterFilme;
    private List<Filme> listaFilme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        iniciarComponentes();

        listaFilme = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://firebasestorage.googleapis.com/v0/b/app-delivery-97d5b.appspot.com/o/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        FilmeApi filmeApi = retrofit.create(FilmeApi.class);
        Call<List<Filme>> call = filmeApi.getFilmes();
        call.enqueue(new Callback<List<Filme>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<List<Filme>> call, @NonNull Response<List<Filme>> response) {
                if (response.code() != 200) {
                    return;
                }
                List<Filme> filmes = response.body();
                assert filmes != null;
                listaFilme.addAll(filmes);
                adapterFilme = new AdapterFilme(getApplicationContext(), listaFilme);
                rvFilme.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                rvFilme.setAdapter(adapterFilme);
                rvFilme.setHasFixedSize(true);
                rvFilme.setAdapter(adapterFilme);
                adapterFilme.notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NonNull Call<List<Filme>> call, @NonNull Throwable t) {}
        });

        rvFilme.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(),
                rvFilme, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, DetalhesFilme.class);
                intent.putExtra("capa", listaFilme.get(position).getCapa());
                intent.putExtra("titulo", listaFilme.get(position).getTitulo());
                intent.putExtra("descricao", listaFilme.get(position).getDescricao());
                intent.putExtra("elenco", listaFilme.get(position).getElenco());
                intent.putExtra("trailer", listaFilme.get(position).getVideo());
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {}
        }));

    }

    private void iniciarComponentes(){
        rvFilme = findViewById(R.id.rvFilme);
    }
}