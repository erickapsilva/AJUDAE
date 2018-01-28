package com.app.armetech.ajudae.user.gui;

import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.app.armetech.ajudae.R;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import com.app.armetech.ajudae.user.gui.fragments.FeedFragment;
import com.app.armetech.ajudae.user.gui.fragments.FaltasFragment;
import com.app.armetech.ajudae.user.gui.fragments.SearchFragment;
import com.app.armetech.ajudae.user.gui.fragments.NotificationsFragment;
import com.app.armetech.ajudae.user.gui.fragments.ProfileFragment;

public class BottomTabActivity extends AppCompatActivity implements AHBottomNavigation.OnTabSelectedListener{

    AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNavigation= (AHBottomNavigation) findViewById(R.id.myBottomNavigation_ID);
        bottomNavigation.setOnTabSelectedListener(this);
        this.createNavItems();

    }

    private void createNavItems()
    {
        //CREATE ITEMS
        AHBottomNavigationItem feedItem = new AHBottomNavigationItem("Feed", R.drawable.ic_tab_home);
        AHBottomNavigationItem faltaItem = new AHBottomNavigationItem("Falta",R.drawable.ic_academic_cap);
        AHBottomNavigationItem searchItem = new AHBottomNavigationItem("Buscar",R.drawable.ic_android_search);
        AHBottomNavigationItem notificationItem = new AHBottomNavigationItem("Notificações",R.drawable.ic_tab_notifications);
        AHBottomNavigationItem profileItem = new AHBottomNavigationItem("Perfil",R.drawable.ic_tab_profile);

        //ADD THEM to bar
        bottomNavigation.addItem(feedItem);
        bottomNavigation.addItem(faltaItem);
        bottomNavigation.addItem(searchItem);
        bottomNavigation.addItem(notificationItem);
        bottomNavigation.addItem(profileItem);


        //set properties
        //bottomNavigation.setColored(true);
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#f1f1f1"));
        bottomNavigation.setAccentColor(Color.parseColor("#E35354"));
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

        //set current item
        bottomNavigation.setCurrentItem(0);


    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        //show fragment
        if (position==0)
        {
            FeedFragment feedFragment=new FeedFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,feedFragment).commit();


        }else  if (position==1)
        {
            FaltasFragment faltasFragment = new FaltasFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,faltasFragment).commit();

        }else  if (position==2)
        {
            SearchFragment searchFragment = new SearchFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,searchFragment).commit();

        }else  if (position==3)
        {
            NotificationsFragment notificationsFragment = new NotificationsFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,notificationsFragment).commit();

        }else  if (position==4)
        {
            ProfileFragment profileFragment = new ProfileFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,profileFragment).commit();

        }
        return true;
    }


}