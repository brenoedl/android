package com.brenoedl.delivery;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Objects;

public class FormLogin extends AppCompatActivity {
    private TextView txtCriarConta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_logn);

        Objects.requireNonNull(getSupportActionBar()).hide();
        inciarComponentes();

        txtCriarConta.setOnClickListener(v -> {
            Intent intent = new Intent(FormLogin.this, FormCadastro.class);
            startActivity(intent);
        });
    }

    public void inciarComponentes(){
        txtCriarConta = findViewById(R.id.txtCriarConta);
    }
}