package com.app.armetech.ajudae.user.gui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.app.armetech.ajudae.user.gui.fragments.FaltasFragment;
import com.app.armetech.ajudae.user.gui.fragments.FeedFragment;
import com.app.armetech.ajudae.user.gui.fragments.NotificationsFragment;
import com.app.armetech.ajudae.user.gui.fragments.ProfileFragment;
import com.app.armetech.ajudae.R;
import com.app.armetech.ajudae.user.gui.fragments.SearchFragment;

public class InitialScreenActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            switch (item.getItemId()) {

                case R.id.navigation_feed:
                    setTitle("@string/feed");//seta o título da ActionBar
                    FeedFragment fragmentFeed = new FeedFragment();
                    FragmentTransaction fragmentTransactionFeed = getSupportFragmentManager().beginTransaction();
                    fragmentTransactionFeed.replace(R.id.frame_layout, fragmentFeed, "@string/fragment_text_feed");
                    fragmentTransactionFeed.commit();
                    return true;

                case R.id.navigation_faltas:
                    setTitle("@string/faltas");//seta o título da ActionBar
                    FaltasFragment fragmentFaltas = new FaltasFragment();
                    FragmentTransaction fragmentTransactionFaltas = getSupportFragmentManager().beginTransaction();
                    fragmentTransactionFaltas.replace(R.id.frame_layout, fragmentFaltas, "@string/fragment_text_faltas");
                    fragmentTransactionFaltas.commit();
                    return true;

                case R.id.navigation_search:
                    setTitle("@string/buscar");//seta o título da ActionBar
                    SearchFragment fragmentSearch = new SearchFragment();
                    FragmentTransaction fragmentTransactionSearch = getSupportFragmentManager().beginTransaction();
                    fragmentTransactionSearch.replace(R.id.frame_layout, fragmentSearch, "@string/fragment_text_search");
                    fragmentTransactionSearch.commit();
                    return true;

                case R.id.navigation_notifications:
                    setTitle("@string/notificacoes");//seta o título da ActionBar
                    NotificationsFragment fragmentNotifications = new NotificationsFragment();
                    FragmentTransaction fragmentTransactionNotifications = getSupportFragmentManager().beginTransaction();
                    fragmentTransactionNotifications.replace(R.id.frame_layout, fragmentNotifications, "@string/fragment_text_notifications");
                    fragmentTransactionNotifications.commit();
                    return true;

                case R.id.navigation_profile:
                    setTitle("@string/feed");//seta o título da ActionBar
                    ProfileFragment fragmentProfile = new ProfileFragment();
                    FragmentTransaction fragmentTransactionProfile = getSupportFragmentManager().beginTransaction();
                    fragmentTransactionProfile.replace(R.id.frame_layout, fragmentProfile, "@string/fragment_text_profile");
                    fragmentTransactionProfile.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_screen);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Quando o app inicia, fragmento Feed sera mostrado
        setTitle("@string/feed");//seta o título da ActionBar
        FeedFragment fragmentFeed = new FeedFragment();
        FragmentTransaction fragmentTransactionFeed = getSupportFragmentManager().beginTransaction();
        fragmentTransactionFeed.replace(R.id.frame_layout, fragmentFeed, "@string/fragment_text_feed");
        fragmentTransactionFeed.commit();
    }

}
