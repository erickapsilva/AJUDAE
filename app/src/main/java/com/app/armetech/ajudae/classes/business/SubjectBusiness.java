package com.app.armetech.ajudae.classes.business;

import android.content.Context;

import com.app.armetech.ajudae.classes.dao.SubjectDao;
import com.app.armetech.ajudae.classes.domain.Subject;

import java.util.ArrayList;

/**
 * Created by erickapsilva on 03/02/2018.
 */

public class SubjectBusiness {

    private SubjectDao subjectDao;
    private ArrayList<Subject> listSubjects;

    public SubjectBusiness(Context context){
        subjectDao = new SubjectDao(context);
    }


    public ArrayList<String> getAutocompleteData(){
        listSubjects = subjectDao.getSubjects();
        ArrayList<String> listSubjectsName = new ArrayList<String>();
        for(Subject subject: listSubjects){
            listSubjectsName.add(subject.getSubjectName());
        }
        return  listSubjectsName;
    }
}
