package com.app.armetech.ajudae.usuario.negocio;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.app.armetech.ajudae.Criptografia;
import com.app.armetech.ajudae.usuario.DAO.UsuarioDAO;
import com.app.armetech.ajudae.usuario.dominio.Usuario;


public class LoginNegocio {
    private Usuario usuario = new Usuario();
    private UsuarioDAO usuarioDAO;
    private Usuario usuarioPorEmail;
    private Usuario usuarioPorSenha;

    public LoginNegocio(Context context){
        usuarioDAO = new UsuarioDAO(context);
    }

    public Usuario getUsuario(){
        return this.usuario;
    }
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean validarBanco(){
        String senhaCriptograda = Criptografia.encryptPassword(this.getUsuario().getSenha());
        usuarioPorEmail = usuarioDAO.getUsuarioLogin(this.usuario.getEmail(),senhaCriptograda);
        if (usuarioPorEmail != null){
                return true;
        }else {
            return false;
        }
    }

}
