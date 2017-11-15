package ru.healthy;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityUAL extends ActivityDetail {

    public ActivityUAL() {
        super();
        this.id_contentView = R.layout.activity_detail;
        spinner_arr = R.array.district;
        recycl_arr = R.array.lpu;
    }

    @Override
    void init() {
        super.init();
        findViewById(R.id.label1).setVisibility(View.GONE);
        findViewById(R.id.label2).setVisibility(View.GONE);
        findViewById(R.id.label3).setVisibility(View.GONE);
        findViewById(R.id.my_recycler_view).setVisibility(View.GONE);
        findViewById(R.id.list).setVisibility(View.GONE);
        findViewById(R.id.text).setVisibility(View.VISIBLE);
        findViewById(R.id.textview).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.text)).setText("Шишкин Владимир Петрович \n1947-09-20");
        ((Button) findViewById(R.id.button)).setText(R.string.save);
    }

    @Override
    public void onClick(View v) {
       if (v.getId()==R.id.button) startActivity(new Intent(getApplicationContext(), ActivityULH.class));
    }

}
