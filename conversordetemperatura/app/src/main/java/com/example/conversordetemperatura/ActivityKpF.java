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

public class ActivityKpF extends AppCompatActivity {
    EditText kelvin;
    LinearLayout layResult;
    TextView formula, semsacao;
    ImageView img;
    InputMethodManager imm;
    Intent tela;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kpf);
        kelvin = findViewById(R.id.editTemperatura6);
        layResult = findViewById(R.id.layResult6);
        formula = findViewById(R.id.txtFormula6);
        semsacao = findViewById(R.id.txtSensacao6);
        img = findViewById(R.id.imgSensacao6);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        layResult.setVisibility(View.INVISIBLE);
    }
    public void calcular(View view) {
        boolean ok = true;
        if (kelvin.getText().toString().trim().isEmpty()){
            ok = false;
            kelvin.setError(getString(R.string.erro));
        }
        if (ok){
            layResult.setVisibility(View.VISIBLE);
            imm.hideSoftInputFromWindow(kelvin.getWindowToken(), 0);
            float k = Float.parseFloat(kelvin.getText().toString().trim());
            float f = (k - 273.15f) * 9 / 5 + 32;
            String form = "(" + k + getString(R.string.simbolloKelvin) + getString(R.string.formulaKpF) + f + getString(R.string.simbolofahrenheit);
            formula.setText(form);
            if (f <= 71.6){
                semsacao.setText(getString(R.string.frio));
                img.setImageResource(R.drawable.img_frio);
                img.setContentDescription(getString(R.string.imgFrio));
            }else if (f <= 86.0){
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