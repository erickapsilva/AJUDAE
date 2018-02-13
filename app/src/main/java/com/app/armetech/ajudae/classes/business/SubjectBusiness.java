package com.app.armetech.ajudae.classes.business;

import android.content.Context;
import android.util.Log;

import com.app.armetech.ajudae.classes.dao.SubjectDao;
import com.app.armetech.ajudae.classes.domain.Subject;
import com.app.armetech.ajudae.user.dao.UserDao;
import com.app.armetech.ajudae.user.domain.Session;
import com.app.armetech.ajudae.user.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by erickapsilva on 03/02/2018.
 */

public class SubjectBusiness {

    private SubjectDao subjectDao;
    private ArrayList<Subject> listSubjects;
    private static String LOG_TAG = "SUBJECT_BUSSINESS";
    public SubjectBusiness(Context context){
        subjectDao = new SubjectDao(context);
        Log.i(LOG_TAG, "CRIANDO ");
    }

    public ArrayList<String> getAutocompleteData(){
        listSubjects = subjectDao.getSubjects();
        ArrayList<String> listSubjectsName = new ArrayList<String>();
        for(Subject subject: listSubjects){
            listSubjectsName.add(subject.getSubjectName());
        }
        return  listSubjectsName;
    }

    public ArrayList<Subject> getSubjects() {
        ArrayList<Subject> subjectsDb = subjectDao.getSubjects();
        return subjectsDb;
    }
}
