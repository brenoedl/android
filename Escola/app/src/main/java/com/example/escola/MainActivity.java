package com.example.escola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edN1, edN2;
    TextView txtM, txtSit;
    ImageView imgSit;
    LinearLayout leyResultado;
    InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edN1 = findViewById(R.id.edN1);
        edN2 = findViewById(R.id.edN2);
        txtM = findViewById(R.id.txtM);
        txtSit = findViewById(R.id.txtSit);
        imgSit = findViewById(R.id.imgSit);
        leyResultado = findViewById(R.id.leyResultado);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        leyResultado.setVisibility(View.INVISIBLE);
    }

    public void calcular(View view) {
        boolean ok = true;
        if (edN1.getText().toString().trim().isEmpty()) {
            ok = false;
            edN1.setError(getString(R.string.erro));
        }
        if (edN2.getText().toString().trim().isEmpty()) {
            ok = false;
            edN2.setError(getString(R.string.erro));
        }
        if (ok) {
            imm.hideSoftInputFromWindow(edN2.getWindowToken(), 0);
            leyResultado.setVisibility(View.VISIBLE);
            float n1 = Float.parseFloat(edN1.getText().toString());
            float n2 = Float.parseFloat(edN2.getText().toString());
            float m = (n1 + n2) / 2;
            txtM.setText(String.format("%.1f", m));
            if (m < 5) {
                txtSit.setText(getString(R.string.rep));
                Toast.makeText(getApplicationContext(), getString(R.string.msgRep), Toast.LENGTH_LONG).show();
                imgSit.setImageResource(R.drawable.emojireprovado);
            } else if (m < 7) {
                txtSit.setText(getString(R.string.rec));
                Toast.makeText(getApplicationContext(), getString(R.string.msgRec), Toast.LENGTH_LONG).show();
                imgSit.setImageResource(R.drawable.emojirecuperacao);
            } else {
                txtSit.setText(getString(R.string.apr));
                Toast.makeText(getApplicationContext(), getString(R.string.msgApr), Toast.LENGTH_LONG).show();
                imgSit.setImageResource(R.drawable.emojiaprovado );
            }
        }
    }
}