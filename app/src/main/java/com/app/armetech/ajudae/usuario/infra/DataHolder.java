package com.app.armetech.ajudae.usuario.infra;

import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by roxac on 18/12/17.
 */

public class DataHolder {

    private static final DataHolder holder = new DataHolder();
    public static DataHolder getInstance() { return holder; }

    private HashMap<String, Object> dataMap = new LinkedHashMap<>();

    public HashMap getData() { return dataMap; }
    public void setData(String key, Object data) { dataMap.put(key, data); }

}
