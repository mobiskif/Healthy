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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class DataAdapter extends BaseAdapter implements IDataAdapter, android.app.LoaderManager.LoaderCallbacks<Cursor> {
    Activity context;
    int resource;
    String action;
    String[] arr;

    public DataAdapter(Activity con, int res, String act) {
        //super(con, res, (new Storage(con).getStringArray(act)));
        //arr = new Storage(con).getStringArray(act);
        context = con;
        resource = res;
        action = act;
        arr = new Storage(context).getStringArray("place_avator");
        Log.d("jop","initiate ====== place_avator");
        con.getLoaderManager().initLoader(resource, null, this);
        //update();
    }

    @Override
    public void update() {
        Log.d("jop","update start ====== "+action);
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
        return new MyCursorLoader(context, action);
        //return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        //scAdapter.swapCursor(cursor);
        arr = toArr(data);
        //arr = new Storage(context).getStringArray(action);
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
                case "lpu": cur=hs.GetLPUList("GetLPUList"); break;
                case "area": cur=hs.GetDistrictList("GetDistrictList"); break;
                case "CheckPatient": cur=hs.CheckPatient("CheckPatient"); break;
                case "hist": cur=hs.GetPatientHistory("GetPatientHistory"); break;
                case "spec": cur=hs.GetSpesialityList("GetSpesialityList"); break;
                case "doc": cur=hs.GetDoctorList("GetDoctorList"); break;
                case "talons": cur=hs.GetAvaibleAppointments("GetAvaibleAppointments"); break;
                case "GetWorkingTime": cur=hs.GetWorkingTime("GetWorkingTime"); break;
                case "SetAppointment": cur=hs.SetAppointment("SetAppointment"); break;
                case "CreateClaimForRefusal": cur=hs.CreateClaimForRefusal("CreateClaimForRefusal"); break;
                case "SearchTop10Patient": cur=hs.SearchTop10Patient("SearchTop10Patient"); break;
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
