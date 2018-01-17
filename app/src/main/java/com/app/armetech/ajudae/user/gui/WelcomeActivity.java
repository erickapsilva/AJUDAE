package com.app.armetech.ajudae.user.gui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.app.armetech.ajudae.R;

public class WelcomeActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private SlideAdapter slideAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        slideAdapter = new SlideAdapter(this);
        viewPager.setAdapter(slideAdapter);
    }
}
