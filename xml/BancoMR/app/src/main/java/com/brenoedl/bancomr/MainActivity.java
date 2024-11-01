package com.brenoedl.bancomr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.brenoedl.bancomr.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        binding.imgSaldo.setOnClickListener(this);

        binding.imgFatura.setOnClickListener(this);

        binding.imgTransferencia.setOnClickListener(this);

        binding.imgPoupanca.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.imgSaldo){
            Intent telaSaldo = new Intent(MainActivity.this, saldo.class);
            startActivity(telaSaldo);
        }else if (id == R.id.imgFatura){
            Intent telaFatura = new Intent(MainActivity.this, Faturas.class);
            startActivity(telaFatura);
        }else if (id == R.id.imgTransferencia){
            Intent telaTransferencia = new Intent(MainActivity.this, Transferencia.class);
            startActivity(telaTransferencia);
        }else {
            Intent telaPoupanca = new Intent(MainActivity.this, Poupanca.class);
            startActivity(telaPoupanca);
        }
    }
}