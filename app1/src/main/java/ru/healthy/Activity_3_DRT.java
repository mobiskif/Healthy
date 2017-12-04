package ru.healthy;

import android.content.Intent;
import android.database.DataSetObserver;
import android.util.Log;
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
        if (resultCode==RESULT_OK) {
            //FirebaseCrash.log("onActivityResult="+data.getDataString());
            //FirebaseCrash.report(data.getDataString());

            final DataAdapter adapter1 = new DataAdapter(this, requestCode, "SetAppointment");
            adapter1.registerDataSetObserver(new DataSetObserver() {
                @Override
                public void onChanged() {
                    super.onChanged();
                    String[] s = (String[]) adapter1.getItem(0);
                    Log.d(TAG, "######### курсор SetAppointment: " + s[0] + " " + s[1] + " " + s[2] + " " + s[3]);
                    //restore_Values();
                    //show_Values();
                }
            });


            String s = "Талончик успешно отложен!\n";
            s += Storage.restore(this, "GetAvaibleAppointments") + " ";
            //+ Storage.restore(this, "CheckPatient") + " "
            //+ Storage.restore(this, "GetLPUList");
            data.putExtra("result", s);
            super.onActivityResult(requestCode, resultCode, data);

            startActivity(new Intent(getApplicationContext(), Activity_1_ULH.class));
            finish();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent,view,position,id);
        Intent intent = new Intent(this, Activity_5_YN.class);
        intent.putExtra("message", getString(R.string.confirm_talon));
        startActivityForResult(intent, position);
    }


}
