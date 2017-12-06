package ru.healthy;

import android.database.DataSetObserver;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import static java.lang.Math.random;

public class Activity_4_MAP extends FragmentActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        addMarkers(googleMap);
        LatLng spb = new LatLng(59.94, 30.29);
        //googleMap.addMarker(new MarkerOptions().position(spb).title("Санкт-Петербург"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(spb));
    }

    LatLng getLocationFromAddress(String strAddress) {
        Geocoder coder = new Geocoder(this);
        try {
            List<Address> address;
            address = coder.getFromLocationName(strAddress, 5);
            Address location = address.get(0);
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            Log.d("jop", lat+" "+lng);
            return new LatLng(lat, lng);
        }
        catch (Exception e) { return new LatLng(0, 0); }
    }

    void addMarkers(final GoogleMap googleMap) {
        int i = (int) (random() * 10000);
        final DataAdapter adapter = new DataAdapter(this, i, "GetOrgList");
        adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                LatLng spb = new LatLng(59.94, 30.29);
                for (int j=0; j<adapter.getCount(); j++) {
                    if (j>10) break;
                    String[] item = (String[]) adapter.getItem(j);
                    //Log.d("jop", "0: " + item[0] + " 1: " + item[1] + " 2: " + item[2] + " 3: " + item[3]);
                    spb = getLocationFromAddress(item[2]);
                    googleMap.addMarker(new MarkerOptions().position(spb).title(item[1]));
                }

                /*
                Cursor cur = (Cursor) o;

                int nrows = cur.getCount();

                Log.d("jop", "===== rows="+nrows);
                Log.d("jop", "===== rows="+nrows);

                if (nrows > 10) nrows = 10;

                for (int i = 0; i < nrows; i++) {

                    cur.moveToPosition(i);

                    MatrixCursor item = (MatrixCursor) cur;

                    String name=item.getString(1);

                    String address=item.getString(2);

                    Log.d("jop", name + " "+ address);

                    mapCenter = getLocationFromAddress(address);

                    googleMap.addMarker(new MarkerOptions()

                            .position(mapCenter)

                            .title(name)

                            .snippet(item.getString(0))

                    )//.showInfoWindow()

                    ;

                }

                //googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.style_json));

                googleMap.moveCamera

                        /**/
            }
        });

    }

}
