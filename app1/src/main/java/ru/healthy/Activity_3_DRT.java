package ru.healthy;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_3_DRT extends ActivityBase {

    public Activity_3_DRT() {
        super();
        //txt = "Терапевт \nМMамедова Ислам Акбаровна";
        spinner_arr = R.array.dates;
        recycl_arr = R.array.talons;
        list_arr = R.array.dates;
        tag = "Взять";
    }

    @Override
    void init() {
        super.init();
        txt = getString(R.string.spec) + "\n" + getString(R.string.doctor);
        //Log.d("jop*3",getClass().getName()+".init()");

        findViewById(R.id.label1).setVisibility(View.GONE);
        findViewById(R.id.label2).setVisibility(View.GONE);
        findViewById(R.id.label3).setVisibility(View.GONE);

        ((TextView)findViewById(R.id.text)).setText(txt);
        ((TextView)findViewById(R.id.textview)).setText(txt);
        ((Button) findViewById(R.id.button)).setText(R.string.button);
        ((TextView)findViewById(R.id.tv)).setText(getString(R.string.button));

        findViewById(R.id.my_recycler_view).setVisibility(View.VISIBLE);
        findViewById(R.id.list).setVisibility(View.GONE);
        findViewById(R.id.text).setVisibility(View.GONE);
        findViewById(R.id.textview).setVisibility(View.VISIBLE);
        findViewById(R.id.button).setVisibility(View.GONE);
        findViewById(R.id.tv).setVisibility(View.VISIBLE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK) {
            Toast.makeText(this, getString(R.string.success) + " " + data.getStringExtra("result"), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), Activity_1_ULH.class));
            finish();
        }
    }

}
