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

import com.google.android.gms.common.data.DataBufferObserver;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class DataAdapter extends BaseAdapter implements IDataAdapter, android.app.LoaderManager.LoaderCallbacks<Cursor> {
    Activity context;
    int resource;
    String action;
    String[] arr;
    boolean loaded;
    String TAG;

    public DataAdapter(Activity c, int r, String a) {
        context = c;
        resource = r;
        action = a;
        arr = context.getResources().getStringArray(R.array.def_arr);
        loaded=false;
        TAG=this.getClass().getSimpleName()+" jop";

        context.getLoaderManager().initLoader(resource, null, this);
    }

    @Override
    public void update() {
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
        arr = toArr(data);
        loaded=true;
        Log.d(TAG,"load "+action+" finished");
        notifyDataSetChanged();

        if (action.equals("CheckPatient")) {
            data.moveToFirst();
            String idPat=data.getString(3);
            Storage.store(context, "idPat", idPat);
            String s = toStr(data);
            Log.d(TAG,"CheckPatient = "+s);
        }
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

    private String toStr(Cursor data) {
        String r="";
        data.moveToFirst();
        while(!data.isAfterLast()) {
            r+=data.getString(0)+ " ";
            r+=data.getString(1)+ " ";
            r+=data.getString(2)+ " ";
            r+=data.getString(3);
            data.moveToNext();
        }
        data.close();
        return r;
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
