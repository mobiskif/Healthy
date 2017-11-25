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
import android.widget.Button;
import android.widget.TextView;

public class Activity_1_ULH extends ActivityBase {
    private DrawerLayout mDrawerLayout;

    public Activity_1_ULH() {
        super();
        this.id_contentView = R.layout.activity_1_ulh;
        spinner_arr = R.array.lpu;
        recycl_arr = R.array.history;
        list_arr = R.array.history;
        btn_text = "Отменить";

    }

    @Override
    void init() {
        super.init();
        txt = getString(R.string.user) + "\n" + getString(R.string.date);
        //Log.d("jop*1",getClass().getName()+".init()" + txt);


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

        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();


        //findViewById(R.id.label1).setVisibility(View.GONE);
        findViewById(R.id.label2).setVisibility(View.GONE);
        findViewById(R.id.label3).setVisibility(View.GONE);

        ((TextView)findViewById(R.id.text)).setText(txt);
        ((TextView)findViewById(R.id.textview)).setText(txt);
        ((Button) findViewById(R.id.button)).setText(R.string.button);

        findViewById(R.id.my_recycler_view).setVisibility(View.VISIBLE);
        findViewById(R.id.list).setVisibility(View.GONE);
        findViewById(R.id.text).setVisibility(View.GONE);
        findViewById(R.id.textview).setVisibility(View.VISIBLE);
        findViewById(R.id.button).setVisibility(View.VISIBLE);
        findViewById(R.id.tv).setVisibility(View.GONE);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.textview) startActivity(new Intent(getApplicationContext(), Activity_0_UA.class));
        else if (v.getId() == R.id.button) startActivity(new Intent(getApplicationContext(), Activity_2_LSD.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) mDrawerLayout.openDrawer(GravityCompat.START);
        else doItem(item);
        return super.onOptionsItemSelected(item);
    }

    public boolean prepareDrawerMenu(Menu menu) {
        //menu.clear(); menu.add ("Ивнов Иван Иванович");
        MenuItem item = menu.getItem(1);
        item.setIcon(R.drawable.redcross_small);
        item.setTitle("Главная страница");
        return true;
    }

    public void doItem(MenuItem menuItem) {
        menuItem.setChecked(true);
        String s = menuItem.getTitle().toString();
        if      (s.equals(getString(R.string.menu0))) startActivity(new Intent(getApplicationContext(), Activity_0_UA.class));
        else if (s.equals(getString(R.string.menu1))) startActivity(new Intent(getApplicationContext(), Activity_1_ULH.class));
        else if (s.equals(getString(R.string.menu2))) startActivity(new Intent(getApplicationContext(), Activity_2_LSD.class));
        else if (s.equals(getString(R.string.menu3))) startActivity(new Intent(getApplicationContext(), Activity_3_DRT.class));
        else if (s.equals(getString(R.string.menu4))) startActivity(new Intent(getApplicationContext(), Activity_4_MAP.class));
        else if (s.equals(getString(R.string.menu5))) startActivity(new Intent(getApplicationContext(), Activity_5_YN.class));
        else if (s.equals(getString(R.string.menu6))) startActivity(new Intent(getApplicationContext(), ActivityBase.class));
        mDrawerLayout.closeDrawers();
    }

}
