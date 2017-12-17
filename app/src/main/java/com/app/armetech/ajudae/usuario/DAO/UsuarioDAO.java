package com.app.armetech.ajudae.usuario.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.app.armetech.ajudae.DataBase;
import com.app.armetech.ajudae.usuario.dominio.Usuario;

/**
 * Created by user on 10/12/2017.
 */

public class UsuarioDAO {
    private DataBase dbHelper;
    private SQLiteDatabase db;


    public UsuarioDAO(Context context){
        dbHelper = new DataBase(context);
    }

    public Usuario getUsuarioPorEmail(String email) {

        db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + DataBase.TABELA_USUARIO +
                " WHERE " + DataBase.USUARIO_EMAIL + " LIKE ?";
        String[] args = {email};
        Cursor cursor = db.rawQuery(query, args);
        Usuario usuario = null;
        if (cursor.moveToNext()) {
            usuario = criaUsuario(cursor);
        }
        cursor.close();
        return usuario;
    }
    public Usuario getUsuarioLogin(String email, String senha){
        db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + DataBase.TABELA_USUARIO +
                " WHERE " + DataBase.USUARIO_EMAIL + " LIKE ? AND " +
                DataBase.USUARIO_SENHA + " LIKE ?";
        String[] args = {email,senha};
        Cursor cursor = db.rawQuery(query, args);
        Usuario usuario = null;
        if (cursor.moveToNext()){
            usuario = criaUsuario(cursor);
        }
        cursor.close();
        db.close();
        return usuario;
    }

    public long inserirUsuario(Usuario usuario){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String tabela = DataBase.TABELA_USUARIO;
        String colunaEmail = DataBase.USUARIO_EMAIL;
        String colunaSenha = DataBase.USUARIO_SENHA;
        String colunaToken = DataBase.USUARIO_TOKEN;
        String colunacurso = DataBase.USUARIO_CURSO;

        values.put(colunaToken,"eita");
        values.put(colunacurso,"eita");

        String email = usuario.getEmail();
        values.put(colunaEmail, email);

        String senha = usuario.getSenha();
        values.put(colunaSenha, senha);

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
