package com.app.armetech.ajudae.user.domain;


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
