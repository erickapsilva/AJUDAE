package com.app.armetech.ajudae.navigation.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.armetech.ajudae.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MakeQuestionFragment extends Fragment {


    public MakeQuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_make_question, container, false);
    }

}
