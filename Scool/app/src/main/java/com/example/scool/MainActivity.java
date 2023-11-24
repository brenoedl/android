package com.example.scool;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edN1, edN2;
    TextView txtM, txtSit;
    LinearLayout leyResultado;
    ImageView imgSit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edN1 = findViewById(R.id.edN1);
        edN2 = findViewById(R.id.edN2);
        txtM = findViewById(R.id.txtM);
        txtSit = findViewById(R.id.txtSit);
        leyResultado = findViewById(R.id.leyResultado);
        imgSit = findViewById(R.id.imgSit);
    }

    @SuppressLint("DefaultLocale")
    public void calcular(View view) {
        boolean ok = true;
        if (edN1.getText().toString().trim().isEmpty()) {
            ok = false;
            edN1.setError(getString(R.string.msgErro));
        }
        if (edN2.getText().toString().trim().isEmpty()) {
            ok = false;
            edN2.setError(getString(R.string.msgErro));
        }
        if (ok) {
            //leyResultado.setVisibility(VISIBLE);
            float n1 = Float.parseFloat(edN1.getText().toString());
            float n2 = Float.parseFloat(edN2.getText().toString());
            float m = (n1 + n2) / 2;
            txtM.setText(String.format("%.1f", m));

            if (m < 5) {
                txtSit.setText(getString(R.string.sitRep));
                Toast.makeText(getApplicationContext(), getString(R.string.msgRp), Toast.LENGTH_SHORT).show();
            } else if (m < 7) {
                txtSit.setText(getString(R.string.sitRec));
                Toast.makeText(getApplicationContext(), getString(R.string.msgRc), Toast.LENGTH_SHORT).show();
            } else {
                txtSit.setText(getString(R.string.sitApr));
                Toast.makeText(getApplicationContext(), getString(R.string.msgAp), Toast.LENGTH_SHORT).show();
            }
        }
    }
}