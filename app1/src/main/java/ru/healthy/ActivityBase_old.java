package ru.healthy;

import android.content.Intent;
import android.database.DataSetObserver;
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

public class ActivityBase_old extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener, View.OnClickListener {
    int id_content;
    int spinner_id;
    String top_text;
    String key_top_text;
    String btn_text;
    String spinner_arr;
    String card_arr;
    String list_arr;
    String TAG;
    Storage storage;

    public ActivityBase_old() {
        super();
        id_content = R.layout.activity_base;
        spinner_id = 0;
        top_text = "*";
        key_top_text = "FIO";
        btn_text = "*";
        spinner_arr = "def_arr";
        card_arr = "def_arr";
        list_arr = "def_arr";
        TAG=this.getClass().getSimpleName()+" jop";
        storage = new Storage(this);
    }

    void init() {
        top_text = Storage.restore(this, key_top_text);
        ((Button) findViewById(R.id.button)).setText(R.string.button);
        ((TextView) findViewById(R.id.text)).setText(top_text);
        ((TextView) findViewById(R.id.textview)).setText(top_text);

        findViewById(R.id.label1).setVisibility(View.GONE);
        findViewById(R.id.label2).setVisibility(View.GONE);
        findViewById(R.id.label3).setVisibility(View.GONE);
        findViewById(R.id.text).setVisibility(View.GONE);
        findViewById(R.id.recycler).setVisibility(View.GONE);
        findViewById(R.id.tv).setVisibility(View.GONE);
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

        init();

        spinner_id = Integer.valueOf(Storage.restore(this, spinner_arr+"_pos"));

        TextView textView = findViewById(R.id.textview);
        textView.setOnClickListener(this);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);

        final Spinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        DataAdapter spinner_adapter = new DataAdapter (this, R.layout.item_spinner, spinner_arr);
        //spinner_adapter.setDropDownViewResource(R.layout.item_spinner);

        spinner_adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                //spinner.setSelection(spinner_id);
                Log.d(TAG,"Spinner априслал сообщение DataSetObserver-у: onChanged()");
            }
        });
        spinner.setAdapter(spinner_adapter);

        //if (((Spinner)findViewById(R.id.spinner)).getAdapter().getCount() >= spinner_id) ((Spinner) findViewById(R.id.spinner)).setSelection(spinner_id);

        ListView listView = findViewById(R.id.list);
        if (listView.getVisibility()==View.VISIBLE) {
            listView.setOnItemClickListener(this);
            DataAdapter list_adapter = new DataAdapter(this, android.R.layout.simple_list_item_1, list_arr);
            listView.setAdapter(list_adapter);
        }

        RecyclerView mRecyclerView = findViewById(R.id.recycler);
        if (mRecyclerView.getVisibility()==View.VISIBLE) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            CardAdapter card_adapter = new CardAdapter(card_arr, this, btn_text);
            mRecyclerView.setAdapter(card_adapter);
        }
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

            if (spinner_arr.equals("GetLPUList")) {
                DataAdapter da = new DataAdapter(this, 666,"CheckPatient");
            }

            ListView listView = findViewById(R.id.list);
            if (listView.getVisibility()==View.VISIBLE) {
                IDataAdapter ladapter = (IDataAdapter) listView.getAdapter();
                //ladapter.update();
            }

            RecyclerView mRecyclerView = findViewById(R.id.recycler);
            if (mRecyclerView.getVisibility()==View.VISIBLE) {
                IDataAdapter radapter = (IDataAdapter) mRecyclerView.getAdapter();
                //radapter.update();
            }


        }


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
