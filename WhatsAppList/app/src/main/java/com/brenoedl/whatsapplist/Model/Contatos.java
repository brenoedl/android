package com.brenoedl.whatsapplist.Model;

public class Contatos {
    private int fotto;
    private String nome;
    private String mensagem;

    public Contatos(int fotto, String nome, String mensagem) {
        this.fotto = fotto;
        this.nome = nome;
        this.mensagem = mensagem;
    }

    public int getFotto() {
        return fotto;
    }

    public void setFotto(int fotto) {
        this.fotto = fotto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
