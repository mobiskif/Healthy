package ru.healthy;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

public class Activity_2_LSD extends ActivityBase {

    public Activity_2_LSD() {
        super();
        spinner_arr = "GetSpesialityList";
        card_arr = "GetDoctorList";
        list_arr = "GetDoctorList";
    }
    @Override
    void set_Visiblity() {
        super.set_Visiblity();
        findViewById(R.id.button).setVisibility(View.GONE);
    }
    @Override
    void restore_Values() {
        super.restore_Values();
        label1_text = Storage.restore(this, "GetDistrictList_str") + " (" + Storage.restore(this, "CheckPatient") +")";
        textview_text = Storage.restore(this, "GetLPUList_str");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent,view,position,id);
        Intent intent = new Intent(this, Activity_3_DRT.class);
        startActivityForResult(intent, 1);
    }

}
