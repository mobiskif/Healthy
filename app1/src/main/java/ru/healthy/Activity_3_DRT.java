package ru.healthy;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.crash.FirebaseCrash;

public class Activity_3_DRT extends ActivityBase {

    public Activity_3_DRT() {
        super();
        spinner_arr = "dates";
        card_arr = "talons";
        list_arr = "talons";
        btn_text = "Взять";
    }

    @Override
    void init() {
        super.init();

        findViewById(R.id.label1).setVisibility(View.GONE);
        findViewById(R.id.label2).setVisibility(View.GONE);
        findViewById(R.id.label3).setVisibility(View.GONE);

        top_text = Storage.restore(this, "currentSpec_str") + "\n"+ Storage.restore(this, "currentDoctor_str");
        ((TextView)findViewById(R.id.text)).setText(top_text);
        ((TextView)findViewById(R.id.textview)).setText(top_text);
        ((Button) findViewById(R.id.button)).setText(R.string.button);
        ((TextView)findViewById(R.id.tv)).setText(getString(R.string.button));

        findViewById(R.id.my_recycler_view).setVisibility(View.VISIBLE);
        findViewById(R.id.list).setVisibility(View.GONE);
        findViewById(R.id.text).setVisibility(View.GONE);
        findViewById(R.id.textview).setVisibility(View.VISIBLE);
        findViewById(R.id.button).setVisibility(View.GONE);
        findViewById(R.id.tv).setVisibility(View.VISIBLE);

        spinner_id = Integer.valueOf(Storage.restore(this, "currentDate"));
        if (((Spinner)findViewById(R.id.spinner)).getAdapter().getCount() >= spinner_id) ((Spinner) findViewById(R.id.spinner)).setSelection(spinner_id);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK) {
            Toast.makeText(this, getString(R.string.success) + " " + data.getStringExtra("result"), Toast.LENGTH_SHORT).show();

            FirebaseCrash.log("onActivityResult="+data.getDataString());
            //FirebaseCrash.report(data.getDataString());

            startActivity(new Intent(getApplicationContext(), Activity_1_ULH.class));
            finish();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String value = String.valueOf(((Spinner) findViewById(R.id.spinner)).getSelectedItemPosition());
        Storage.store(this, "currentDate", value);

        super.onItemSelected(parent,view,position,id);
    }

}
