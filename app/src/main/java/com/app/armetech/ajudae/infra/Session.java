package com.app.armetech.ajudae.infra;

import com.app.armetech.ajudae.user.domain.Person;

/**
 * Created by user on 10/12/2017.
 */

public final class Session {
    private static int ID_LOGADO;
    private static Person USER_LOGADO;


    public Person getUsuarioLogado() {
        return USER_LOGADO;
    }

    public void setUsuarioLogado(Person usuarioLogado) {
        USER_LOGADO = usuarioLogado;
    }

    public int getid(){
        return ID_LOGADO;
    }


    public void logar(int id){
        ID_LOGADO = id;
    }

    public void deslogar(){
        ID_LOGADO = 0;
    }

}
