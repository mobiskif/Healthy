package ru.healthy;

import android.content.Intent;
import android.database.DataSetObserver;
import android.support.design.widget.NavigationView;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

public class Activity_1_ULH extends ActivityBase {
    private DrawerLayout mDrawerLayout;

    public Activity_1_ULH() {
        super();
        content_view = R.layout.activity_1_ulh;
        spinner_arr = "GetLPUList";
        card_arr = "GetPatientHistory";
        list_arr = "GetPatientHistory";
        button_text = "Талоны и расписание";
    }

    @Override
    void config_ToolbarAndMenu() {
        super.config_ToolbarAndMenu();
        if (getSupportActionBar() != null) {
            VectorDrawableCompat indicator = VectorDrawableCompat.create(getResources(), R.drawable.ic_menu, getTheme());
            indicator.setTint(ResourcesCompat.getColor(getResources(), R.color.white, getTheme()));
            getSupportActionBar().setHomeAsUpIndicator(indicator);
        }

        mDrawerLayout = findViewById(R.id.drawer);
        NavigationView navigationView = findViewById(R.id.nav_view);
        onCreateOptionsMenu(navigationView.getMenu());
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        doItem(menuItem);
                        return true;
                    }
                });
    }

    @Override
    void set_Visiblity() {
        super.set_Visiblity();
        findViewById(R.id.label1).setVisibility(View.VISIBLE);
    }

    @Override
    void restore_Values() {
        super.restore_Values();
        label1_text = Storage.restore(this, "GetDistrictList_str") + " (" + Storage.restore(this, "CheckPatient") +")";
        if (label1_text.length()<5) label1_text="Нахмите сюда, заполните ФИО и район";
        //label1_text = Storage.restore(this, "CheckPatient");
        textview_text = Storage.restore(this, "FIO");
        if (textview_text.length()<5) textview_text="Нажмите сюда, заполните ФИО и район";
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        if (v.getId() == R.id.textview) startActivity(new Intent(getApplicationContext(), Activity_0_UA.class));
        else if (v.getId() == R.id.button) startActivity(new Intent(getApplicationContext(), Activity_2_LSD.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.menu_drawer, menu);

        String currentUser = Storage.getCurrentUser(this);
        int id = Integer.valueOf(currentUser);
        MenuItem item = menu.getItem(id);
        item.setIcon(R.drawable.redcross_small);
        /**/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) mDrawerLayout.openDrawer(GravityCompat.START);
        else doItem(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent,view,position,id);
        Intent intent = new Intent(this, Activity_5_YN.class);
        intent.putExtra("message", getString(R.string.cancel_talon));
        startActivityForResult(intent, position);
    }

    public void doItem(MenuItem menuItem) {
        //menuItem.setChecked(true);
        String s = menuItem.getTitle().toString();
        if (s.equals(getString(R.string.menu0))) startActivity(new Intent(getApplicationContext(), Activity_0_UA.class));
        else if (s.equals(getString(R.string.umenu0))) Storage.setCurrentUser(this, "0");
        else if (s.equals(getString(R.string.umenu1))) Storage.setCurrentUser(this, "1");
        else if (s.equals(getString(R.string.umenu2))) Storage.setCurrentUser(this, "2");
        mDrawerLayout.closeDrawers();

        Spinner spinner = findViewById(R.id.spinner);
        ((DataAdapter) spinner.getAdapter()).update();
        restore_Values();
        show_Values();
        spinner.setSelection(spinner_pos);

        ListView listView = findViewById(R.id.list);
        if (listView.getVisibility()==View.VISIBLE) {
            final DataAdapter adapter2 = (DataAdapter) listView.getAdapter();
            adapter2.update();
        }


        NavigationView navigationView = findViewById(R.id.nav_view);
        onCreateOptionsMenu(navigationView.getMenu());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode==RESULT_OK) {
            //FirebaseCrash.log("onActivityResult="+data.getDataString());
            //FirebaseCrash.report(data.getDataString());

            final DataAdapter adapter1 = new DataAdapter(this, requestCode, "CreateClaimForRefusal");
            adapter1.registerDataSetObserver(new DataSetObserver() {
                @Override
                public void onChanged() {
                    super.onChanged();
                    String[] s = (String[]) adapter1.getItem(0);
                    Log.d(TAG, "######### курсор CreateClaimForRefusal: " + s[0] + " " + s[1] + " " + s[2] + " " + s[3]);
                    //restore_Values();
                    //show_Values();
                }
            });


            String s = "Талончик успешно отменен!\n";
            s += Storage.restore(this, "GetPatientHistory") + " ";
            //+ Storage.restore(this, "CheckPatient") + " "
            //+ Storage.restore(this, "GetLPUList");
            data.putExtra("result", s);
            super.onActivityResult(requestCode, resultCode, data);

            startActivity(new Intent(getApplicationContext(), Activity_1_ULH.class));
            finish();
        }
    }
}
