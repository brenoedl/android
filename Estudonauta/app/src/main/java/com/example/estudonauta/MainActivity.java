package com.example.estudonauta;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Intent tela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickEquipe(View view) {
        tela = new Intent(getApplicationContext(), Equipe.class);
        startActivity(tela);
    }

    public void clickCursos(View view) {
        tela = new Intent(getApplicationContext(), Cursos.class);
        startActivity(tela);
    }

    public void clickMidia(View view) {
        tela = new Intent(getApplicationContext(), Midia.class);
        startActivity(tela);
    }
}