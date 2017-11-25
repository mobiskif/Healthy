package ru.healthy;

import android.content.Context;

/**
 * Created by john on 25.11.17.
 */

public class API {
    Context context;
    public API(Context c) {
        context = c;
    }

    public String[] getStringArray(String name) {
        if (name.equals("area")) return  context.getResources().getStringArray(R.array.district);
        else if (name.equals("lpu")) return  context.getResources().getStringArray(R.array.lpu);
        else if (name.equals("spec")) return  context.getResources().getStringArray(R.array.spec);
        else if (name.equals("doc")) return  context.getResources().getStringArray(R.array.doctors);
        else return context.getResources().getStringArray(R.array.history);
    }
}
