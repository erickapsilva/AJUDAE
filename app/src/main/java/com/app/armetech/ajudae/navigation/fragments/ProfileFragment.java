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
import android.widget.TextView;

import com.app.armetech.ajudae.R;
import com.app.armetech.ajudae.classes.domain.DataObjectAusences;
import com.app.armetech.ajudae.classes.gui.RVAusences;
import com.app.armetech.ajudae.user.domain.Session;

import java.util.ArrayList;


public class ProfileFragment extends Fragment {

    TextView userNameProfile;
    TextView userGraduationProfile;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_profile,container,false);

        userNameProfile = (TextView) rootView.findViewById(R.id.txtUserNameProfile);
        userGraduationProfile = (TextView) rootView.findViewById(R.id.txtGraduationProfile);

        userNameProfile.setText(String.format(Session.getLoggedPerson().getName()));
        userGraduationProfile.setText(String.format(Session.getLoggedUser().getCourse()));

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RVAusences(getDataSet());
        mRecyclerView.setAdapter(mAdapter);

        // Code to Add an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).addItem(obj, index);

        // Code to remove an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).deleteItem(index);

        return rootView;
    }

    public void onResume() {
        super.onResume();
        ((RVAusences) mAdapter).setOnItemClickListener(new RVAusences.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, Session.getLoggedUser().getCourse() +" Clicked on Item " + position);
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
