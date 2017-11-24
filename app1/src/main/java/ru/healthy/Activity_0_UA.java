package ru.healthy;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity_0_UA extends ActivityBase {

    public Activity_0_UA() {
        super();
        spinner_arr = R.array.district;
        recycl_arr = R.array.lpu;
        list_arr = R.array.lpu;
    }

    @Override
    void init() {
        super.init();
        txt = getString(R.string.user) + "\n" + getString(R.string.date);
        //Log.d("jop*1",getClass().getName()+".init()");

        findViewById(R.id.label1).setVisibility(View.GONE);
        findViewById(R.id.label2).setVisibility(View.GONE);
        findViewById(R.id.label3).setVisibility(View.GONE);

        ((TextView)findViewById(R.id.text)).setText(txt);
        ((TextView)findViewById(R.id.textview)).setText(txt);
        ((Button) findViewById(R.id.button)).setText(R.string.save);

        findViewById(R.id.my_recycler_view).setVisibility(View.GONE);
        findViewById(R.id.list).setVisibility(View.GONE);
        findViewById(R.id.text).setVisibility(View.VISIBLE);
        findViewById(R.id.textview).setVisibility(View.GONE);
        findViewById(R.id.button).setVisibility(View.VISIBLE);
        findViewById(R.id.tv).setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
       if (v.getId()==R.id.button) startActivity(new Intent(getApplicationContext(), Activity_1_ULH.class));
    }

}
