package ru.healthy;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by john on 25.11.17.
 */

public class Storage {
    Context context;

    public Storage(Context c) {
        context = c;
    }

    public static void setCurrentUser(Context c, String user) {
        SharedPreferences defsettings = PreferenceManager.getDefaultSharedPreferences(c);
        SharedPreferences.Editor eddef = defsettings.edit();
        eddef.putString("currentUser", user);
        eddef.apply();
    }

    public static String getCurrentUser(Context c) {
        SharedPreferences defsettings = PreferenceManager.getDefaultSharedPreferences(c);
        return defsettings.getString("currentUser","0");
    }

    public static void store(Context c, String key, String value) {
        String currentUser = getCurrentUser(c);
        SharedPreferences settings = c.getSharedPreferences(currentUser, 0);
        SharedPreferences.Editor ed = settings.edit();
        ed.putString(key, value);
        ed.apply();
    }

    public static String restore(Context c, String key) {
        String currentUser = getCurrentUser(c);
        SharedPreferences settings = c.getSharedPreferences(currentUser, 0);
        String value = settings.getString(key, "0");
        return value;
    }

    public String[] getStringArrayy(String action) {
        if (action.equals("area")) return  context.getResources().getStringArray(R.array.area);
        else if (action.equals("lpu")) return  context.getResources().getStringArray(R.array.lpu);
        else if (action.equals("spec")) return  context.getResources().getStringArray(R.array.spec);
        else if (action.equals("doc")) return  context.getResources().getStringArray(R.array.doc);
        else if (action.equals("dates")) return  context.getResources().getStringArray(R.array.dates);
        else if (action.equals("talons")) return  context.getResources().getStringArray(R.array.talons);
        else if (action.equals("hist")) return  context.getResources().getStringArray(R.array.hist);
        else return context.getResources().getStringArray(R.array.def_arr);
    }
}
