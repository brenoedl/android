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

public class ActivityCpK extends AppCompatActivity {
    EditText celsus;
    LinearLayout layResult;
    TextView formula, semsacao;
    ImageView img;
    InputMethodManager imm;
    Intent tela;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpk);
        celsus = findViewById(R.id.editTemperatura2);
        layResult = findViewById(R.id.layResult2);
        formula = findViewById(R.id.txtFormula2);
        semsacao = findViewById(R.id.txtSensacao2);
        img = findViewById(R.id.imgSensacao2);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        layResult.setVisibility(View.INVISIBLE);
    }

    public void calcular(View view) {
        boolean ok = true;
        if (celsus.getText().toString().trim().isEmpty()){
            ok = false;
            celsus.setError(getString(R.string.erro));
        }
        if (ok){
            layResult.setVisibility(View.VISIBLE);
            imm.hideSoftInputFromWindow(celsus.getWindowToken(), 0);
            float c = Float.parseFloat(celsus.getText().toString().trim());
            float k = c + 273.15f;
            String form = c + getString(R.string.simboloCelsus) + getString(R.string.formulaCpK) + k + getString(R.string.simbolloKelvin);
            formula.setText(form);
            if (k <= 295.15){
                semsacao.setText(getString(R.string.frio));
                img.setImageResource(R.drawable.img_frio);
                img.setContentDescription(getString(R.string.imgFrio));
            }else if (k <= 303.15){
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