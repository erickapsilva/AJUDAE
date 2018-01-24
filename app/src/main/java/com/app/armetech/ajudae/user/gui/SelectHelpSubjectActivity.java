package com.app.armetech.ajudae.user.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.armetech.ajudae.R;
import com.app.armetech.ajudae.aulas.domain.Subject;
import com.app.armetech.ajudae.perguntas.gui.NewsFeed;

import java.util.ArrayList;
import java.util.List;

public class SelectHelpSubjectActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private List<Subject> takenSubjects;
    private RecyclerView recyclerView;


    String[] countryNames={"MATEMÁTICA DISCRETA","ÁLGEBRA","FÍSICA","ECONOMIA","ENGENHARIA DE SOFTWARE"};
    int flags[] = {R.drawable.ic_academic_cap, R.drawable.ic_academic_cap,
            R.drawable.ic_academic_cap, R.drawable.ic_academic_cap, R.drawable.ic_academic_cap};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_help_subject);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewHelp);

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.simpleSpinner);
        spin.setOnItemSelectedListener(this);

        CustomAdapter customAdapter=new CustomAdapter(getApplicationContext(),flags,countryNames);
        spin.setAdapter(customAdapter);

        GridLayoutManager glm = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(glm);
        initializeData();
        initializeUpdateAdapter();
    }

    public void initializeData() {
        takenSubjects = new ArrayList<>();
        takenSubjects.add(new Subject("SI1", "Modelagem e Programação Orientada a Objectos"));
        takenSubjects.add(new Subject("SI1", "Matemática Discreta"));
        takenSubjects.add(new Subject("SI2", "Teoria da Computação"));
        takenSubjects.add(new Subject("SI1", "Fundamentos de Engenharia de Software"));
        takenSubjects.add(new Subject("SI2", "Introdução da Programação"));
    }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(), countryNames[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    //Função para testar o NewsFeed ButtomNavigation
    public void goToNewsFeedScreen(View view){
        Intent intent = new Intent(this, NewsFeed.class);
        startActivity(intent);
        finish();
    }

    private void initializeUpdateAdapter() {
        RVSubjects adapter = new RVSubjects(takenSubjects);
        recyclerView.setAdapter(adapter);
    }
}
