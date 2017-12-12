package com.app.armetech.ajudae;

import com.app.armetech.ajudae.usuario.dominio.Pessoa;

/**
 * Created by user on 10/12/2017.
 */

public final class Sessao {
    private static int ID_LOGADO;
    private static Pessoa USER_LOGADO;


    public Pessoa getUsuarioLogado() {
        return USER_LOGADO;
    }

    public void setUsuarioLogado(Pessoa usuarioLogado) {
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
