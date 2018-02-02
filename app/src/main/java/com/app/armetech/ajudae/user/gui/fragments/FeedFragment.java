package com.app.armetech.ajudae.user.gui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.armetech.ajudae.R;
import com.app.armetech.ajudae.perguntas.dao.QuestionDao;
import com.app.armetech.ajudae.perguntas.domain.Question;
import com.app.armetech.ajudae.user.gui.RVFeed;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;


public class FeedFragment extends Fragment {

    private List<Question> questions;
    private QuestionDao questionDao;

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton2, floatingActionButton3;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_feed,container,false);

        materialDesignFAM = (FloatingActionMenu) rootView.findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton2 =  (FloatingActionButton) rootView.findViewById(R.id.material_design_floating_action_menu_item2);
        floatingActionButton3 = (FloatingActionButton) rootView.findViewById(R.id.material_design_floating_action_menu_item3);

        questionDao = new QuestionDao(getContext());

        RecyclerView recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        initializeData();
        recyclerView.setAdapter(new RVFeed(questions));


        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked
            }
        });
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked

            }
        });

        return recyclerView;

    }

    public void initializeData() {
        List<Question> questionsDb = questionDao.getQuestions();
        questions = new ArrayList<>();
        questions = questionsDb;
    }

}
