package ru.healthy;

import android.content.Context;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by john on 26.11.17.
 */

public class DataAdapter extends ArrayAdapter implements IDataAdapter {

    public DataAdapter(@NonNull Context context, int resource, String[] spins) {
        super(context, resource, spins);
    }

    public DataAdapter(Context context, int resource, String spinner_arr) {
        super(context, resource, (new Storage(context).getStringArray(spinner_arr)));
    }

    @Override
    public void update() {
        //((BaseAdapter) ((ListView) findViewById(R.id.list)).getAdapter()).notifyDataSetChanged();
        notifyDataSetChanged();
    }


/*
    public DataAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }
    */


}
