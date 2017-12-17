package com.app.armetech.ajudae.usuario.dominio;

import com.app.armetech.ajudae.Sessao;

/**
 * Created by user on 10/12/2017.
 */

public class Usuario {
    private String email;
    private String senha;
    private String Token;
    private String curso;
    private Sessao sessao = new Sessao();
    private long id;
    private Pessoa pessoa = new Pessoa();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
    public Pessoa getPessoa(){
        return this.pessoa;
    }


}
