package com.app.armetech.ajudae.classes.gui;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.armetech.ajudae.R;
import com.app.armetech.ajudae.classes.domain.DataObjectAusences;

import java.util.ArrayList;

/**
 * Created by erickapsilva on 08/02/2018.
 */

public class RVProfileSubjects extends RecyclerView
        .Adapter<RVProfileSubjects
        .DataObjectHolder> {
    private static String LOG_TAG = "RVProfileSubjects";
    private ArrayList<DataObjectAusences> mDataset;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView cadeira;

        public DataObjectHolder(View itemView) {
            super(itemView);
            cadeira = (TextView) itemView.findViewById(R.id.txtProfileSubjects);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public RVProfileSubjects(ArrayList<DataObjectAusences> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_subjects_profile, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.cadeira.setText(mDataset.get(position).getCadeira());

    }

    public void addItem(DataObjectAusences dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}