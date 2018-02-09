package com.app.armetech.ajudae.user.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.armetech.ajudae.R;
import com.app.armetech.ajudae.classes.dao.SubjectDao;
import com.app.armetech.ajudae.classes.domain.Subject;
import com.app.armetech.ajudae.infra.DataHolder;
import com.app.armetech.ajudae.utils.StudentExternalData;
import com.app.armetech.ajudae.infra.RequestHttp;
import com.app.armetech.ajudae.user.domain.Session;
import com.app.armetech.ajudae.user.domain.User;

import java.util.ArrayList;
import java.util.List;

public class ScheduleAvaActivity extends Activity {


    private DataHolder dataHolder;
    private RequestHttp requestHttp;
    private StudentExternalData studentExternalData;
    private List<String> subjects;
    private List<String> courseClass;
    private List<Subject> courseSubjects;
    private RecyclerView recyclerView;
    private TextView txtViewName;
    private TextView txtViewCourse;
    private Button btnRemover;
    private String dept;
    private String fullname;
    private SubjectDao subjectDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_ava_screen);
        txtViewName = (TextView)findViewById(R.id.txtViewName);
        txtViewCourse = (TextView)findViewById(R.id.txtViewCourse);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        btnRemover = (Button)findViewById(R.id.btnContinuar);
        dataHolder = DataHolder.getInstance();
        requestHttp = RequestHttp.getInstance();
        GridLayoutManager glm = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(glm);
        subjectDao = new SubjectDao(getApplicationContext());
        User logUser = Session.getLoggedUser();
        if(logUser.getSubjectsHelper().size() > 0)
            Log.i("TESTE: ", "ALGO: " + logUser.getSubjectsHelper().get(0).getSubjectName());
        initializeData();
        initializeUpdateAdapter();
    }

    public void initializeData() {
        subjects = (ArrayList)dataHolder.getData().get("subjects");
        courseClass = (ArrayList)dataHolder.getData().get("course_class");
        dept = dataHolder.getData().get("dpt").toString();
        fullname = dataHolder.getData().get("fullname").toString();
        User loggedUser = Session.getLoggedUser();
        courseSubjects = new ArrayList<>();
        for(int i = 0; i < subjects.size(); i++) {
            Subject newSubject = new Subject();
            newSubject.setSubjectName(subjects.get(i));
            newSubject.setCourseClass(courseClass.get(i));
            newSubject.setDepartment(courseClass.get(i));
            long newId = subjectDao.insertSubject(newSubject);
            newSubject.setId(newId);
            courseSubjects.add(newSubject);
            loggedUser.addSubjectHelped(newSubject);
        }
        txtViewName.append(fullname);
        txtViewCourse.append(dept);
        Log.i("CURSO: ", dept);
        loggedUser.setCourse(dept);
    }

    public void goToHelpSubjectsScreen(View view){
        Intent helpSubject = new Intent(this, SelectHelpSubjectActivity.class);
        startActivity(helpSubject);
        finish();
    }


    public void removeSubjects(View view) {
        courseSubjects.remove(0);
        initializeUpdateAdapter();
    }

    private void initializeUpdateAdapter() {
        RVSubjects adapter = new RVSubjects(courseSubjects, false);
        recyclerView.setAdapter(adapter);
    }
}
