package ru.healthy;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityLSD extends ActivityDetail {

    public ActivityLSD() {
        super();
        this.id_contentView = R.layout.activity_detail;
        spinner_arr = R.array.spec;
        recycl_arr = R.array.doctors;
        list_arr = R.array.doctors;
    }

    @Override
    void init() {
        super.init();
        findViewById(R.id.label1).setVisibility(View.GONE);
        findViewById(R.id.label2).setVisibility(View.GONE);
        findViewById(R.id.label3).setVisibility(View.GONE);

        findViewById(R.id.my_recycler_view).setVisibility(View.GONE);
        findViewById(R.id.list).setVisibility(View.VISIBLE);
        findViewById(R.id.text).setVisibility(View.GONE);
        findViewById(R.id.textview).setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.text)).setText("Пб ГБУЗ \"Городская поликлиника №23\" Детское поликлиническое отделение №21");
        findViewById(R.id.button).setVisibility(View.INVISIBLE);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(getApplicationContext(), ActivityDRT.class));
    }
}
