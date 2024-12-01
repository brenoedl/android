package com.brrenoedl.appdelivery;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Objects;

public class FormCadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);
        Objects.requireNonNull(getSupportActionBar()).hide();
    }
}