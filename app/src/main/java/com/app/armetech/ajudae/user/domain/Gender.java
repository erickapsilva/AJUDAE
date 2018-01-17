package com.app.armetech.ajudae.user.domain;

/**
 * Created by user on 10/12/2017.
 */

public enum Gender {
    FEMININO("F"), MASCULINO("M"), OUTRO("O");
    private String genderValue;


    Gender(String valor) {
        this.genderValue = valor;
    }

    public String getGenderValue(){
        return this.genderValue;
    }
}
