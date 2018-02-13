package com.app.armetech.ajudae.user.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.app.armetech.ajudae.classes.dao.SubjectDao;
import com.app.armetech.ajudae.classes.domain.Subject;
import com.app.armetech.ajudae.utils.DataBaseHelper;
import com.app.armetech.ajudae.user.domain.Session;
import com.app.armetech.ajudae.user.domain.User;

import java.util.List;


public class UserDao {
    private DataBaseHelper dbHelper;
    private SQLiteDatabase database;
    private SubjectDao subjectDao;
    private String userTable = DataBaseHelper.getUserTable();
    private String userIdColumn = DataBaseHelper.getUserId();
    private String emailColumn = DataBaseHelper.getUserEmail();
    private String passwordColumn = DataBaseHelper.getUserPassword();
    private String userSubjectTable = DataBaseHelper.getUserSubjectTable();
    private String userSubjectIdColumn = DataBaseHelper.getUserSubjectId();
    private String userSubjectOwnerIdColumn = DataBaseHelper.getUserSubjectOwnerId();
    private String userSubjectNameIdColumn = DataBaseHelper.getUserSubjectNameId();
    private String userSubjectTypeColumn = DataBaseHelper.getUserSubjectType();
    private User user;

    public UserDao(Context context){
        subjectDao = new SubjectDao(context);
        dbHelper = new DataBaseHelper(context);
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
        long id = cursor.getInt(indexIdColumn);

        int indexEmailColumn = cursor.getColumnIndex(emailColumn);
        String email = cursor.getString(indexEmailColumn);

        int indexPasswordColumn = cursor.getColumnIndex(passwordColumn);
        String password = cursor.getString(indexPasswordColumn);

        List<Subject> subjectsHelper = subjectDao.getSubjectsHelperByUserId(id);
        List<Subject> subjectsHelped = subjectDao.getSubjectsHelpedByUserId(id);

        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setCourse("BACHARELADO EM SISTEMAS DE INFORMAÇÃO");
        user.setSubjectsHelper(subjectsHelper);
        user.setSubjectsHelped(subjectsHelped);
        return user;
    }

    public void insertUserSubjects() {
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        user = Session.getLoggedUser();
        List<Subject> userSubjectsHelper = user.getSubjectsHelper();
        List<Subject> userSubjectsHelped = user.getSubjectsHelped();
        String userId = Long.toString(user.getId());

        //type = 1 (cadeiras a ajudar), type = 2 (cadeiras para ser ajudado)

        for(Subject subject : userSubjectsHelper) {
            Log.i("SHOW: ", "NUM: " + subject.getId());
            values.put(userSubjectOwnerIdColumn, userId);
            values.put(userSubjectNameIdColumn, Long.toString(subject.getId()));
            values.put(userSubjectTypeColumn, Integer.toString(1));
            database.insert(userSubjectTable, null, values);
        }

        for(Subject subject : userSubjectsHelped) {
            Log.i("SHOW: ", "NUM: " + subject.getId());
            values.put(userSubjectOwnerIdColumn, userId);
            values.put(userSubjectNameIdColumn, Long.toString(subject.getId()));
            values.put(userSubjectTypeColumn, Integer.toString(2));
            database.insert(userSubjectTable, null, values);
        }

    }



}
