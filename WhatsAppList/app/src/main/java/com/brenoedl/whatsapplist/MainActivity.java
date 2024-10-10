package com.brenoedl.whatsapplist;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.brenoedl.whatsapplist.Adapter.AdapterContatos;
import com.brenoedl.whatsapplist.Model.Contatos;
import com.brenoedl.whatsapplist.RecyclerItemClickListener.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv_usuarios;
    private List<Contatos> contatosList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        rv_usuarios = findViewById(R.id.rvUsuarios);
        rv_usuarios.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rv_usuarios.setHasFixedSize(true);
        AdapterContatos adapterContatos = new AdapterContatos(contatosList);
        rv_usuarios.setAdapter(adapterContatos);
        contatos();
        rv_usuarios.addOnItemTouchListener(new  RecyclerItemClickListener(getApplicationContext(), rv_usuarios, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Contatos contato = contatosList.get(position);
                Toast.makeText(getApplicationContext(), getString(R.string.msgTC) + contato.getNome(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {
                Contatos contato = contatosList.get(position);
                Toast.makeText(getApplicationContext(), getString(R.string.msgTC) + contato.getNome(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }));
}
    public void contatos(){
        Contatos contatos1 = new Contatos(R.drawable.usuario1, getString(R.string.nome_m1), getString(R.string.frase1));
        contatosList.add(contatos1);
        Contatos contatos2 = new Contatos(R.drawable.usuario2, getString(R.string.nome_f1), getString(R.string.frase1));
        contatosList.add(contatos2);
        Contatos contatos3 = new Contatos(R.drawable.usuario1, getString(R.string.nome_m2), getString(R.string.frase2));
        contatosList.add(contatos3);
        Contatos contatos4 = new Contatos(R.drawable.usuario2, getString(R.string.nome_f2), getString(R.string.frase2));
        contatosList.add(contatos4);
        Contatos contatos5 = new Contatos(R.drawable.usuario1, getString(R.string.nome_m3), getString(R.string.frase3));
        contatosList.add(contatos5);
        Contatos contatos6 = new Contatos(R.drawable.usuario2, getString(R.string.nnme_f3), getString(R.string.frase5));
        contatosList.add(contatos6);
        Contatos contatos7 = new Contatos(R.drawable.usuario1, getString(R.string.nome_m4), getString(R.string.frase4));
        contatosList.add(contatos7);
        Contatos contatos8 = new Contatos(R.drawable.usuario2, getString(R.string.nome_f4), getString(R.string.frase4));
        contatosList.add(contatos8);
        Contatos contatos9 = new Contatos(R.drawable.usuario1, getString(R.string.nome_m5), getString(R.string.frase5));
        contatosList.add(contatos9);
        Contatos contatos10 = new Contatos(R.drawable.usuario2, getString(R.string.nome_f5), getString(R.string.frase5));
        contatosList.add(contatos10);
        Contatos contatos11 = new Contatos(R.drawable.usuario1, getString(R.string.nome_m6), getString(R.string.frase6));
        contatosList.add(contatos11);
        Contatos contatos12 = new Contatos(R.drawable.usuario2, getString(R.string.nome_f6), getString(R.string.frase6));
        contatosList.add(contatos12);
        Contatos contatos13 = new Contatos(R.drawable.usuario1, getString(R.string.nome_m7), getString(R.string.frase7));
        contatosList.add(contatos13);
        Contatos contatos14 = new Contatos(R.drawable.usuario2, getString(R.string.nome_f7), getString(R.string.frase7));
        contatosList.add(contatos14);
    }
}


