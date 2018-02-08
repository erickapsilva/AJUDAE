package com.app.armetech.ajudae.navigation.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.armetech.ajudae.R;
import com.app.armetech.ajudae.questions.dao.QuestionDao;
import com.app.armetech.ajudae.questions.domain.Question;
import com.app.armetech.ajudae.user.gui.RVFeed;

import java.util.ArrayList;
import java.util.List;


public class FeedFragment extends Fragment {

    private List<Question> questions;
    private QuestionDao questionDao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_feed,container,false);


        questionDao = new QuestionDao(getContext());

        RecyclerView recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        initializeData();
        recyclerView.setAdapter(new RVFeed(questions));

        return recyclerView;

    }

    public void initializeData() {
        List<Question> questionsDb = questionDao.getQuestions();
        questions = new ArrayList<>();
        questions = questionsDb;
   }

}
