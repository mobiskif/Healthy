package ru.healthy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityBase extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener, View.OnClickListener {
    int content_view = R.layout.activity_base;
    int spinner_pos = 0;
    String label1_text = "*";
    String textview_text = "*";
    String text_text = "*";
    String button_text = "*";
    String spinner_arr = "def_arr";
    String card_arr = "def_arr";
    String list_arr = "def_arr";
    boolean error=false;
    String TAG=getClass().getSimpleName()+" jop";

    void config_ToolbarAndMenu() {
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

    }

    void set_Visiblity() {
        findViewById(R.id.label1).setVisibility(View.VISIBLE);
        findViewById(R.id.label2).setVisibility(View.GONE);
        findViewById(R.id.label3).setVisibility(View.GONE);
        findViewById(R.id.text).setVisibility(View.GONE);
        findViewById(R.id.recycler).setVisibility(View.GONE);
        findViewById(R.id.tv).setVisibility(View.GONE);

        findViewById(R.id.textview).setOnClickListener(this);
        findViewById(R.id.button).setOnClickListener(this);
    }

    void attach_Adapters() {
        Spinner spinner = findViewById(R.id.spinner);
        if (spinner.getVisibility()==View.VISIBLE) {
            spinner.setOnItemSelectedListener(this);
            DataAdapter spinner_adapter = new DataAdapter (this, R.layout.item_spinner, spinner_arr);
            spinner.setAdapter(spinner_adapter);
        }

        ListView listView = findViewById(R.id.list);
        if (listView.getVisibility()==View.VISIBLE) {
            listView.setOnItemClickListener(this);
            DataAdapter list_adapter = new DataAdapter(this, android.R.layout.simple_list_item_1, list_arr);
            listView.setAdapter(list_adapter);
        }

        RecyclerView mRecyclerView = findViewById(R.id.recycler);
        if (mRecyclerView.getVisibility()==View.VISIBLE) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            CardAdapter card_adapter = new CardAdapter(card_arr, this, button_text);
            mRecyclerView.setAdapter(card_adapter);
        }

    }

    void restore_Values() {
        spinner_pos = Integer.valueOf(Storage.restore(this, spinner_arr+"_pos"));
        label1_text = Storage.restore(this, spinner_arr+"_str");
        textview_text = Storage.restore(this, spinner_arr+"_str");
        text_text = Storage.restore(this, "FIO");
        button_text = getString(R.string.button);
        //if (((Spinner)findViewById(R.id.spinner)).getAdapter().getCount() >= spinner_pos) ((Spinner) findViewById(R.id.spinner)).setSelection(spinner_pos);
        ((Spinner) findViewById(R.id.spinner)).setSelection(spinner_pos);
    }

    void show_Values() {
        ((TextView) findViewById(R.id.label1)).setText(label1_text);
        ((TextView) findViewById(R.id.text)).setText(text_text);
        ((TextView) findViewById(R.id.textview)).setText(textview_text);
        ((Button) findViewById(R.id.button)).setText(button_text);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(content_view);
        config_ToolbarAndMenu();

        set_Visiblity();
        attach_Adapters();
        restore_Values();
        show_Values();
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

            if (spinner_arr.equals("GetLPUList")) new DataAdapter(this, 666,"CheckPatient");

            ListView listView = findViewById(R.id.list);
            //if (listView.getVisibility()==View.VISIBLE) ((IDataAdapter) listView.getAdapter()).update();

            RecyclerView mRecyclerView = findViewById(R.id.recycler);
            //if (mRecyclerView.getVisibility()==View.VISIBLE) ((IDataAdapter) mRecyclerView.getAdapter()).update();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {  }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Toast.makeText(this, data.getStringExtra("result"), Toast.LENGTH_SHORT).show();
        }
    }

}
