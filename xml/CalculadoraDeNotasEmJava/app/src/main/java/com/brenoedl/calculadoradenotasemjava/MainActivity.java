package com.brenoedl.calculadoradenotasemjava;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText txtN1, txtN2, txtN3, txtN4, txtF;
    Button btnCalc;
    TextView tvResp;
    InputMethodManager imm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtN1 = findViewById(R.id.txtN1);
        txtN2 = findViewById(R.id.txtN2);
        txtN3 = findViewById(R.id.txtN3);
        txtN4 = findViewById(R.id.txtN4);
        txtF = findViewById(R.id.txtF);
        btnCalc = findViewById(R.id.btnCalc);
        tvResp = findViewById(R.id.tvResp);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean ok = true;
                if (txtN1.getText().toString().trim().isEmpty()){
                    ok = false;
                    txtN1.setError(getString(R.string.erro));
                }
                if (txtN2.getText().toString().trim().isEmpty()){
                    ok = false;
                    txtN2.setError(getString(R.string.erro));
                }
                if (txtN3.getText().toString().trim().isEmpty()){
                    ok = false;
                    txtN3.setError(getString(R.string.erro));
                }
                if (txtN4.getText().toString().trim().isEmpty()){
                    ok = false;
                    txtN4.setError(getString(R.string.erro));
                }
                if (txtF.getText().toString().trim().isEmpty()){
                    ok = false;
                    txtF.setError(getString(R.string.erro));
                }
                if (ok){
                    imm.hideSoftInputFromWindow(txtF.getWindowToken(), 0);
                    float n1 = Float.parseFloat(txtN1.getText().toString());
                    float n2 = Float.parseFloat(txtN2.getText().toString());
                    float n3 = Float.parseFloat(txtN3.getText().toString());
                    float n4 = Float.parseFloat(txtN4.getText().toString());
                    float m = (n1 + n2 + n3 + n4) / 4;
                    int f = Integer.parseInt(txtF.getText().toString());
                    if (m >= 5 && f <= 20){
                        String ma = getString(R.string.msgA) + "\n" + getString(R.string.msgMF) + String.format("%.1f", m);
                        tvResp.setText(ma);
                        tvResp.setTextColor(getColor(R.color.green));
                    }else if (f > 20){
                        String mrf = getString(R.string.msgRF) + "\n" + getString(R.string.msgMF) + String.format("%.1f", m);
                        tvResp.setText(mrf);
                        tvResp.setTextColor(getColor(R.color.red));
                    }else if (m < 5){
                        String mrn = getString(R.string.msgRN) + "\n" + getString(R.string.msgMF) + String.format("%.1f", m);
                        tvResp.setText(mrn);
                        tvResp.setTextColor(getColor(R.color.red));
                    }
                }
            }
        });
    }
}