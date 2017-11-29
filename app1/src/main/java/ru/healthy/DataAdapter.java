package ru.healthy;

import android.app.Activity;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class DataAdapter extends BaseAdapter implements IDataAdapter, android.app.LoaderManager.LoaderCallbacks<Cursor> {
    ActivityBase context;
    int resource;
    String action;
    String[] arr;
    AdapterView adapterView;
    boolean loaded;

    public DataAdapter(ActivityBase c, int r, String a) {
        //super(c, res, (new Storage(c).getStringArray(a)));
        context = c;
        resource = r;
        action = a;
        //arr = new Storage(context).getStringArray("def_arr");
        arr = context.getResources().getStringArray(R.array.def_arr);
        //Log.d("jop","initiate ====== def_arr");

        context.getLoaderManager().initLoader(resource, null, this);
    }

    @Override
    public void update() {
        //Log.d("jop","update start ====== "+action);
        //context.getLoaderManager().getLoader(resource).forceLoad();
        context.getLoaderManager().initLoader(resource, null, this);

    }

    @Override
    public int getCount() {
        return arr.length;
    }

    @Override
    public Object getItem(int position) {
        if (position>=arr.length) position=0;
        return arr[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //MatrixCursor item = (MatrixCursor) getItem(position);
        String item = (String) getItem(position);
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView v = (TextView) ((convertView == null) ? inflater.inflate(android.R.layout.simple_list_item_1, parent, false) : convertView);
        v.setText(item);
        return v;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        loaded=false;
        return new MyCursorLoader(context, action);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        //scAdapter.swapCursor(cursor);
        arr = toArr(data);
        loaded=true;
        context.init();
        //arr = new Storage(context).getStringArray(action);
        //adapterView.setAdapter(this);
        //spinner_id = Integer.valueOf(Storage.restore(this, spinner_arr+"_pos"));
        //if (((Spinner)findViewById(R.id.spinner)).getAdapter().getCount() >= spinner_id) ((Spinner) findViewById(R.id.spinner)).setSelection(spinner_id);

        Log.d("jop","load finished ====== "+action);
        notifyDataSetChanged();
    }

    private String[] toArr(Cursor data) {
        data.moveToFirst();
        ArrayList<String> names = new ArrayList<String>();
        while(!data.isAfterLast()) {
            //names.add(data.getString(data.getColumnIndex("name")));
            names.add(data.getString(1));
            data.moveToNext();
        }
        data.close();
        return names.toArray(new String[names.size()]);


    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {  }

    static class MyCursorLoader extends CursorLoader {
        Context context;
        String action;

        public MyCursorLoader(Context con, String act) {
            super(con);
            context=con;
            action=act;
        }

        @Override
        public Cursor loadInBackground() {
            HubService hs = new HubService(context);
            Cursor cur=null;

            switch (action) {
                case "GetLPUList": cur=hs.GetLPUList("GetLPUList"); break;
                case "GetDistrictList": cur=hs.GetDistrictList("GetDistrictList"); break;
                case "CheckPatient": cur=hs.CheckPatient("CheckPatient"); break;
                case "GetPatientHistory": cur=hs.GetPatientHistory("GetPatientHistory"); break;
                case "GetSpesialityList": cur=hs.GetSpesialityList("GetSpesialityList"); break;
                case "GetDoctorList": cur=hs.GetDoctorList("GetDoctorList"); break;
                case "GetAvaibleAppointments": cur=hs.GetAvaibleAppointments("GetAvaibleAppointments"); break;
                case "GetWorkingTime": cur=hs.GetWorkingTime("GetWorkingTime"); break;
                case "SetAppointment": cur=hs.SetAppointment("SetAppointment"); break;
                case "CreateClaimForRefusal": cur=hs.CreateClaimForRefusal("CreateClaimForRefusal"); break;
                case "SearchTop10Patient": cur=hs.SearchTop10Patient("SearchTop10Patient"); break;
                default: cur=hs.defaultList();
            }

            return cur;

            /*
            Cursor cursor = null;//db.getAllData();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return cursor;
            */
        }

    }
}
