package com.app.armetech.ajudae.usuario.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.armetech.ajudae.DataBase;
import com.app.armetech.ajudae.usuario.dominio.Pessoa;
import com.app.armetech.ajudae.usuario.dominio.Usuario;

/**
 * Created by user on 10/12/2017.
 */

public class PessoaDAO {
    private DataBase dbHelper;
    private SQLiteDatabase db;


    public PessoaDAO(Context context){
        dbHelper = new DataBase(context);
    }

    public long inserirPessoa(Pessoa pessoa){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_PESSOA;
        String colunaNome = DataBase.PESSOA_NOME;
        String colunaGenero = DataBase.PESSOA_GENERO;
        String colunaNascimento = DataBase.PESSOA_DATANASC;
        String colunaCEP = DataBase.PESSOA_CEP;
        String colunaIDUSER = DataBase.PESSOA_USER_ID;

        String nome = pessoa.getNome();
        values.put(colunaNome, nome);

        String genero = pessoa.getGenero();
        values.put(colunaGenero, genero);

        String data = pessoa.getDataNasc();
        values.put(colunaNascimento, data);

        String cep = pessoa.getCep();
        values.put(colunaCEP, cep);

        long userid = pessoa.getIdUsuario();
        values.put(colunaIDUSER, userid);

        long id = db.insert(tabela, null, values);
        db.close();
        return id;
    }

    public Usuario criaUsuario(Cursor cursor){
        String colunaId = DataBase.USUARIO_ID;
        int indexColunaId= cursor.getColumnIndex(colunaId);
        int id = cursor.getInt(indexColunaId);

        String colunaEmail = DataBase.USUARIO_EMAIL;
        int indexColunaEmail = cursor.getColumnIndex(colunaEmail);
        String email = cursor.getString(indexColunaEmail);

        String colunaSenha = DataBase.USUARIO_SENHA;
        int indexColunaSenha = cursor.getColumnIndex(colunaSenha);
        String senha = cursor.getString(indexColunaSenha);

        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        return usuario;
    }

}
