package com.app.armetech.ajudae.navigation.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.app.armetech.ajudae.R;
import com.app.armetech.ajudae.classes.domain.DataObjectAusences;
import com.app.armetech.ajudae.classes.gui.RVProfileHelpSubjects;
import com.app.armetech.ajudae.classes.gui.RVProfileSubjects;
import com.app.armetech.ajudae.user.domain.Session;
import com.app.armetech.ajudae.user.gui.EditProfileActivity;

import java.util.ArrayList;


public class ProfileFragment extends Fragment {

    TextView userNameProfile;
    TextView userGraduationProfile;
    TextView numberCurrentSubjects;
    TextView numberubjectsHelping;

    ImageButton goToEditProfile;

    private RecyclerView mRecyclerView;
    private RecyclerView recyclerViewHelpSubject;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.Adapter helpSubjectAdapter;

    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.LayoutManager hLayoutManager;

    private static String LOG_TAG = "CardViewActivity";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_profile,container,false);

        userNameProfile = (TextView) rootView.findViewById(R.id.txtUserNameProfile);
        userGraduationProfile = (TextView) rootView.findViewById(R.id.txtGraduationProfile);
        numberCurrentSubjects = (TextView) rootView.findViewById(R.id.txtCurrentSubjects);
        numberubjectsHelping = (TextView) rootView.findViewById(R.id.txtSubjectsHelping);

        userNameProfile.setText(String.format(Session.getLoggedPerson().getName()));
        userGraduationProfile.setText(String.format(Session.getLoggedUser().getCourse()));
        numberCurrentSubjects.setText(String.format("CADEIRAS CURSANDO: "+String.valueOf(Session.getLoggedUser().getSubjectsHelped().size())));
        numberubjectsHelping.setText(String.format("CADEIRAS AJUDANDO: "+String.valueOf(Session.getLoggedUser().getSubjectsHelper().size())));

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        recyclerViewHelpSubject = (RecyclerView) rootView.findViewById(R.id.recyclerViewHelpSubject);

        mRecyclerView.setHasFixedSize(true);
        recyclerViewHelpSubject.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false);
        hLayoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false);

        mRecyclerView.setLayoutManager(mLayoutManager);
        recyclerViewHelpSubject.setLayoutManager(hLayoutManager);

        helpSubjectAdapter = new RVProfileHelpSubjects(getHelpSubjects());
        mAdapter = new RVProfileSubjects(getDataSet());

        recyclerViewHelpSubject.setAdapter(helpSubjectAdapter);
        mRecyclerView.setAdapter(mAdapter);

        goToEditProfile =  (ImageButton) rootView.findViewById(R.id.goToEditProfile);
        goToEditProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                getActivity().startActivity(intent);
                startActivity(intent);
            }
        });

        // Code to Add an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).addItem(obj, index);

        // Code to remove an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).deleteItem(index);

        return rootView;
    }

    public void onResume() {
        super.onResume();

        ((RVProfileHelpSubjects) helpSubjectAdapter).setOnItemClickListener(new RVProfileHelpSubjects.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, Session.getLoggedUser().getCourse() +" Clicked on Item " + position);
            }
        });

        ((RVProfileSubjects) mAdapter).setOnItemClickListener(new RVProfileSubjects.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, Session.getLoggedUser().getCourse() +" Clicked on Item " + position);
            }
        });
    }

    private ArrayList<DataObjectAusences> getDataSet() {
        ArrayList results = new ArrayList<DataObjectAusences>();
        for (int index = 0; index < Session.getLoggedUser().getSubjectsHelped().size(); index++) {
            DataObjectAusences obj = new DataObjectAusences(
                    "" + Session.getLoggedUser().getSubjectsHelped().get(index).getSubjectName(),
                    "Pode Faltar " + index,
                    "Faltou: " + index);
            results.add(index, obj);
        }
        return results;
    }

    private ArrayList<DataObjectAusences> getHelpSubjects() {
        ArrayList results = new ArrayList<DataObjectAusences>();
        for (int index = 0; index < Session.getLoggedUser().getSubjectsHelper().size(); index++) {
            DataObjectAusences obj = new DataObjectAusences(
                    "" + Session.getLoggedUser().getSubjectsHelper().get(index).getSubjectName(),
                    "Pode Faltar " + index,
                    "Faltou: " + index);
            results.add(index, obj);
        }
        return results;
    }





}
