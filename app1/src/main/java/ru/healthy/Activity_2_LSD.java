package ru.healthy;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

public class Activity_2_LSD extends ActivityBase {

    public Activity_2_LSD() {
        super();
        spinner_arr = "GetSpesialityList";
        card_arr = "GetDoctorList";
        list_arr = "GetDoctorList";
    }

    @Override
    void restore_Values() {
        super.restore_Values();
        label1_text = Storage.restore(this, "GetDistrictList_str");
        textview_text = Storage.restore(this, "GetLPUList_str");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Storage.store(this, "currentDoctor_str", ((TextView) view).getText().toString());
        startActivity(new Intent(getApplicationContext(), Activity_3_DRT.class));
    }

}
