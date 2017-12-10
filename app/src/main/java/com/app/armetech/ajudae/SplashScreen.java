package com.app.armetech.ajudae;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 1000; // este é o tempo da duração da Splash Screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {

			/* Implementando um handler a partir do tempo que criei
             * Esse handler segura a splash na tela pra mostrar a tela seguinte
			 */

            @Override
            public void run() {
                // Quando o tempo que setei acaber esse método vai rodar
                // e iniciar minha splash activity
                Intent i = new Intent(SplashScreen.this, WelcomeScreen.class);
                startActivity(i);

                // fechando a splash activity pra abrir a starter
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
