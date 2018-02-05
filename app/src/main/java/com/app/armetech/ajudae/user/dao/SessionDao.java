package com.app.armetech.ajudae.user.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.armetech.ajudae.infra.DataBaseHelper;
import com.app.armetech.ajudae.user.domain.User;



public class SessionDao {
    private DataBaseHelper dbHelper;
    private SQLiteDatabase database;
    private UserDao userDao;
    private String sessionTable = DataBaseHelper.getSessionTable();
    private String sessionLoggedId = DataBaseHelper.getLoggedId();

    public SessionDao(Context context){
        dbHelper = new DataBaseHelper(context);
        userDao = new UserDao(context);
    }

    public void startSession(User user){
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        long userId = user.getId();
        values.put(sessionLoggedId,userId);

        database.insert(sessionTable,null,values);
        database.close();
    }

    public User recoversSession(){
        database = dbHelper.getReadableDatabase();

        String query = "SELECT * FROM " + sessionTable;
        Cursor cursor = database.rawQuery(query, null);
        User user = null;
        if (cursor.moveToNext()){
            int indexIdLogged = cursor.getColumnIndex(sessionLoggedId);
            long loggedId = cursor.getLong(indexIdLogged);
            user = userDao.getUserById(loggedId);
        }
        cursor.close();
        database.close();
        return user;
    }

    public void finishSession(){
        database = dbHelper.getWritableDatabase();
        database.delete(sessionTable,null, null);
        database.close();
    }


}
