package ru.healthy;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_6_help extends ActivityBase {
    public Activity_6_help() {
        super();
        //content_view = R.layout.activity_base;
    }

    @Override
    void set_Visiblity() {
        super.set_Visiblity();
        findViewById(R.id.label1).setVisibility(View.VISIBLE);
        findViewById(R.id.text).setVisibility(View.GONE);
        findViewById(R.id.textview).setVisibility(View.GONE);
        findViewById(R.id.list).setVisibility(View.GONE);
        findViewById(R.id.tv).setVisibility(View.GONE);
        findViewById(R.id.spinner).setVisibility(View.GONE);
        findViewById(R.id.button).setVisibility(View.GONE);


    }


    @Override
    void restore_Values() {
        label1_text = getString(R.string.help_text);
    }
}
