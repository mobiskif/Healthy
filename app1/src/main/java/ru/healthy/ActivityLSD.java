package ru.healthy;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

public class ActivityLSD extends ActivityBase {

    public ActivityLSD() {
        super();
        txt = "Пб ГБУЗ \"Городская поликлиника №23\" Детское отделение №21";
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
        ((TextView)findViewById(R.id.textview)).setText(txt);
        findViewById(R.id.button).setVisibility(View.GONE);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(getApplicationContext(), ActivityDRT.class));
    }
}
