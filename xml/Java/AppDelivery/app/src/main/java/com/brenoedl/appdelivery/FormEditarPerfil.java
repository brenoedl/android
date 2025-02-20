package com.brenoedl.appdelivery;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class FormEditarPerfil extends AppCompatActivity {
    private CircleImageView civFotoUsuarioAtualizar;;
    private Button btSelecionarFotoAtualizar, btAtualizar;
    private EditText etnomeAtualizar;
    private Uri bSelecionarUri;
    private String usuarioID;
    private InputMethodManager imm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_editar_perfil);
        iniciarComponentes();

        btSelecionarFotoAtualizar.setOnClickListener(view -> selecionarFoto());
        btAtualizar.setOnClickListener(v -> {
            String nome = etnomeAtualizar.getText().toString().trim();
            if (!nome.isEmpty()) {
                atualizarDadosUsuario();
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }else {
                etnomeAtualizar.setError(getString(R.string.erro_campo));
            }
        });
    }

    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK) {
            Intent data = result.getData();
            assert data != null;
            bSelecionarUri = data.getData();
            try {
                civFotoUsuarioAtualizar.setImageURI(bSelecionarUri);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    });

    private void selecionarFoto() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        activityResultLauncher.launch(intent);
    }

    private void atualizarDadosUsuario() {
        String nomeArquivo = UUID.randomUUID().toString();
        final StorageReference reference = FirebaseStorage.getInstance().getReference("/imagens_usuarios/" + nomeArquivo);
        reference.putFile(bSelecionarUri).addOnSuccessListener(taskSnapshot -> {
            reference.getDownloadUrl().addOnSuccessListener(uri -> {
                String foto = uri.toString();

                usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                FirebaseFirestore bd = FirebaseFirestore.getInstance();
                String nome = etnomeAtualizar.getText().toString().trim();

                Task<Void> documentReference = bd.collection("usuarios").document(usuarioID)
                        .update("nome", nome, "foto", foto).addOnSuccessListener(unused -> {
                            Log.i("db", getString(R.string.sucesso_dados));
                            Intent intent = new Intent(FormEditarPerfil.this, PerfilUsuario.class);
                            startActivity(intent);
                            finish();
                        }).addOnFailureListener(e -> {
                            Log.i("db_error", getString(R.string.falha_dados));
                        });
            }).addOnFailureListener(e -> {
            });
        }).addOnFailureListener(e -> {
        });
    }

    private void iniciarComponentes(){
        civFotoUsuarioAtualizar = findViewById(R.id.civFotoUsuarioAtualizar);
        btSelecionarFotoAtualizar = findViewById(R.id.btSelecionarFotoAtualizar);
        etnomeAtualizar = findViewById(R.id.etnomeAtualizar);
        btAtualizar = findViewById(R.id.btAtualizar);
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

    }
}