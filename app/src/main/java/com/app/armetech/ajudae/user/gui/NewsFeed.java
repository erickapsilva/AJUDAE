package com.app.armetech.ajudae.user.gui;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.app.armetech.ajudae.R;

public class NewsFeed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.tab_feed:
                        //ação a ser tomada ao clicar na aba feed
                        //#teste
                        Toast.makeText(NewsFeed.this, "Clicou na aba Feed", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.tab_faltas:
                        //ação a ser tomada ao clicar na aba faltas
                        //#teste
                        Toast.makeText(NewsFeed.this, "Clicou na aba Faltas", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.tab_search:
                        //ação a ser tomada ao clicar na aba buscar
                        //#teste
                        Toast.makeText(NewsFeed.this, "Clicou na aba Buscar", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.tab_notifications:
                        //ação a ser tomada ao clicar na aba notificacoes
                        //#teste
                        Toast.makeText(NewsFeed.this, "Clicou na aba Notificações", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.tab_profile:
                        //ação a ser tomada ao clicar na aba perfil
                        //#teste
                        Toast.makeText(NewsFeed.this, "Clicou na aba Perfil", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }
}
