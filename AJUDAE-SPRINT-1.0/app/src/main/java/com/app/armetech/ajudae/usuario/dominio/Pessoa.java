package com.app.armetech.ajudae.usuario.dominio;

/**
 * Created by user on 10/12/2017.
 */


import java.util.Date;

public class Pessoa {
    private long id;
    private String nome;
    private long idUsuario;
    private String dataNasc;
    private String genero;
    private String cep;

    public void setCep(String newCep){
        this.cep = newCep;
    }

    public String getCep(){
        return cep;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero){
        this.genero = genero;
    }



}
