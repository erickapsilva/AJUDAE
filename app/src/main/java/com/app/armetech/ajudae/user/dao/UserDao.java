package com.app.armetech.ajudae.user.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.app.armetech.ajudae.infra.DataBase;
import com.app.armetech.ajudae.user.domain.Session;
import com.app.armetech.ajudae.user.domain.User;


public class UserDao {
    private DataBase dbHelper;
    private SQLiteDatabase database;
    private String userTable = DataBase.getUserTable();
    private String userIdColumn = DataBase.getUserId();
    private String emailColumn = DataBase.getUserEmail();
    private String passwordColumn = DataBase.getUserPassword();
    private String subjectsHelpedColumn = DataBase.getUserSubjectsHelped();
    private String subjectsHelperColumn = DataBase.getUserSubjectsHelper();
    private User user;

    public UserDao(Context context){
        dbHelper = new DataBase(context);
    }

    public User getUserById(long id){
        database = dbHelper.getReadableDatabase();

        String query = "SELECT * FROM " + userTable + " WHERE " + userIdColumn + " LIKE ?";

        String stringId = Long.toString(id);
        String[] arguments = {stringId};
        Cursor cursor = database.rawQuery(query, arguments);

        User user = null;

        if(cursor.moveToNext()){
            user = createUser(cursor);
        }

        cursor.close();
        database.close();

        return user;
    }

    public User getUserByEmail(String email) {
        database = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + userTable + " WHERE " + emailColumn + " LIKE ?";
        String[] arguments = {email};
        Cursor cursor = database.rawQuery(query, arguments);
        User user = null;
        if (cursor.moveToNext()) {
            user = createUser(cursor);
        }
        cursor.close();
        return user;
    }

    public User getUserLogin(String email, String password){
        database = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + userTable + " WHERE " + emailColumn + " LIKE ? AND " + passwordColumn + " LIKE ?";
        String[] arguments = {email,password};
        Cursor cursor = database.rawQuery(query, arguments);
        User user = null;
        if (cursor.moveToNext()){
            user = createUser(cursor);
        }
        cursor.close();
        database.close();
        return user;
    }

    public long insertUser(User user){
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String email = user.getEmail();
        values.put(emailColumn, email);

        String password = user.getPassword();
        values.put(passwordColumn, password);

        long id = database.insert(userTable, null, values);
        database.close();
        return id;
    }

    public User createUser(Cursor cursor){
        int indexIdColumn= cursor.getColumnIndex(userIdColumn);
        int id = cursor.getInt(indexIdColumn);

        int indexEmailColumn = cursor.getColumnIndex(emailColumn);
        String email = cursor.getString(indexEmailColumn);

        int indexPasswordColumn = cursor.getColumnIndex(passwordColumn);
        String password = cursor.getString(indexPasswordColumn);

        int indexSubjectsHelpedColumn = cursor.getColumnIndex(subjectsHelpedColumn);
        String subjectsHelped = cursor.getString(indexSubjectsHelpedColumn);

        int indexSujectsHelperColumn = cursor.getColumnIndex(subjectsHelperColumn);
        String subjectsHelper = cursor.getString(indexSujectsHelperColumn);

        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);
        if(subjectsHelped != null)
            user.setSubjectsHelped(subjectsHelped);
        if(subjectsHelper != null)
            user.setSubjectsHelper(subjectsHelper);
        return user;
    }

    public void updateUserSubjects() {
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        user = Session.getLoggedUser();

        String subjectsHelped = user.getSubjectsHelpedAsString();
        values.put(subjectsHelpedColumn, subjectsHelped);

        String subjectsHelper = user.getSubjectsHelperAsString();
        values.put(subjectsHelperColumn, subjectsHelper);

        Log.i("TAGHELPED", subjectsHelped);
        Log.i("TAGHELPER", subjectsHelper);

        database.update(userTable, values, userIdColumn + "=" + user.getId(), null);
    }



}
