package com.app.armetech.ajudae.classes.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.armetech.ajudae.utils.DataBaseHelper;
import com.app.armetech.ajudae.classes.domain.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emerson on 18/12/2017.
 */

public class SubjectDao{

    private DataBaseHelper dbHelper;
    private SQLiteDatabase db;
    private String subjectTableColumn = DataBaseHelper.getSubjectTable();
    private String subjectIdColumn = DataBaseHelper.getSubjectId();
    private String subjectDeptColumn = DataBaseHelper.getSubjectDept();
    private String subjectNameColumn = DataBaseHelper.getSubjectName();
    private String userSubjectTable = DataBaseHelper.getUserSubjectTable();
    private String userSubjectIdColumn = DataBaseHelper.getUserSubjectId();
    private String userSubjectOwnerIdColumn = DataBaseHelper.getUserSubjectOwnerId();
    private String userSubjectNameIdColumn = DataBaseHelper.getUserSubjectNameId();
    private String userSubjectTypeColumn = DataBaseHelper.getUserSubjectType();


    public SubjectDao(Context context){
        dbHelper = new DataBaseHelper(context);
    }

    public ArrayList getSubjects(){
        db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + subjectTableColumn;
        Cursor cursor = db.rawQuery(query, null);
        Subject subject = new Subject();
        ArrayList<Subject> subjects = new ArrayList<>();
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
        String query = "SELECT * FROM " + subjectTableColumn + " WHERE " + subjectDeptColumn + " LIKE ?";
        String[] args = {Department};
        Cursor cursor = db.rawQuery(query, args);
        Subject subject = new Subject();
        ArrayList<Subject> subjects = new ArrayList<>();
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
        String query = "SELECT DISTINCT DEPT FROM " + subjectTableColumn;
        Cursor cursor = db.rawQuery(query,null);
        String department = "Escolha um Departamento";
        ArrayList<String> departments = new ArrayList<>();
        departments.add(department);
        while(cursor.moveToNext()){
            department = cursor.getString(0);
            departments.add(department);
        }
        cursor.close();
        db.close();
        return departments;
    }

    public Subject getSubjectById(long id) {
        db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + subjectTableColumn + " WHERE " + subjectIdColumn + " LIKE ?";
        String stringId = Long.toString(id);
        String[] arguments = {stringId};
        Cursor cursor = db.rawQuery(query, arguments);
        Subject subject =  new Subject();
        while(cursor.moveToNext()) {
            subject = createSubject(cursor);
        }

        cursor.close();
        db.close();

        return subject;
    }

    public Subject getSubjectByName(String name) {
        db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + subjectTableColumn + " WHERE " + subjectNameColumn + " LIKE ?";
        String[] arguments = {name};
        Cursor cursor = db.rawQuery(query, arguments);
        Subject subject =  new Subject();
        while(cursor.moveToNext()) {
            subject = createSubject(cursor);
        }

        cursor.close();
        db.close();

        return subject;
    }

    public List<Subject> getSubjectsHelperByUserId(long id) {
        db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + userSubjectTable + " WHERE " + userSubjectOwnerIdColumn + " LIKE ?" + " AND " + userSubjectTypeColumn + " LIKE ?";
        String stringId = Long.toString(id);
        String stringType = "1";
        String[] arguments = {stringId, stringType};
        List<Integer> subjectsId = new ArrayList<>();
        List<Subject> subjects = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, arguments);

        while(cursor.moveToNext()) {
            subjectsId.add(getSubjectsNameId(cursor));
        }

        cursor.close();
        db.close();

        for(Integer i : subjectsId)
            subjects.add(getSubjectById(i));

        return subjects;
    }

    public List<Subject> getSubjectsHelpedByUserId(long id) {
        db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + userSubjectTable + " WHERE " + userSubjectOwnerIdColumn + " LIKE ?" + " AND " + userSubjectTypeColumn + " LIKE ?";
        String stringId = Long.toString(id);
        String stringType = "2";
        String[] arguments = {stringId, stringType};
        List<Integer> subjectsId = new ArrayList<>();
        List<Subject> subjects = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, arguments);

        while(cursor.moveToNext()) {
            subjectsId.add(getSubjectsNameId(cursor));
        }

        cursor.close();
        db.close();

        for(Integer i : subjectsId)
            subjects.add(getSubjectById(i));

        return subjects;
    }

    public Integer getSubjectsNameId(Cursor cursor) {
        int indexUserSubjectId = cursor.getColumnIndex(userSubjectNameIdColumn);
        int id = cursor.getInt(indexUserSubjectId);
        return id;
    }

    public long insertSubject(Subject subject) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String subjectName = subject.getSubjectName();
        values.put(subjectNameColumn, subjectName);

        String subjectDept = subject.getDepartment();
        values.put(subjectDeptColumn, subjectDept);

        long id = db.insert(subjectTableColumn, null, values);
        return id;
    }

    public Subject createSubject(Cursor cursor){

        String colunaId = subjectIdColumn;
        int indexColunaId= cursor.getColumnIndex(colunaId);
        int id = cursor.getInt(indexColunaId);

        String colunaDept = subjectDeptColumn;
        int indexColunaDept = cursor.getColumnIndex(colunaDept);
        String Dept = cursor.getString(indexColunaDept);

        String colunaNome = subjectNameColumn;
        int indexColunaNome = cursor.getColumnIndex(colunaNome);
        String nome = cursor.getString(indexColunaNome);

        Subject subject = new Subject("SI1", nome);
        subject.setId(id);
        subject.setDepartment(Dept);

        return subject;

    }
}

