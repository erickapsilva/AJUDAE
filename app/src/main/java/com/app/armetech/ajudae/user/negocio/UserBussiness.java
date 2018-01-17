package com.app.armetech.ajudae.user.negocio;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.app.armetech.ajudae.infra.Cryptography;
import com.app.armetech.ajudae.user.dao.PersonDao;
import com.app.armetech.ajudae.user.dao.UserDao;
import com.app.armetech.ajudae.user.domain.Person;
import com.app.armetech.ajudae.user.domain.User;

/**
 * Created by user on 10/12/2017.
 */

public class UserBussiness {
    private PersonDao personDao;
    private UserDao userDao;


    public UserBussiness(Context context){
        personDao = new PersonDao(context);
        userDao = new UserDao(context);
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
            return true;
        }else {
            return false;
        }
    }
}
