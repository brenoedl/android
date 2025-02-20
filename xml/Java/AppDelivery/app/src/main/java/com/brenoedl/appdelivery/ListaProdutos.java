package com.brenoedl.appdelivery;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.brenoedl.appdelivery.Adapter.AdaapterPtoduto;
import com.brenoedl.appdelivery.Model.Produto;
import com.brenoedl.appdelivery.RecyclerItemClickListener.RecyclerItemClickListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListaProdutos extends AppCompatActivity {
    private RecyclerView rvProdutos;
    private AdaapterPtoduto adapterPtoduto;
    private List<Produto> produtoList;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);

        rvProdutos = findViewById(R.id.rvProdutos);
        produtoList = new ArrayList<>();
        adapterPtoduto = new AdaapterPtoduto(getApplicationContext(), produtoList);
        rvProdutos.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvProdutos.setHasFixedSize(true);
        rvProdutos.setAdapter(adapterPtoduto);

        rvProdutos.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        rvProdutos,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent = new Intent(ListaProdutos.this, DetalhesProduto.class);
                                intent.putExtra("foto", produtoList.get(position).getFoto());
                                intent.putExtra("nome", produtoList.get(position).getNome());
                                intent.putExtra("descricao", produtoList.get(position).getDescricao());
                                intent.putExtra("preco", produtoList.get(position).getPreco());
                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {}

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {}
                        }
                )
        );

        db = FirebaseFirestore.getInstance();
        db.collection("produtos").orderBy("nome").get().addOnCompleteListener(new OnCompleteListener<>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                        Produto produto = queryDocumentSnapshot.toObject(Produto.class);
                        produtoList.add(produto);
                        adapterPtoduto.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.perfil) {
            Toast.makeText(ListaProdutos.this, "Perfil do usuário!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ListaProdutos.this, PerfilUsuario.class);
            startActivity(intent);
        } else if (itemId == R.id.produtos) {
            Toast.makeText(ListaProdutos.this, "Produtos em breve!", Toast.LENGTH_LONG).show();
        } else if (itemId == R.id.deslogar) {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(ListaProdutos.this, "Deslogado usuario!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ListaProdutos.this, FormLogin.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}