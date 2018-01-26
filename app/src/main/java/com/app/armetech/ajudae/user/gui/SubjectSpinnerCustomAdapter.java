package com.app.armetech.ajudae.user.gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.armetech.ajudae.R;

/**
 * Created by user1 on 24/01/2018.
 */

public class SubjectSpinnerCustomAdapter extends BaseAdapter {
    Context context;
    String[] info;
    LayoutInflater inflter;

    public SubjectSpinnerCustomAdapter(Context applicationContext, String[] info) {
        this.context = applicationContext;
        this.info = info;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return info.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_spinner_items, null);
        ImageView icon = (ImageView) view.findViewById(R.id.imageView);
        TextView names = (TextView) view.findViewById(R.id.textView);
        icon.setImageResource(R.drawable.ic_academic_cap);
        names.setText(info[i]);
        return view;
    }
}
