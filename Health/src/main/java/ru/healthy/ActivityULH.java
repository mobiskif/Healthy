package ru.healthy;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public class ActivityULH extends ActivityDetail {
    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    private DrawerLayout mDrawerLayout;

    public ActivityULH() {
        super();
        this.id_contentView = R.layout.activity_ulh;
        spinner_arr = R.array.lpu;
        recycl_arr = R.array.history;
        list_arr = R.array.history;
    }

    @Override
    void init() {
        super.init();
        if (getSupportActionBar() != null) {
            VectorDrawableCompat indicator = VectorDrawableCompat.create(getResources(), R.drawable.ic_menu, getTheme());
            indicator.setTint(ResourcesCompat.getColor(getResources(), R.color.white, getTheme()));
            getSupportActionBar().setHomeAsUpIndicator(indicator);
        }

        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        mFirebaseRemoteConfig.setDefaults(R.xml.config);

        mDrawerLayout = findViewById(R.id.drawer);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        String s = menuItem.getTitle().toString();
                        switch (s){
                            case "Главная": startActivity(new Intent(getApplicationContext(), ActivityULH.class));
                            break;
                            case "Пациент": startActivity(new Intent(getApplicationContext(), ActivityUAL.class));
                            break;
                            case "Поликлиника": startActivity(new Intent(getApplicationContext(), ActivityLSD.class));
                            break;
                            case "Доктор": startActivity(new Intent(getApplicationContext(), ActivityDRT.class));
                            break;
                        }
                        //startActivity(new Intent(getApplicationContext(), ActivityULH.class));
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });

        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();

        findViewById(R.id.label1).setVisibility(View.GONE);
        findViewById(R.id.label2).setVisibility(View.GONE);
        findViewById(R.id.label3).setVisibility(View.GONE);

        findViewById(R.id.my_recycler_view).setVisibility(View.VISIBLE);
        findViewById(R.id.list).setVisibility(View.GONE);
        findViewById(R.id.text).setVisibility(View.GONE);
        findViewById(R.id.textview).setVisibility(View.VISIBLE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //Log.d("jop", "Menu="+id);
        if (id == R.id.action_settings) {
            mFirebaseRemoteConfig.fetch(60)
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                mFirebaseRemoteConfig.activateFetched();
                                String welcomeMessage = mFirebaseRemoteConfig.getString("welcome_message");
                                Toast.makeText(ActivityULH.this, welcomeMessage, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ActivityULH.this, "Fetch Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            return true;
        } else if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.textview) startActivity(new Intent(getApplicationContext(), ActivityUAL.class));
        else if (v.getId()==R.id.button) startActivity(new Intent(getApplicationContext(), ActivityLSD.class));
    }

}
