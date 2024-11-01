package com.example.conversordetemperatura;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActivityFpC extends AppCompatActivity {
    EditText fahrenheit;
    LinearLayout layResult;
    TextView formula, semsacao;
    ImageView img;
    InputMethodManager imm;
    Intent tela;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fpc);
        fahrenheit = findViewById(R.id.editTemperatura3);
        layResult = findViewById(R.id.layResult3);
        formula = findViewById(R.id.txtFormula3);
        semsacao = findViewById(R.id.txtSensacao3);
        img = findViewById(R.id.imgSensacao3);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        layResult.setVisibility(View.INVISIBLE);
    }

    public void calcular(View view) {
        boolean ok = true;
        if (fahrenheit.getText().toString().trim().isEmpty()){
            ok = false;
            fahrenheit.setError(getString(R.string.erro));
        }
        if (ok){
            layResult.setVisibility(View.VISIBLE);
            imm.hideSoftInputFromWindow(fahrenheit.getWindowToken(), 0);
            float f = Float.parseFloat(fahrenheit.getText().toString().trim());
            float c = (f - 32) * 5 / 9;
            String form = "(" + f + getString(R.string.simbolofahrenheit) + getString(R.string.formulaFpC) + c + getString(R.string.simboloCelsus);
            formula.setText(form);
            if (c <= 22.0){
                semsacao.setText(getString(R.string.frio));
                img.setImageResource(R.drawable.img_frio);
                img.setContentDescription(getString(R.string.imgFrio));
            }else if (c <= 30.0){
                semsacao.setText(getString(R.string.agradavel));
                img.setImageResource(R.drawable.img_agradavel);
                img.setContentDescription(getString(R.string.imgAgradavel));
            }else {
                semsacao.setText(getString(R.string.calor));
                img.setImageResource(R.drawable.img_calor);
                img.setContentDescription(getString(R.string.imgCalor));
            }
        }
    }

    public void voltar(View view) {
        tela = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(tela);
    }
}