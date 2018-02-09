package com.app.armetech.ajudae.classes.domain;

/**
 * Created by aicsb on 08/02/2018.
 */

public class DataObjectAusences {
    private String cadeira;
    private String podeFaltar;
    private String faltou;

    public DataObjectAusences (String cadeira, String podeFaltar, String faltou){
        this.cadeira = cadeira;
        this.podeFaltar = podeFaltar;
        this.faltou = faltou;
    }

    public String getCadeira() {
        return cadeira;
    }

    public void setCadeira(String cadeira) {
        this.cadeira = cadeira;
    }

    public String getPodeFaltar() {
        return podeFaltar;
    }

    public void setPodeFaltar(String podeFaltar) {
        this.podeFaltar = podeFaltar;
    }

    public String getFaltou() {
        return faltou;
    }

    public void setFaltou(String faltou) {
        this.faltou = faltou;
    }
}
