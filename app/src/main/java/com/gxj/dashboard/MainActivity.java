package com.gxj.dashboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import edu.hrbeu.ice.dashboard.R;


public class MainActivity extends AppCompatActivity {
    private DashboardCustom wat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wat = (DashboardCustom) findViewById(R.id.wat);

        DashboardLayout dashboardLayout = (DashboardLayout) findViewById(R.id.dashboard);
        dashboardLayout.setIndexNum(0);
    }
}
