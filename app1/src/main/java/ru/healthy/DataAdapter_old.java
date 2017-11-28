package ru.healthy;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;


public class DataAdapter_old extends ArrayAdapter implements IDataAdapter{
    Activity context;
    int resource;
    String action;

    public DataAdapter_old(@NonNull Context context, int resource, String[] spins) {
        super(context, resource, spins);
    }

    public DataAdapter_old(Activity con, int res, String act) {
        super(con, res, (new Storage(con).getStringArray(act)));
        context = con;
        resource = res;
        action = act;
    }

    @Override
    public void update() {
        String[] arr = new Storage(context).getStringArray(action);
        notifyDataSetChanged();
    }
}
