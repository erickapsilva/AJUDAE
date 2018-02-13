package com.app.armetech.ajudae.navigation;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.app.armetech.ajudae.R;
import com.app.armetech.ajudae.user.business.UserBusiness;
import com.app.armetech.ajudae.user.gui.LoginAvaActivity;
import com.app.armetech.ajudae.user.gui.SelectHelpSubjectActivity;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UserBusiness userBusiness = new UserBusiness(this);
        if (userBusiness.checkSession()){
            if(userBusiness.checkUserStage() == 3) {
                goToFeed();
            } else if(userBusiness.checkUserStage() == 2){
                goToHelpSubjectsScreen();
            } else {
                goToLoginAva();
            }
        } else {
            setContentView(R.layout.activity_splash_screen);
            new Handler().postDelayed(new Runnable() {

			/* Implementando um handler a partir do tempo que criei
             * Esse handler segura a splash na tela pra mostrar a tela seguinte
			 */

                @Override
                public void run() {
                    Intent i = new Intent(SplashActivity.this, WelcomeActivity.class);
                    startActivity(i);
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }
    }

    public void goToLoginAva(){
        Intent startScreen = new Intent(this, LoginAvaActivity.class);
        startActivity(startScreen);
        finish();
    }

    public void goToHelpSubjectsScreen(){
        Intent helpSubject = new Intent(this, SelectHelpSubjectActivity.class);
        startActivity(helpSubject);
        finish();
    }

    public void goToFeed() {
        Intent feedScreen = new Intent(this, BottomTabActivity.class);
        startActivity(feedScreen);
        finish();
    }
}
