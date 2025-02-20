package com.brenoedl.appdelivery;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class PerfilUsuario extends AppCompatActivity {
    private CircleImageView civFotoUsuario;
    private TextView tvNomeUsuario, tvEmailUsuario;
    private Button btEditarPerfil;
    private String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);
        iniciarComponentes();

        btEditarPerfil.setOnClickListener(view -> {
            Intent intent = new Intent(PerfilUsuario.this, FormEditarPerfil.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        usuarioID = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        DocumentReference documentReference = db.collection("usuarios").document(usuarioID);

        documentReference.addSnapshotListener((documentSnapshot, error) -> {
            if (documentSnapshot != null) {
                Glide.with(getApplicationContext()).load(documentSnapshot.getString("foto")).into(civFotoUsuario);
                tvNomeUsuario.setText(documentSnapshot.getString("nome"));
                tvEmailUsuario.setText(email);
            }
        });
    }

    private void iniciarComponentes(){
        civFotoUsuario = findViewById(R.id.civFotoUsuario);
        tvNomeUsuario = findViewById(R.id.tvNomeUsuario);
        tvEmailUsuario = findViewById(R.id.tvEmailUsuario);
        btEditarPerfil = findViewById(R.id.btEditarPerfil);
    }
}