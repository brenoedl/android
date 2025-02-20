package com.brenoedl.appdelivery;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class FormCadastro extends AppCompatActivity {
    private CircleImageView civFotoUsuarioCad;
    private Button btSelecionarFotoCad, btCadastrar;
    private EditText etNomeCad, etEmailCad, etSenhaCad;
    private TextView tvMensagemErroCad;
    private InputMethodManager imm;
    private Uri bSelecionarUri;
    private String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);

        iniciarComponentes();

        etNomeCad.addTextChangedListener(cadastroTextWatcher);
        etEmailCad.addTextChangedListener(cadastroTextWatcher);
        etSenhaCad.addTextChangedListener(cadastroTextWatcher);

        btSelecionarFotoCad.setOnClickListener(view -> selecionarFoto());

        btCadastrar.setOnClickListener(this::cadastrarUsuario);
    }

    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK) {
            Intent data = result.getData();
            assert data != null;
            bSelecionarUri = data.getData();
            try {
                civFotoUsuarioCad.setImageURI(bSelecionarUri);
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

        private void cadastrarUsuario(View view) {
            String email = etEmailCad.getText().toString().trim();
            String senha = etSenhaCad.getText().toString();

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    salvarDadosUsuario();
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    Snackbar snackbar = Snackbar.make(view, getString(R.string.sucesso_cadastro), Snackbar.LENGTH_INDEFINITE)
                            .setAction(getString(R.string.bt_ok), view2 -> finish());
                    snackbar.show();
                    tvMensagemErroCad.setText("");
                } else {
                    String erro;
                    try {
                        throw Objects.requireNonNull(task.getException());
                    } catch (FirebaseAuthWeakPasswordException e) {
                        erro = getString(R.string.erro_senha_pequena);
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        erro = getString(R.string.erro_email);
                    } catch (FirebaseAuthUserCollisionException e) {
                        erro = getString(R.string.erro_conta_duplicada);
                        etEmailCad.setText("");
                    } catch (FirebaseNetworkException e) {
                        erro = getString(R.string.erro_internet);
                    } catch (Exception e) {
                        erro = getString(R.string.erro_cadastro);
                    }
                    tvMensagemErroCad.setText(erro);
                }
            });
        }

        private void salvarDadosUsuario() {
            String nomeArquivo = UUID.randomUUID().toString();
            final StorageReference reference = FirebaseStorage.getInstance().getReference("/imagens_usuarios/" + nomeArquivo);
            reference.putFile(bSelecionarUri).addOnSuccessListener(taskSnapshot -> {
                reference.getDownloadUrl().addOnSuccessListener(uri -> {
                    String foto = uri.toString();

                    usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    FirebaseFirestore bd = FirebaseFirestore.getInstance();
                    String nome = etNomeCad.getText().toString().trim();
                    Map<String, Object> usuarios = new HashMap<>();
                    usuarios.put("nome", nome);
                    usuarios.put("foto", foto);


                    DocumentReference documentReference = bd.collection("usuarios").document(usuarioID);

                    documentReference.set(usuarios).addOnSuccessListener(unused -> {
                        Log.i("db", getString(R.string.sucesso_dados));
                    }).addOnFailureListener(e -> {
                        Log.i("db_error", getString(R.string.falha_dados));
                    });
                }).addOnFailureListener(e -> {
                });
            }).addOnFailureListener(e -> {
            });
        }

        private void iniciarComponentes(){
            civFotoUsuarioCad = findViewById(R.id.civFotoUsuarioCad);
            btSelecionarFotoCad = findViewById(R.id.btSelecionarFotoCad);
            etNomeCad = findViewById(R.id.etnomeCad);
            etEmailCad = findViewById(R.id.etEmailCad);
            etSenhaCad = findViewById(R.id.etSenhaCad);
            tvMensagemErroCad = findViewById(R.id.tvMensagemErroCad);
            btCadastrar = findViewById(R.id.btCadastrar);
            imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        }

        TextWatcher cadastroTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String nome = etNomeCad.getText().toString().trim();
                String email = etEmailCad.getText().toString().trim();
                String senha = etSenhaCad.getText().toString();

                if (!nome.isEmpty() && !email.isEmpty() && !senha.isEmpty()) {
                    btCadastrar.setEnabled(true);
                } else {
                    btCadastrar.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };
    }