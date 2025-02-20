package com.brenoedl.blocodenotas;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private AnotacaoPreferencias preferencias;
    private EditText editBlocoNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        preferencias = new AnotacaoPreferencias(getApplicationContext());
        editBlocoNotas = findViewById(R.id.blocoNotas);
        FloatingActionButton btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String anotaca = editBlocoNotas.getText().toString();
                if (anotaca.trim().isEmpty()){
                    Toast.makeText(getApplicationContext(), getString(R.string.erro_anotaccao), Toast.LENGTH_LONG).show();
                }else {
                    preferencias.salvarAnotacao(anotaca);
                    Toast.makeText(getApplicationContext(), getString(R.string.msg_sucessp), Toast.LENGTH_LONG).show();

                }
            }
        });
        String anotacao = preferencias.recuperarAnotacao();
        if (!anotacao.trim().isEmpty()){
            editBlocoNotas.setText(anotacao);
        }
    }
}