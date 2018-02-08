package com.app.armetech.ajudae.utils;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public final class Cryptography {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String encryptPassword(String password){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] encryptPassword = md.digest();
            
            StringBuilder sb = new StringBuilder();
            for (byte caractere : encryptPassword) {
                sb.append(String.format("%02X", caractere));
            }

            return sb.toString();

        }catch(NoSuchAlgorithmException e){
            return null;
        }
    }


}

