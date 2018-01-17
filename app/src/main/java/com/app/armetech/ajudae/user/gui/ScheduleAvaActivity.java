package com.app.armetech.ajudae.user.gui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.app.armetech.ajudae.R;
import com.app.armetech.ajudae.aulas.domain.Subject;
import com.app.armetech.ajudae.infra.DataHolder;
import com.app.armetech.ajudae.infra.StudentExternalData;
import com.app.armetech.ajudae.infra.RequestHttp;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_ava_screen);
        txtViewName = (TextView)findViewById(R.id.txtViewName);
        txtViewCourse = (TextView)findViewById(R.id.txtViewCourse);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        dataHolder = DataHolder.getInstance();
        requestHttp = RequestHttp.getInstance();
        GridLayoutManager glm = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(glm);
        initializeData();
        initializeAdapter();
    }

    public void initializeData() {
        subjects = (ArrayList)dataHolder.getData().get("subjects");
        courseClass = (ArrayList)dataHolder.getData().get("course_class");
        courseSubjects = new ArrayList<>();
        for(int i = 0; i < subjects.size(); i++)
            courseSubjects.add(new Subject(courseClass.get(i), subjects.get(i)));
        txtViewName.append(dataHolder.getData().get("fullname").toString());
        txtViewCourse.append(dataHolder.getData().get("dpt").toString());
    }

    private void initializeAdapter() {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(courseSubjects);
        recyclerView.setAdapter(adapter);
    }
}
