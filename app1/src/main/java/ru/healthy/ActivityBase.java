package ru.healthy;

import android.content.Intent;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityBase extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener, View.OnClickListener {
    int id_content;
    int spinner_id;
    String top_text;
    String btn_text;
    String spinner_arr;
    String card_arr;
    String list_arr;
    String TAG = "jop";
    Storage storage;
    //boolean loaded = false;

    public ActivityBase() {
        super();
        storage = new Storage(this);
        id_content = R.layout.activity_base;
        spinner_arr = "def_arr";
        card_arr = "def_arr";
        list_arr = "def_arr";
    }

    void init() {
        top_text = "**";
        btn_text = "**";
        //spinner_id = Integer.valueOf(Storage.restore(this, spinner_arr+"_pos"));
        //if (((Spinner)findViewById(R.id.spinner)).getAdapter().getCount() >= spinner_id) ((Spinner) findViewById(R.id.spinner)).setSelection(spinner_id);


        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();

        spinner_id = Integer.valueOf(Storage.restore(this, spinner_arr+"_pos"));
        if (((Spinner)findViewById(R.id.spinner)).getAdapter().getCount() >= spinner_id) ((Spinner) findViewById(R.id.spinner)).setSelection(spinner_id);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(id_content);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Нажата FloatingActionButton", Snackbar.LENGTH_LONG).show();
            }
        });

        TextView textView = findViewById(R.id.textview);
        textView.setOnClickListener(this);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        DataAdapter spinner_adapter = new DataAdapter (this, R.layout.item_spinner, spinner_arr);
        //spinner_adapter.setDropDownViewResource(R.layout.item_spinner);
        spinner.setAdapter(spinner_adapter);

        ListView listView = findViewById(R.id.list);
        listView.setOnItemClickListener(this);
        DataAdapter list_adapter = new DataAdapter (this, android.R.layout.simple_list_item_1, list_arr);
        listView.setAdapter(list_adapter);

        RecyclerView mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //CardAdapter card_adapter = new CardAdapter(card_arr, this, btn_text);
        //mRecyclerView.setAdapter(card_adapter);

        init();
    }



    @Override
    public void onClick(View v) {
        //Toast.makeText(getApplicationContext(), "onClick", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(getApplicationContext(), "onItemClick", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(getApplicationContext(), "onItemSelected", Toast.LENGTH_LONG).show();
        if (((DataAdapter) parent.getAdapter()).loaded) {
            Storage.store(this, spinner_arr + "_pos", String.valueOf(position));
            if (view!=null) {
                String s = ((TextView) view).getText().toString();
                Storage.store(this, spinner_arr + "_str", s);
            }

            IDataAdapter ladapter = (IDataAdapter) ((ListView) findViewById(R.id.list)).getAdapter();
            ladapter.update();
        }


        //IDataAdapter radapter = (IDataAdapter) ((RecyclerView) findViewById(R.id.recycler)).getAdapter();
        //radapter.update();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Toast.makeText(this, data.getStringExtra("result"), Toast.LENGTH_SHORT).show();
        }

    }

}
