package com.app.armetech.ajudae.user.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.app.armetech.ajudae.infra.DataBaseHelper;
import com.app.armetech.ajudae.user.domain.Person;


public class PersonDao {
    private DataBaseHelper dbHelper;
    private SQLiteDatabase database;
    private String personTable = DataBaseHelper.getPersonTable();
    private String personId = DataBaseHelper.getPersonId();
    private String nameColumn = DataBaseHelper.getPersonName();
    private String genderColumn = DataBaseHelper.getPersonGender();
    private String birthDateColumn = DataBaseHelper.getPersonBirthdate();
    private String cepColumn = DataBaseHelper.getPersonCep();
    private String idUserColumn = DataBaseHelper.getPersonUserId();

    public PersonDao(Context context){
        dbHelper = new DataBaseHelper(context);
    }

    public long insertPerson(Person person){
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        String name = person.getName();
        values.put(nameColumn, name);

        String gender = person.getGender();
        values.put(genderColumn, gender);

        String date = person.getBirthDate();
        values.put(birthDateColumn, date);

        String cep = person.getCep();
        values.put(cepColumn, cep);

        long userId = person.getUserId();
        values.put(idUserColumn, userId);

        long id = database.insert(personTable, null, values);
        database.close();
        return id;
    }

    public Person getPersonByUserId(long userId){
        database = dbHelper.getReadableDatabase();

        String query = "SELECT * FROM " + personTable + " WHERE " + idUserColumn + " LIKE ?";

        String stringId = Long.toString(userId);
        String[] arguments = {stringId};
        Cursor cursor = database.rawQuery(query, arguments);

        Person person = null;

        if(cursor.moveToNext()){
            person = createPerson(cursor);
        }

        cursor.close();
        database.close();

        return person;
    }

    public Person createPerson(Cursor cursor){
        int indexIdColumn= cursor.getColumnIndex(personId);
        int id = cursor.getInt(indexIdColumn);

        int indexNameColumn = cursor.getColumnIndex(nameColumn);
        String name = cursor.getString(indexNameColumn);

        int indexGenderColumn = cursor.getColumnIndex(genderColumn);
        String gender = cursor.getString(indexGenderColumn);

        int indexBirthDateColumn = cursor.getColumnIndex(birthDateColumn);
        String birthDate = cursor.getString(indexBirthDateColumn);

        int indexCepColumn = cursor.getColumnIndex(cepColumn);
        String cep = cursor.getString(indexCepColumn);

        int indexUserIdColumn = cursor.getColumnIndex(idUserColumn);
        int userId = cursor.getInt(indexUserIdColumn);

        Person person = new Person();
        person.setId(id);
        person.setName(name);
        person.setGender(gender);
        person.setBirthDate(birthDate);
        person.setCep(cep);
        person.setUserId(userId);
        return person;
    }

}
