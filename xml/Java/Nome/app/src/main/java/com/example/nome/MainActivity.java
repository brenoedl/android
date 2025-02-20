package com.example.nome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText nome;
    LinearLayout layResp;
    TextView txtResp;
    ImageView img;
    InputMethodManager imm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nome = findViewById(R.id.editPNome);
        layResp = findViewById(R.id.leyResp);
        txtResp = findViewById(R.id.txtResp);
        img = findViewById(R.id.imgResp);
        layResp.setVisibility(View.INVISIBLE);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    public void analizar(View view) {
        boolean ok = true;
        if (nome.getText().toString().trim().isEmpty()){
            ok = false;
            nome.setError(getString(R.string.msgErro));
        }
        if (ok){
            imm.hideSoftInputFromWindow(nome.getWindowToken(), 0);
            layResp.setVisibility(View.VISIBLE);
            String n = nome.getText().toString();
            if (n.trim().equals("Daniele") || n.trim().equals("daniele")){
                txtResp.setText(getString(R.string.fraseDani));
                img.setImageResource(R.drawable.img_namoro);
            }else if (n.trim().equals("Elis") || n.trim().equals("elis")){
                txtResp.setText(getString(R.string.fraseIrma));
                img.setImageResource(R.drawable.img_irmaos);
            }else if (n.trim().equals("Ellen") || n.trim().equals("ellen")){
                txtResp.setText(getString(R.string.fraseMae));
                img.setImageResource(R.drawable.img_mae);
            }else if (n.trim().equals("Josefa") || n.trim().equals("josefa")){
                txtResp.setText(getString(R.string.fraseVo));
                img.setImageResource(R.drawable.img_vo);
            }else if (n.trim().equals("Renan") || n.trim().equals("renan")){
                txtResp.setText(getString(R.string.fraseIrmao));
                img.setImageResource(R.drawable.img_irmaos);
            }else if (n.trim().equals("Roberto") || n.trim().equals("roberto")){
                txtResp.setText(getString(R.string.frasevoo));
                img.setImageResource(R.drawable.img_voo);
            }else {
                txtResp.setText(getString(R.string.fraseNao));
                img.setImageResource(R.drawable.img_nao);
            }
        }
    }
}