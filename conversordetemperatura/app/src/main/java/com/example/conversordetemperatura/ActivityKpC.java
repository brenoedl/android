package com.example.conversordetemperatura;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;

public class ActivityKpC extends AppCompatActivity {
    EditText kelvin;
    LinearLayout layResult;
    TextView formula, semsacao;
    ImageView img;
    InputMethodManager imm;
    Intent tela;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kpc);
        kelvin = findViewById(R.id.editTemperatura5);
        layResult = findViewById(R.id.layResult6);
        formula = findViewById(R.id.txtFormula5);
        semsacao = findViewById(R.id.txtSensacao5);
        img = findViewById(R.id.imgSensacao5);
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
            float c = k - 273.15f;
            String form = k + getString(R.string.simbolloKelvin) + getString(R.string.formulaKpC) + c + getString(R.string.simboloCelsus);
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