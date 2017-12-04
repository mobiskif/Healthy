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

public class ActivityBase extends AppCompatActivity implements  AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener, View.OnClickListener {
    int content_view = R.layout.activity_base;
    int spinner_pos = 0;
    String label1_text = "*";
    String label2_text = "*";
    String textview_text = "*";
    String text_text = "*";
    String button_text = "*";
    String spinner_arr = "GetDistrictList";
    String card_arr = "ca";
    String list_arr = "GetLPUList";
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
        findViewById(R.id.tv).setOnClickListener(this);
    }

    void attach_Adapters() {
        final Spinner spinner = findViewById(R.id.spinner);
        if (spinner.getVisibility()==View.VISIBLE) {
            spinner.setOnItemSelectedListener(this);
            DataAdapter spinner_adapter = new DataAdapter (this, R.layout.item_spinner, spinner_arr);
            spinner.setAdapter(spinner_adapter);

            spinner_adapter.registerDataSetObserver(new DataSetObserver() {
                @Override
                public void onChanged() {
                    super.onChanged();
                    //Log.d(TAG,"адаптер спинера (" + spinner_arr+") прислал сообщение наблюдателю, можно делать Restore");
                    spinner.setSelection(spinner_pos);
                }
            });
        }

        final ListView listView = findViewById(R.id.list);
        if (listView.getVisibility()==View.VISIBLE) {
            listView.setOnItemClickListener(this);
            final DataAdapter list_adapter = new DataAdapter(this, android.R.layout.simple_list_item_1, list_arr);
            listView.setAdapter(list_adapter);

            list_adapter.registerDataSetObserver(new DataSetObserver() {
                @Override
                public void onChanged() {
                    super.onChanged();
                    //Log.d(TAG,"адаптер списка (" + list_arr+") прислал сообщение наблюдателю, можно делать Restore");
                    listView.invalidateViews();
                }
            });
        }

        RecyclerView mRecyclerView = findViewById(R.id.recycler);
        if (mRecyclerView.getVisibility()==View.VISIBLE) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            CardAdapter card_adapter = new CardAdapter(card_arr, this, button_text);
            mRecyclerView.setAdapter(card_adapter);
        }
    }

    void restore_Values() {
        label2_text = Storage.restore(this, "CheckPatient");
        label1_text = Storage.restore(this, spinner_arr+"_str");
        textview_text = Storage.restore(this, spinner_arr+"_str");
        text_text = Storage.restore(this, "FIO");
        spinner_pos = Integer.valueOf(Storage.restore(this, spinner_arr+"_pos"));
    }

    void show_Values() {
        ((TextView) findViewById(R.id.label1)).setText(label1_text);
        ((TextView) findViewById(R.id.label2)).setText(label2_text);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DataAdapter adapter = (DataAdapter) parent.getAdapter();
        if (adapter.loaded) {
            String[] row = (String[]) adapter.getItem(position);
            Storage.store(this, list_arr, row[0]);
            Storage.store(this, list_arr + "_str", row[1]);
            Storage.store(this, list_arr + "_pos", String.valueOf(position));
            //Log.d(TAG, "onItemClick() сохранено в SharedPref: " + row[0] + " " + row[1] + " " + row[2] + " ");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        DataAdapter adapter = (DataAdapter) parent.getAdapter();
        if (adapter.loaded) {
            String [] row = (String[]) adapter.getItem(position);
            Storage.store(this, spinner_arr, row[0]);
            Storage.store(this, spinner_arr+"_str", row[1]);
            Storage.store(this, spinner_arr + "_pos", String.valueOf(position));
            //Log.d(TAG, "onItemSelected() сохранено в SharedPref: "+row[0] +" "+row[1] +" "+row[2] +" ");

            if (spinner_arr.equals("GetLPUList")) {
                final DataAdapter adapter1 = new DataAdapter(this, position,"CheckPatient");
                adapter1.registerDataSetObserver(new DataSetObserver() {
                    @Override
                    public void onChanged() {
                        super.onChanged();
                        String[] s = (String[]) adapter1.getItem(0);
                        Log.d(TAG, "курсор CheckPatient: "+s[0]+" "+s[1]+ " " +s[2]+ " " +s[3]);
                        restore_Values();
                        show_Values();
                    }
                });
            }

            ListView listView = findViewById(R.id.list);
            if (listView.getVisibility()==View.VISIBLE) {
                final DataAdapter adapter2 = (DataAdapter) listView.getAdapter();
                adapter2.update();
            }
            //RecyclerView mRecyclerView = findViewById(R.id.recycler); if (mRecyclerView.getVisibility()==View.VISIBLE) updateObserver((AdapterView) mRecyclerView);

            restore_Values();
            show_Values();
        }
        else {
            Log.d(TAG,"onItemSelected, но адаптер спинера еще не обновился");
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

    @Override
    public void onClick(View view) {

    }
}
