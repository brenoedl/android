package com.brrenoedl.appdelivery;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Objects;

public class FormLogin extends AppCompatActivity {
    private EditText etEmailLogin, etSenhaLogin;
    private TextView tvMensagemErroLogin, tvCriarConta;
    private Button btEntrar;
    private ProgressBar pbLogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);
        Objects.requireNonNull(getSupportActionBar()).hide();

        iniciarComponentes();
    }

    public void iniciarComponentes() {
        etEmailLogin = findViewById(R.id.etEmailLogin);
        etSenhaLogin = findViewById(R.id.etSenhaLogin);
        tvMensagemErroLogin = findViewById(R.id.tvMensagemErroLogin);
        btEntrar = findViewById(R.id.btEntrar);
        tvCriarConta = findViewById(R.id.tvCriarConta);
        pbLogar = findViewById(R.id.pbLogar);
    }
}