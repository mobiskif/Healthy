package ru.healthy;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Date;

import static ru.healthy.R.drawable.redcross_small_;

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
                        switch (s) {
                            case "Главная":
                                startActivity(new Intent(getApplicationContext(), ActivityULH.class));
                                break;
                            case "Пациент":
                                startActivity(new Intent(getApplicationContext(), ActivityUAL.class));
                                break;
                            case "Поликлиника":
                                startActivity(new Intent(getApplicationContext(), ActivityLSD.class));
                                break;
                            case "Доктор":
                                startActivity(new Intent(getApplicationContext(), ActivityDRT.class));
                                break;
                            case "Dialog":
                                //startActivityForResult(new Intent(getApplicationContext(), ActivityYesNo.class), 1);
                                //shortcutAdd("Kuku", 12);
                                //shortcutDel();
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
        findViewById(R.id.button).setVisibility(View.VISIBLE);
        findViewById(R.id.tv).setVisibility(View.GONE);
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
        if (v.getId() == R.id.textview)
            startActivity(new Intent(getApplicationContext(), ActivityUAL.class));
        else if (v.getId() == R.id.button)
            startActivity(new Intent(getApplicationContext(), ActivityLSD.class));
    }

    private void shortcutAdd(String name, int number) { // Intent to be send, when shortcut is pressed by user ("launched")
        Intent shortcutIntent1 = new Intent();
        shortcutIntent1.setClassName("ru.healthy", "ActivityULH.class");
        Intent addIntent1 = new Intent();
        addIntent1.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent1);
        addIntent1.putExtra(Intent.EXTRA_SHORTCUT_NAME, "shortcut_name");
        addIntent1.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(this, R.drawable.redcross_small));
        addIntent1.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        sendBroadcast(addIntent1);

        Intent shortcutIntent = new Intent(getApplicationContext(), ActivityULH.class);
        shortcutIntent.setAction(Intent.ACTION_CREATE_SHORTCUT);
        // Create bitmap with number in it -> very default. You probably want to give it a more stylish look
        Bitmap bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        paint.setColor(0xFF808080);
        // gray
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(50);
        new Canvas(bitmap).drawText("" + number, 50, 50, paint);
        ((ImageView) findViewById(R.id.img)).setImageBitmap(bitmap);
        // Decorate the shortcut
        Intent addIntent = new Intent();
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON, bitmap);
        // Inform launcher to create shortcut
        addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        getApplicationContext().sendBroadcast(addIntent);
    }

    private void shortcutDel() {
        /*
        Intent shortcutIntent = new Intent(getApplicationContext(), ActivityULH.class);
        shortcutIntent.setAction(Intent.ACTION_DELETE);
        // Decorate the shortcut
        Intent delIntent = new Intent();
        delIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        delIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);
        // Inform launcher to remove shortcut
        delIntent.setAction("com.android.launcher.action.UNINSTALL_SHORTCUT");
        getApplicationContext().sendBroadcast(delIntent);
        */

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        int imageResourceId;
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        int hours = new Time(System.currentTimeMillis()).getHours();
        Log.d("DATE", "onCreate: " + hours);
        getPackageManager().setComponentEnabledSetting(getComponentName(), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        if (hours == 13) {
            imageResourceId = this.getResources().getIdentifier("redcross_small", "drawable", this.getPackageName());
            getPackageManager().setComponentEnabledSetting(new ComponentName("ru.healthy", "ActivityULH"), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        } else if (hours == 14) {
            imageResourceId = this.getResources().getIdentifier("redcross_small", "drawable", this.getPackageName());
            getPackageManager().setComponentEnabledSetting(new ComponentName("ru.healthy", "ActivityULH"), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        } else {
            imageResourceId = this.getResources().getIdentifier("redcross_small", "drawable", this.getPackageName());
            getPackageManager().setComponentEnabledSetting(new ComponentName("ru.healthy", "ActivityULH"), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        }
        imageView.setImageResource(imageResourceId);

    }


}
