package com.app.armetech.ajudae.navigation;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.app.armetech.ajudae.R;
import com.app.armetech.ajudae.navigation.fragments.FaltasFragment;
import com.app.armetech.ajudae.navigation.fragments.FeedFragment;
import com.app.armetech.ajudae.navigation.fragments.NotificationsFragment;
import com.app.armetech.ajudae.navigation.fragments.ProfileFragment;
import com.app.armetech.ajudae.navigation.fragments.SearchFragment;
import com.app.armetech.ajudae.questions.gui.AddQuestionActivity;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class BottomTabActivity extends AppCompatActivity implements AHBottomNavigation.OnTabSelectedListener{

    AHBottomNavigation bottomNavigation;
    TextView mTitle;
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bottom_tab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton2 =  (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);


        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        bottomNavigation= (AHBottomNavigation) findViewById(R.id.myBottomNavigation_ID);
        bottomNavigation.setOnTabSelectedListener(this);
        this.createNavItems();

        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked
                startActivity(new Intent(BottomTabActivity.this,AddQuestionActivity.class));
            }
        });


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
            mTitle.setText("Feed");


        }else  if (position==1)
        {
            FaltasFragment faltasFragment = new FaltasFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,faltasFragment).commit();
            mTitle.setText("Faltas");

        }else  if (position==2)
        {
            SearchFragment searchFragment = new SearchFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,searchFragment).commit();
            mTitle.setText("Buscar");

        }else  if (position==3)
        {
            NotificationsFragment notificationsFragment = new NotificationsFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,notificationsFragment).commit();
            mTitle.setText("Alertas");

        }else  if (position==4)
        {
            ProfileFragment profileFragment = new ProfileFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,profileFragment).commit();
            mTitle.setText("Perfil");

        }
        return true;
    }


}