package ru.healthy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public class ActivityBase extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener, View.OnClickListener {
    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    int id_contentView;
    String txt;
    String tag;
    int spinner_arr;
    int recycl_arr;
    int list_arr;
    Context context;
    RecyclerView mRecyclerView;
    AppCompatActivity activity;

    public ActivityBase() {
        super();
        txt = "bla-bla-bla";
        tag = "bla-bla";
        id_contentView = R.layout.activity_base;
        spinner_arr = R.array.places;
        recycl_arr = R.array.place_details;
        list_arr = R.array.place_desc;
    }

    void init() {
        Log.d("jop",getClass().getName()+".init()");
        /*
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        mFirebaseRemoteConfig.setDefaults(R.xml.config);
        Log.d("jop=","start fetch");
        mFirebaseRemoteConfig.fetch(60)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //mFirebaseRemoteConfig.activateFetched();
                            //tag = mFirebaseRemoteConfig.getString("welcome_message");
                            Log.d("jop=","Fetched tag= " + tag);
                            mRecyclerView.setAdapter(new CardAdapter(getResources().getStringArray(recycl_arr), activity, tag));

                        } else {
                            //tag = mFirebaseRemoteConfig.getString("welcome_message");
                            Log.d("jop=","Fetched error tag= " + tag);
                            mRecyclerView.setAdapter(new CardAdapter(getResources().getStringArray(recycl_arr), activity, tag));
                        }
                    }
                });
                */
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("jop",getClass().getName()+".onCreate()");
        activity=this;

        setContentView(id_contentView);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Нажата кнопка Help! (snackbar)", Snackbar.LENGTH_LONG).show();
            }
        });

        TextView textView = findViewById(R.id.textview);
        textView.setOnClickListener(this);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> spinner_adapter = new ArrayAdapter<>(this, R.layout.item_spinner, getResources().getStringArray(spinner_arr));
        spinner_adapter.setDropDownViewResource(R.layout.item_spinner);
        spinner.setAdapter(spinner_adapter);

        mRecyclerView = findViewById(R.id.my_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new CardAdapter(getResources().getStringArray(recycl_arr), this, tag));

        ListView listView = findViewById(R.id.list);
        listView.setOnItemClickListener(this);
        //String[] names = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис", "Костя", "Игорь", "Анна", "Денис", "Андрей" };
        String[] names = getResources().getStringArray(list_arr);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        listView.setAdapter(adapter);

        init();
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "это ActivityBase");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, v.toString());
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Событие");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        Toast.makeText(getApplicationContext(), "onClick", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), "onItemClick", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), "onItemSelected", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode==RESULT_OK) Toast.makeText(this, data.getStringExtra("result"), Toast.LENGTH_SHORT).show();
    }

}
