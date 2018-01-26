package com.app.armetech.ajudae.aulas.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.armetech.ajudae.infra.DataBase;
import com.app.armetech.ajudae.aulas.domain.Subject;

import java.util.ArrayList;

/**
 * Created by Emerson on 18/12/2017.
 */

public class SubjectDao{
    private DataBase dbHelper;
    private SQLiteDatabase db;

    public SubjectDao(Context context){
        dbHelper = new DataBase(context);
    }

    public ArrayList getSubjects(){
        db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + DataBase.getSubjectTable();
        Cursor cursor = db.rawQuery(query, null);
        Subject subject = new Subject("SI1","Escolha uma cadeira");
        ArrayList<Subject> subjects = new ArrayList<Subject>();
        subjects.add(subject);
        while(cursor.moveToNext()){
            subject = createSubject(cursor);
            subjects.add(subject);
        }
        cursor.close();
        db.close();
        return subjects;
    }


    public ArrayList getSubjectsByDept(String Department){
        db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + DataBase.getSubjectTable() +
                " WHERE " + DataBase.getSubjectDept() + " LIKE ?";
        String[] args = {Department};
        Cursor cursor = db.rawQuery(query, args);
        Subject subject = new Subject("SI1","Escolha uma cadeira");
        ArrayList<Subject> subjects = new ArrayList<Subject>();
        subjects.add(subject);
        while(cursor.moveToNext()){
            subject = createSubject(cursor);
            subjects.add(subject);
        }
        cursor.close();
        db.close();
        return subjects;
    }
    public ArrayList getDepartments(){
        db = dbHelper.getReadableDatabase();
        String query = "SELECT DISTINCT DEPT FROM " + DataBase.getSubjectTable();
        Cursor cursor = db.rawQuery(query,null);
        String department = "Escolha um Departamento";
        ArrayList<String> departments = new ArrayList<String>();
        departments.add(department);
        while(cursor.moveToNext()){
            department = cursor.getString(0);
            departments.add(department);
        }
        cursor.close();
        db.close();
        return departments;
    }
    public Subject createSubject(Cursor cursor){
        String colunaId = DataBase.getSubjectId();
        int indexColunaId= cursor.getColumnIndex(colunaId);
        int id = cursor.getInt(indexColunaId);

        String colunaDept = DataBase.getSubjectDept();
        int indexColunaDept = cursor.getColumnIndex(colunaDept);
        String Dept = cursor.getString(indexColunaDept);

        String colunaNome = DataBase.getSubjectName();
        int indexColunaNome = cursor.getColumnIndex(colunaNome);
        String nome = cursor.getString(indexColunaNome);

        Subject subject = new Subject("SI1",nome);
        subject.setIdSubject(id);
        subject.setDepartment(Dept);
        return subject;

    }
}

