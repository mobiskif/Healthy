package ru.healthy;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

public class ActivityDetail extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener, View.OnClickListener {
    private FirebaseAnalytics mFirebaseAnalytics;
    //ActionBar supportActionBar;
    int id_contentView;
    int spinner_arr;
    int recycl_arr;
    int list_arr;

    public ActivityDetail() {
        super();
        id_contentView = R.layout.activity_detail;
        spinner_arr = R.array.places;
        recycl_arr = R.array.place_details;
        list_arr = R.array.place_desc;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                Toast.makeText(getApplicationContext(), "Нажата кнопка Help! (toast)", Toast.LENGTH_LONG).show();
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "это ActivityULH");
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Нажата кнопка Help! (toast)");
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Событие");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
            }
        });

        TextView textView = findViewById(R.id.textview);
        textView.setOnClickListener(this);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> spinner_adapter = new ArrayAdapter<>(this, R.layout.item_spinner, getResources().getStringArray(spinner_arr));
        spinner_adapter.setDropDownViewResource(R.layout.item_spinner_down);
        spinner.setAdapter(spinner_adapter);

        RecyclerView mRecyclerView = findViewById(R.id.my_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new CardAdapter(getResources().getStringArray(recycl_arr)));

        ListView listView = findViewById(R.id.list);
        listView.setOnItemClickListener(this);
        //String[] names = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис", "Костя", "Игорь", "Анна", "Денис", "Андрей" };
        String[] names = getResources().getStringArray(list_arr);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        listView.setAdapter(adapter);

        init();
    }

    void init() {

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
    public void onClick(View v) {
        Toast.makeText(getApplicationContext(), "onClick", Toast.LENGTH_LONG).show();
    }
}
