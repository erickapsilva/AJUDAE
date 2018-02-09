package com.app.armetech.ajudae.navigation.fragments;

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

import com.app.armetech.ajudae.R;
import com.app.armetech.ajudae.classes.business.SubjectBusiness;
import com.app.armetech.ajudae.classes.domain.DataObjectAusences;
import com.app.armetech.ajudae.classes.domain.Subject;
import com.app.armetech.ajudae.classes.gui.RVAusences;
import com.app.armetech.ajudae.user.domain.Session;
import com.app.armetech.ajudae.user.domain.User;

import java.util.ArrayList;
import java.util.List;


public class FaltasFragment extends Fragment {

    private SubjectBusiness subjectBusiness;
    private User user;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_faltas,container,false);
        subjectBusiness = new SubjectBusiness(getContext());
        user = new User();

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RVAusences(getDataSet());
        mRecyclerView.setAdapter(mAdapter);

        // Code to Add an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).addItem(obj, index);

        // Code to remove an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).deleteItem(index);
        return rootView;

    }

    @Override
    public void onResume() {
        super.onResume();
        ((RVAusences) mAdapter).setOnItemClickListener(new RVAusences.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, Session.getLoggedUser().getSubjectsHelped().size() +" Clicked on Item " + position);
            }
        });
    }

    private ArrayList<DataObjectAusences> getDataSet() {
        ArrayList results = new ArrayList<DataObjectAusences>();
        for (int index = 0; index < 4; index++) {
            DataObjectAusences obj = new DataObjectAusences("Cadeira " + index,"Pode Faltar " + index, "Faltou: " + index);
            results.add(index, obj);
        }
        return results;
    }

}
