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
        return String.valueOf(value);
    }
}
