package com.example.conversordetemperatura;

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

    public void calcCpF(View view) {
        tela = new Intent(getApplicationContext(), ActivityCpF.class);
        startActivity(tela);
    }

    public void calcCpK(View view) {
        tela = new Intent(getApplicationContext(), ActivityCpK.class);
        startActivity(tela);
    }

    public void calcFpC(View view) {
        tela = new Intent(getApplicationContext(), ActivityFpC.class);
        startActivity(tela);
    }

    public void calcFpK(View view) {
        tela = new Intent(getApplicationContext(), ActivityFpK.class);
        startActivity(tela);
    }

    public void calcKpC(View view) {
        tela = new Intent(getApplicationContext(), ActivityKpC.class);
        startActivity(tela);
    }

    public void calcKpF(View view) {
        tela = new Intent(getApplicationContext(), ActivityKpF.class);
        startActivity(tela);
    }
}