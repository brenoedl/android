package com.example.estudonauta;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class Cursos extends AppCompatActivity {
    Intent nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos);
    }

    public void clickVoltar(View view) {
        finish();
    }

    public void clickCursoPortugol(View view) {
        nav = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.estudonauta.com/curso/programacao-basica/"));
        startActivity(nav);
    }

    public void clickCursoPhp(View view) {
        nav = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.estudonauta.com/curso/php-com-banco-de-dados/"));
        startActivity(nav);
    }

    public void clickCursoAndroid(View view) {
        nav = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.estudonauta.com/curso/android-studio/"));
        startActivity(nav);
    }

    public void clickCursoLinC(View view) {
        nav = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.estudonauta.com/curso/linguagem-c/"));
        startActivity(nav);
    }

    public void clickCursoLinCSharp(View view) {
        nav = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.estudonauta.com/curso/c/"));
        startActivity(nav);
    }

    public void clickCursoKotllin(View view) {
        nav = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.estudonauta.com/curso/kotlin/"));
        startActivity(nav);
    }

    public void clickCursoProducaoVideos(View view) {
        nav = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.estudonauta.com/curso/producao-de-videos/"));
        startActivity(nav);
    }
}