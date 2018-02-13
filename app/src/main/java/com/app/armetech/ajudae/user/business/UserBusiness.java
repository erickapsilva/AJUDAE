package com.app.armetech.ajudae.user.business;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.app.armetech.ajudae.utils.Cryptography;
import com.app.armetech.ajudae.user.domain.Session;
import com.app.armetech.ajudae.user.dao.PersonDao;
import com.app.armetech.ajudae.user.dao.SessionDao;
import com.app.armetech.ajudae.user.dao.UserDao;
import com.app.armetech.ajudae.user.domain.Person;
import com.app.armetech.ajudae.user.domain.User;

public class UserBusiness {
    private PersonDao personDao;
    private UserDao userDao;
    private SessionDao sessionDao;


    public UserBusiness(Context context){
        personDao = new PersonDao(context);
        userDao = new UserDao(context);
        sessionDao = new SessionDao(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean register(User user, Person person){
        User verify = userDao.getUserByEmail(user.getEmail());
        if (verify != null) {
            return false;
        } else {
            user.setPassword(Cryptography.encryptPassword(user.getPassword()));
            long userId = userDao.insertUser(user);

            person.setUserId(userId);
            long personId = personDao.insertPerson(person);
            person.setId(personId);

            return true;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean validateLogin(String email, String password){
        String encryptedPassword = Cryptography.encryptPassword(password);
        User user = userDao.getUserLogin(email,encryptedPassword);
        if (user != null){
            sessionDao.startSession(user);
            checkSession();
            return true;
        }else {
            return false;
        }
    }

    public boolean checkSession(){
        User loggedUser = sessionDao.recoversSession();
        if (loggedUser!=null){
            Session.setLoggedUser(loggedUser);
            Session.setLoggedPerson(personDao.getPersonByUserId(loggedUser.getId()));
            return true;
        }
        return false;
    }

    public int checkUserStage() {
        User loggedUser = sessionDao.recoversSession();
        if(loggedUser.getStage() == 1) {
            return 1;
        } else if(loggedUser.getStage() == 2) {
            return 2;
        } else {
            return 3;
        }
    }

    public void logout(){
        sessionDao.finishSession();
        Session.finishSession();
    }
}
