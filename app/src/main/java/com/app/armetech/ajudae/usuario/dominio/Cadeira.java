package com.app.armetech.ajudae.usuario.dominio;

/**
 * Created by roxac on 17/12/17.
 */

public class Cadeira {

    private String nome, turma;

    public Cadeira(String turma, String nome) {
        this.turma = turma;
        this.nome = nome;
    }

    public String getTurma() {
        return "TURMA: " + turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
