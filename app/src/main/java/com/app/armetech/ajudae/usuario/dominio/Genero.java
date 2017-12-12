package com.app.armetech.ajudae.usuario.dominio;

/**
 * Created by user on 10/12/2017.
 */

public enum Genero {
    FEMININO("F"), MASCULINO("M"), OUTRO("O");
    private String sexoValor;


    Genero(String valor) {
        this.sexoValor = valor;
    }

    public String getGeneroValor(){
        return this.sexoValor;
    }
}
