package ru.healthy;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

public class ActivityDRT extends ActivityDetail {
    public ActivityDRT() {
        super();
        txt = "Терапевт \nМамедова Ислам Акбаровна";
        spinner_arr = R.array.dates;
        recycl_arr = R.array.talons;
        list_arr = R.array.dates;
        tag = "Взять";
    }

    @Override
    void init() {
        super.init();
        findViewById(R.id.label1).setVisibility(View.GONE);
        findViewById(R.id.label2).setVisibility(View.GONE);
        findViewById(R.id.label3).setVisibility(View.GONE);

        findViewById(R.id.textview).setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.textview)).setText(txt);
        findViewById(R.id.text).setVisibility(View.GONE);
        findViewById(R.id.list).setVisibility(View.GONE);
        findViewById(R.id.my_recycler_view).setVisibility(View.VISIBLE);
        findViewById(R.id.button).setVisibility(View.GONE);
        findViewById(R.id.tv).setVisibility(View.VISIBLE);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(getApplicationContext(), ActivityDRT.class));
    }

}
