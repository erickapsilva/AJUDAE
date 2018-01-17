package com.app.armetech.ajudae.user.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.armetech.ajudae.infra.DataBase;
import com.app.armetech.ajudae.user.domain.User;

/**
 * Created by user on 10/12/2017.
 */

public class UserDao {
    private DataBase dbHelper;
    private SQLiteDatabase db;


    public UserDao(Context context){
        dbHelper = new DataBase(context);
    }

    public User getUserByEmail(String email) {

        db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + DataBase.USER_TABLE +
                " WHERE " + DataBase.USER_EMAIL + " LIKE ?";
        String[] args = {email};
        Cursor cursor = db.rawQuery(query, args);
        User user = null;
        if (cursor.moveToNext()) {
            user = createUser(cursor);
        }
        cursor.close();
        return user;
    }
    public User getUserLogin(String email, String password){
        db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + DataBase.USER_TABLE +
                " WHERE " + DataBase.USER_EMAIL + " LIKE ? AND " +
                DataBase.USER_PASS + " LIKE ?";
        String[] args = {email,password};
        Cursor cursor = db.rawQuery(query, args);
        User user = null;
        if (cursor.moveToNext()){
            user = createUser(cursor);
        }
        cursor.close();
        db.close();
        return user;
    }

    public long insertUser(User user){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String table = DataBase.USER_TABLE;
        String emailCol = DataBase.USER_EMAIL;
        String passwordCol = DataBase.USER_PASS;
        String tokenCol = DataBase.USER_TOKEN;
        String courseCol = DataBase.USER_COURSE;

        values.put(tokenCol,"eita");
        values.put(courseCol,"eita");

        String email = user.getEmail();
        values.put(emailCol, email);

        String password = user.getPassword();
        values.put(passwordCol, password);

        long id = db.insert(table, null, values);
        db.close();
        return id;
    }

    public User createUser(Cursor cursor){
        String idCol = DataBase.USER_ID;
        int indexIdCol= cursor.getColumnIndex(idCol);
        int id = cursor.getInt(indexIdCol);

        String emailCol = DataBase.USER_EMAIL;
        int indexEmailCol = cursor.getColumnIndex(emailCol);
        String email = cursor.getString(indexEmailCol);

        String passwordCol = DataBase.USER_PASS;
        int indexPasswordCol = cursor.getColumnIndex(passwordCol);
        String password = cursor.getString(indexPasswordCol);

        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }

}
