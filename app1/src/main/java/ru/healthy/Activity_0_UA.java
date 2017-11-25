package ru.healthy;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Activity_0_UA extends ActivityBase {

    public Activity_0_UA() {
        super();
        spinner_arr = R.array.district;
        card_arr = R.array.lpu;
        list_arr = R.array.lpu;
    }

    @Override
    void init() {
        super.init();

        findViewById(R.id.label1).setVisibility(View.GONE);
        findViewById(R.id.label2).setVisibility(View.GONE);
        findViewById(R.id.label3).setVisibility(View.GONE);

        top_text = Storage.restore(this, "FIO");
        ((TextView) findViewById(R.id.text)).setText(top_text);

        ((TextView) findViewById(R.id.textview)).setText(top_text);
        ((Button) findViewById(R.id.button)).setText(R.string.save);
        ((TextView) findViewById(R.id.tv)).setText(R.string.menu4);

        findViewById(R.id.my_recycler_view).setVisibility(View.GONE);
        findViewById(R.id.list).setVisibility(View.GONE);
        findViewById(R.id.text).setVisibility(View.VISIBLE);
        findViewById(R.id.textview).setVisibility(View.GONE);
        findViewById(R.id.button).setVisibility(View.VISIBLE);
        findViewById(R.id.tv).setVisibility(View.VISIBLE);

        findViewById(R.id.tv).setOnClickListener(this);

        spinner_id = Integer.valueOf(Storage.restore(this, "currentArea"));
        ((Spinner) findViewById(R.id.spinner)).setSelection(spinner_id);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        String value;
        if (v.getId() == R.id.button) {

            value = ((TextView) findViewById(R.id.text)).getText().toString();
            Storage.store(this, "FIO", value);

            value = String.valueOf(((Spinner) findViewById(R.id.spinner)).getSelectedItemPosition());
            Storage.store(this, "currentArea", value);

            value = (String) ((Spinner) findViewById(R.id.spinner)).getSelectedItem();
            Storage.store(this, "currentArea_str", value);

            startActivity(new Intent(getApplicationContext(), Activity_1_ULH.class));
            finish();
        } else if (v.getId() == R.id.tv)
            startActivity(new Intent(getApplicationContext(), Activity_4_MAP.class));
    }

}
