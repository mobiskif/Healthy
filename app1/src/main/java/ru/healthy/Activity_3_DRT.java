package ru.healthy;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

public class Activity_3_DRT extends ActivityBase {

    public Activity_3_DRT() {
        super();
        spinner_arr = "GetWorkingTime";
        card_arr = "GetAvaibleAppointments";
        list_arr = "GetAvaibleAppointments";
        button_text = "Взять";
    }

    @Override
    void set_Visiblity() {
        super.set_Visiblity();
        findViewById(R.id.button).setVisibility(View.GONE);
    }

    @Override
    void restore_Values() {
        super.restore_Values();
        label1_text = Storage.restore(this, "GetSpesialityList_str");
        textview_text = Storage.restore(this, "GetDoctorList_str");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK) {
            Toast.makeText(this, getString(R.string.success) + " " + data.getStringExtra("result"), Toast.LENGTH_SHORT).show();

            //FirebaseCrash.log("onActivityResult="+data.getDataString());
            //FirebaseCrash.report(data.getDataString());

            startActivity(new Intent(getApplicationContext(), Activity_1_ULH.class));
            finish();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, Activity_5_YN.class);
        intent.putExtra("message", getString(R.string.confirm_talon));
        startActivityForResult(intent, 1);
    }


}
