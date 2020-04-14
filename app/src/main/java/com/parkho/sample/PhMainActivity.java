package com.parkho.sample;

import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class PhMainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_request_self).setOnClickListener(this);
        findViewById(R.id.btn_request_permission).setOnClickListener(this);
    }

    @Override
    public void onClick(View a_view) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
            if (powerManager.isIgnoringBatteryOptimizations(getPackageName()) == false) {
                PhBatteryDialog.newInstance(a_view.getId()).show(getSupportFragmentManager(), "dialog");
            }
        }

    }
}