package ru.healthy;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Activity_0_UA extends ActivityBase {

    public Activity_0_UA() {
        super();
        spinner_arr = "GetDistrictList";
        card_arr = "GetLPUList";
        list_arr = "GetLPUList";
    }

    @Override
    void init_Visiblity() {
        super.init_Visiblity();
        findViewById(R.id.text).setVisibility(View.VISIBLE);
        findViewById(R.id.textview).setVisibility(View.GONE);
        findViewById(R.id.list).setVisibility(View.GONE);
        findViewById(R.id.tv).setVisibility(View.VISIBLE);
    }

    @Override
    void init_Values() {
        super.init_Values();
        ((Button) findViewById(R.id.button)).setText(R.string.save);
        ((TextView) findViewById(R.id.tv)).setText(R.string.menu4);
        findViewById(R.id.tv).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.button) {

            String value = String.valueOf(((Spinner) findViewById(R.id.spinner)).getSelectedItemPosition());
            Storage.store(this, spinner_arr+"_pos", value);

            value = (String) ((Spinner) findViewById(R.id.spinner)).getSelectedItem();
            Storage.store(this, spinner_arr+"_str", value);
            Storage.store(this, "top_text_value", value);

            value = ((TextView) findViewById(R.id.text)).getText().toString();
            Storage.store(this, "FIO_value", value);

            startActivity(new Intent(getApplicationContext(), Activity_1_ULH.class));
            finish();
        }
        else if (v.getId() == R.id.tv) startActivity(new Intent(getApplicationContext(), Activity_4_MAP.class));
    }

}
