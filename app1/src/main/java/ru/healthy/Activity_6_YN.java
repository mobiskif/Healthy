package ru.healthy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

public class Activity_6_YN extends AppCompatActivity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yesno);

        findViewById(R.id.Yes).setOnClickListener(this);
        findViewById(R.id.No).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if (v.getId()==R.id.Yes) {
            setResult(RESULT_OK, intent);
            intent.putExtra("result", "Ok");
        }
        else {
            setResult(RESULT_CANCELED, intent);
            intent.putExtra("result", "Отмена");
        }
        finish();
    }
}
