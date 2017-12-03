package ru.healthy;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_0_UA extends ActivityBase {

    public Activity_0_UA() {
        super();
        spinner_arr = "GetDistrictList";
        card_arr = "GetLPUList";
        list_arr = "GetLPUList";
    }

    @Override
    void set_Visiblity() {
        super.set_Visiblity();
        findViewById(R.id.label1).setVisibility(View.GONE);
        findViewById(R.id.text).setVisibility(View.VISIBLE);
        findViewById(R.id.textview).setVisibility(View.GONE);
        findViewById(R.id.list).setVisibility(View.GONE);
        findViewById(R.id.tv).setVisibility(View.VISIBLE);
        findViewById(R.id.spinner).setVisibility(View.VISIBLE);
    }

    @Override
    void show_Values() {
        super.show_Values();
        ((Button) findViewById(R.id.button)).setText(R.string.save);
        findViewById(R.id.tv).setOnClickListener(this);
    }

    void parseFIO(String s) {
        String[] ar = s.split(" ");
        if (ar.length==4) {
            Storage.store(this, "Surname", ar[0]);
            Storage.store(this, "Name", ar[1]);
            Storage.store(this, "Secondname", ar[2]);
            Storage.store(this, "Birstdate", ar[3]);
            error=false;
        }
        else {
            Storage.store(this, "Surname", "Фамилия");
            Storage.store(this, "Name", "Имя");
            Storage.store(this, "Secondname", "Отчество");
            Storage.store(this, "Birstdate", "2001-11-23");
            error=true;
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        if (v.getId() == R.id.button) {
/*
            String value = String.valueOf(((Spinner) findViewById(R.id.spinner)).getSelectedItemPosition());
            Storage.store(this, spinner_arr+"_pos", value);

            String[] svalue = (String[]) ((Spinner) findViewById(R.id.spinner)).getSelectedItem();
            Storage.store(this, spinner_arr+"_str", svalue[1]);

            Spinner spinner = findViewById(R.id.spinner);
            DataAdapter adapter = (DataAdapter) spinner.getAdapter();
            String [] row = (String[]) adapter.getItem(spinner.getSelectedItemPosition());
            Storage.store(this, spinner_arr, row[0]);
*/
            String tvalue = ((TextView) findViewById(R.id.text)).getText().toString();
            Storage.store(this, "FIO", tvalue);
            parseFIO(tvalue);

            if (error) Toast.makeText(this, "Заполните ФИО и дату точно, как в примере", Toast.LENGTH_LONG).show();
            else {
                startActivity(new Intent(getApplicationContext(), Activity_1_ULH.class));
                finish();
            }
        }
        else if (v.getId() == R.id.tv) startActivity(new Intent(getApplicationContext(), Activity_4_MAP.class));
    }

}
