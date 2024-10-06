package com.brenoedl.blocodenotas;

import android.content.Context;
import android.content.SharedPreferences;

public class AnotacaoPreferencias {
    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private final String NOME_ARQUIVO = "Anotacao.Preferencias";
    private final String CHAVE_NOME = "nome";


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public SharedPreferences getPreferences() {
        return preferences;
    }

    public void setPreferences(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public SharedPreferences.Editor getEditor() {
        return editor;
    }

    public void setEditor(SharedPreferences.Editor editor) {
        this.editor = editor;
    }

    public AnotacaoPreferencias(Context c) {
        this.setContext(c);
        this.setPreferences(this.getContext().getSharedPreferences(NOME_ARQUIVO, 0));
        setEditor(getPreferences().edit());
    }

    public void salvarAnotacao(String anotacao){
        this.getEditor().putString(CHAVE_NOME, anotacao);
        this.getEditor().commit();
    }

    public String recuperarAnotacao(){
        return this.getPreferences().getString(CHAVE_NOME, "");
    }
}
