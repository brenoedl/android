package com.brenoedl.calculadoradeiimcemjava;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.brenoedl.calculadoradeiimcemjava.databinding.ActivityMainBinding;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    InputMethodManager imm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        binding.btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean ok = true;
                String peso = Objects.requireNonNull(binding.etPeso.getText()).toString();
                String alttura = Objects.requireNonNull(binding.etAltura.getText()).toString();
                if (peso.isEmpty()){
                    ok = false;
                    binding.etPeso.setError(getString(R.string.msg_ep));
                }
                if (alttura.isEmpty()){
                    ok = false;
                    binding.etAltura.setError(getString(R.string.msg_ea));
                }
                if (ok){
                    imm.hideSoftInputFromWindow(binding.etAltura.getWindowToken(), 0);
                    calcularIMC(peso, alttura);
                }
            }
        });

    }
    private void calcularIMC(String p, String a) {
        float peso = Float.parseFloat(p.replace(",", "."));
        float altura = Float.parseFloat(a.replace(",", "."));
        float imc = peso / (altura * altura);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        if (imc < 18.5) {
            String msg = getString(R.string.msg_imc) + decimalFormat.format(imc) + "\n" + getString(R.string.msg_bp);
            binding.tvResp.setText(msg);
        } else if (imc <= 24.9) {
            String msg = getString(R.string.msg_imc) + decimalFormat.format(imc) + "\n" + getString(R.string.msg_pn);
            binding.tvResp.setText(msg);
        }else if (imc <= 29.9){
            String msg = getString(R.string.msg_imc) + decimalFormat.format(imc) + "\n" + getString(R.string.msg_s );
            binding.tvResp.setText(msg);
        }else if (imc <= 34.9){
            String msg = getString(R.string.msg_imc) + decimalFormat.format(imc) + "\n" + getString(R.string.msg_og1);
            binding.tvResp.setText(msg);
        }else if (imc <= 39.9){
            String msg = getString(R.string.msg_imc) + decimalFormat.format(imc) + "\n" + getString(R.string.msg_og2);
            binding.tvResp.setText(msg);
        }else if (imc >= 40) {
            String msg = getString(R.string.msg_imc) + decimalFormat.format(imc) + "\n" + getString(R.string.msg_og3);
            binding.tvResp.setText(msg);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pricipal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.limpar){
            binding.etPeso.setText("");
            binding.etAltura.setText("");
            binding.tvResp.setText("");
        }
        return super.onOptionsItemSelected(item);
    }
}