package ru.healthy;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class Activity_2_LSD extends ActivityBase {

    public Activity_2_LSD() {
        super();
        spinner_arr = R.array.spec;
        card_arr = R.array.doctors;
        list_arr = R.array.doctors;
    }

    @Override
    void init() {
        super.init();

        ((TextView) findViewById(R.id.label1)).setText(Storage.restore(this, "currentArea_str"));
        //findViewById(R.id.label1).setVisibility(View.GONE);
        findViewById(R.id.label2).setVisibility(View.GONE);
        findViewById(R.id.label3).setVisibility(View.GONE);

        top_text = Storage.restore(this, "currentLPU_str");
        ((TextView)findViewById(R.id.textview)).setText(top_text);
        ((TextView)findViewById(R.id.text)).setText(top_text);
        ((Button) findViewById(R.id.button)).setText(R.string.button);
        ((TextView)findViewById(R.id.tv)).setText(getString(R.string.menu3));

        findViewById(R.id.my_recycler_view).setVisibility(View.GONE);
        findViewById(R.id.list).setVisibility(View.VISIBLE);
        findViewById(R.id.text).setVisibility(View.GONE);
        findViewById(R.id.textview).setVisibility(View.VISIBLE);
        findViewById(R.id.button).setVisibility(View.GONE);
        findViewById(R.id.tv).setVisibility(View.VISIBLE);

        spinner_id = Integer.valueOf(Storage.restore(this, "currentSpec"));
        ((Spinner) findViewById(R.id.spinner)).setSelection(spinner_id);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String value = ((TextView) view).getText().toString();
        Storage.store(this, "currentDoctor_str", value);

        startActivity(new Intent(getApplicationContext(), Activity_3_DRT.class));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        super.onItemSelected(parent,view,position,id);

        String value = String.valueOf(((Spinner) findViewById(R.id.spinner)).getSelectedItemPosition());
        Storage.store(this, "currentSpec", value);

        value = (String) ((Spinner) findViewById(R.id.spinner)).getSelectedItem();
        Storage.store(this, "currentSpec_str", value);

    }
}
