package ru.healthy;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Activity_1_ULH extends ActivityBase {
    private DrawerLayout mDrawerLayout;

    public Activity_1_ULH() {
        super();
        id_content = R.layout.activity_1_ulh;
        spinner_arr = "lpu";
        card_arr = "hist";
        list_arr = "hist";
        btn_text = "Отменить";
    }

    @Override
    void init() {
        super.init();
        //fetchWelcome();

        if (getSupportActionBar() != null) {
            VectorDrawableCompat indicator = VectorDrawableCompat.create(getResources(), R.drawable.ic_menu, getTheme());
            indicator.setTint(ResourcesCompat.getColor(getResources(), R.color.white, getTheme()));
            getSupportActionBar().setHomeAsUpIndicator(indicator);
        }

        mDrawerLayout = findViewById(R.id.drawer);
        NavigationView navigationView = findViewById(R.id.nav_view);
        prepareDrawerMenu(navigationView.getMenu());
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        doItem(menuItem);
                        return true;
                    }
                });

        //findViewById(R.id.label1).setVisibility(View.GONE);
        findViewById(R.id.label2).setVisibility(View.GONE);
        findViewById(R.id.label3).setVisibility(View.GONE);

        top_text = Storage.restore(this, "FIO");
        ((TextView) findViewById(R.id.text)).setText(top_text);
        ((TextView) findViewById(R.id.textview)).setText(top_text);
        ((Button) findViewById(R.id.button)).setText(R.string.button);

        findViewById(R.id.recycler).setVisibility(View.GONE);
        findViewById(R.id.list).setVisibility(View.VISIBLE);
        findViewById(R.id.text).setVisibility(View.GONE);
        findViewById(R.id.textview).setVisibility(View.VISIBLE);
        findViewById(R.id.button).setVisibility(View.VISIBLE);
        findViewById(R.id.tv).setVisibility(View.GONE);

        spinner_id = Integer.valueOf(Storage.restore(this, "currentLPU"));
        if (((Spinner) findViewById(R.id.spinner)).getAdapter().getCount() >= spinner_id)
            ((Spinner) findViewById(R.id.spinner)).setSelection(spinner_id);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.textview)
            startActivity(new Intent(getApplicationContext(), Activity_0_UA.class));
        else if (v.getId() == R.id.button)
            startActivity(new Intent(getApplicationContext(), Activity_2_LSD.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.menu_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) mDrawerLayout.openDrawer(GravityCompat.START);
        else doItem(item);
        return super.onOptionsItemSelected(item);
    }

    public void prepareDrawerMenu(Menu menu) {
        onCreateOptionsMenu(menu);

        String currentUser = Storage.getCurrentUser(this);
        int id = Integer.valueOf(currentUser);
        MenuItem item = menu.getItem(id);
        item.setIcon(R.drawable.redcross_small);
    }

    public void doItem(MenuItem menuItem) {
        //menuItem.setChecked(true);
        String s = menuItem.getTitle().toString();
        if (s.equals(getString(R.string.menu0)))
            startActivity(new Intent(getApplicationContext(), Activity_0_UA.class));
        else if (s.equals(getString(R.string.menu1)))
            startActivity(new Intent(getApplicationContext(), Activity_1_ULH.class));
        else if (s.equals(getString(R.string.menu2)))
            startActivity(new Intent(getApplicationContext(), Activity_2_LSD.class));
        else if (s.equals(getString(R.string.menu3)))
            startActivity(new Intent(getApplicationContext(), Activity_3_DRT.class));
        else if (s.equals(getString(R.string.menu4)))
            startActivity(new Intent(getApplicationContext(), Activity_4_MAP.class));
        else if (s.equals(getString(R.string.menu5)))
            startActivity(new Intent(getApplicationContext(), Activity_5_YN.class));
        else if (s.equals(getString(R.string.menu6)))
            startActivity(new Intent(getApplicationContext(), ActivityBase.class));
        else if (s.equals(getString(R.string.umenu0)))
            Storage.setCurrentUser(this, "0");
        else if (s.equals(getString(R.string.umenu1)))
            Storage.setCurrentUser(this, "1");
        else if (s.equals(getString(R.string.umenu2)))
            Storage.setCurrentUser(this, "2");
        mDrawerLayout.closeDrawers();
        init();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String value = String.valueOf(((Spinner) findViewById(R.id.spinner)).getSelectedItemPosition());
        Storage.store(this, "currentLPU", value);

        value = (String) ((Spinner) findViewById(R.id.spinner)).getSelectedItem();
        Storage.store(this, "currentLPU_str", value);

        super.onItemSelected(parent, view, position, id);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, Activity_5_YN.class);
        intent.putExtra("message", getString(R.string.cancel_talon));
        startActivityForResult(intent, 1);
    }


}
