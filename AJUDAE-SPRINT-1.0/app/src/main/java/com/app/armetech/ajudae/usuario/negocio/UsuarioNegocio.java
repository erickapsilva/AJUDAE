package com.app.armetech.ajudae.usuario.negocio;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import com.app.armetech.ajudae.Criptografia;
import com.app.armetech.ajudae.usuario.DAO.PessoaDAO;
import com.app.armetech.ajudae.usuario.DAO.UsuarioDAO;
import com.app.armetech.ajudae.usuario.GUI.RegisterScreen;
import com.app.armetech.ajudae.usuario.dominio.Pessoa;
import com.app.armetech.ajudae.usuario.dominio.Usuario;

/**
 * Created by user on 10/12/2017.
 */

public class UsuarioNegocio {
    private PessoaDAO pessoaDAO;
    private UsuarioDAO usuarioDAO;
    private Pessoa pessoa;
    private Usuario usuario;


    public UsuarioNegocio(Context context){
        pessoaDAO = new PessoaDAO(context);
        usuarioDAO = new UsuarioDAO(context);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean cadastrar(String nome, String genero, String nasc, String cep, String email, String senha){
        Usuario verificacao = usuarioDAO.getUsuarioPorEmail(email);
        if (verificacao != null) {
            return false;
        } else {
            usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setSenha(Criptografia.encryptPassword(senha));
            long idUsuario = usuarioDAO.inserirUsuario(usuario);

            pessoa = new Pessoa();
            pessoa.setNome(nome);
            pessoa.setGenero(genero);
            pessoa.setDataNasc(nasc);
            pessoa.setIdUsuario(idUsuario);
            pessoa.setCep(cep);
            long idPessoa = pessoaDAO.inserirPessoa(pessoa);
            pessoa.setId(idPessoa);
            return true;

        }
    }
}
