package com.brenoedl.appdelivery;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class FormLogin extends AppCompatActivity {
    private EditText etEmail, etSenha;
    private Button btEntrar;
    private TextView tvMensagemErro, tvCriarConta;
    private ProgressBar pbEntrar;
    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);
        Objects.requireNonNull(getSupportActionBar()).hide();

        iniciarComponentes();

        btEntrar.setOnClickListener(view -> {
            String email = etEmail.getText().toString().trim();
            String senha = etSenha.getText().toString();

            if (email.isEmpty() || senha.isEmpty()) {
                tvMensagemErro.setText(getString(R.string.erro_campo));;
            } else {
                tvMensagemErro.setText("");
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                autenticarUsuario(email, senha);
            }
        });

        tvCriarConta.setOnClickListener(view -> {
            Intent intent = new Intent(FormLogin.this, FormCadastro.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser();
        if (usuarioAtual != null) {
            iniciarTelaProdutos();
        };
    }

    private void autenticarUsuario(String email, String senha) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                pbEntrar.setVisibility(View.VISIBLE);
                new Handler(Looper.getMainLooper()).postDelayed((this::iniciarTelaProdutos), 3000);
            } else {
                String erro;
                try {
                    throw Objects.requireNonNull(task.getException());
                } catch (FirebaseAuthWeakPasswordException e) {
                    erro = getString(R.string.erro_senha_pequena);
                } catch (FirebaseAuthInvalidCredentialsException e) {
                    erro = getString(R.string.erro_email);
                } catch (FirebaseNetworkException e) {
                    erro = getString(R.string.erro_internet);
                } catch (Exception e) {
                    erro = getString(R.string.erro_login);
                }
                tvMensagemErro.setText(erro);
            }
        });
    }

    private void iniciarTelaProdutos() {
        Intent intent = new Intent(FormLogin.this, ListaProdutos.class);
        startActivity(intent);
        finish();
    }

    private void iniciarComponentes() {
        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);
        tvMensagemErro = findViewById(R.id.tvMensagemErro);
        btEntrar = findViewById(R.id.btEntrar);
        tvCriarConta = findViewById(R.id.tvCriarConta);
        pbEntrar = findViewById(R.id.pbEntrar);
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
    }
}