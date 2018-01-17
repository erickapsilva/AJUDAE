package com.app.armetech.ajudae.user.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.armetech.ajudae.infra.DataBase;
import com.app.armetech.ajudae.user.domain.Person;
import com.app.armetech.ajudae.user.domain.User;

/**
 * Created by user on 10/12/2017.
 */

public class PersonDao {
    private DataBase dbHelper;
    private SQLiteDatabase db;


    public PersonDao(Context context){
        dbHelper = new DataBase(context);
    }

    public long insertPerson(Person person){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String table = DataBase.PERSON_TABLE;
        String nameCol = DataBase.PERSON_NAME;
        String genderCol = DataBase.PERSON_GENDER;
        String birthDateCol = DataBase.PERSON_BIRTHDATE;
        String cepCol = DataBase.PERSON_CEP;
        String idUserCol = DataBase.PERSON_USER_ID;

        String name = person.getName();
        values.put(nameCol, name);

        String gender = person.getGender();
        values.put(genderCol, gender);

        String date = person.getBirthDate();
        values.put(birthDateCol, date);

        String cep = person.getCep();
        values.put(cepCol, cep);

        long userId = person.getUserId();
        values.put(idUserCol, userId);

        long id = db.insert(table, null, values);
        db.close();
        return id;
    }

    public User createUser(Cursor cursor){
        String idCol = DataBase.USER_ID;
        int indexColId= cursor.getColumnIndex(idCol);
        int id = cursor.getInt(indexColId);

        String emailCol = DataBase.USER_EMAIL;
        int indexColunaEmail = cursor.getColumnIndex(emailCol);
        String email = cursor.getString(indexColunaEmail);

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
