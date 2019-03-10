package com.cps.spinner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onGyroscopeSelect(View view) {
        Intent intent = new Intent(this, Gyroscope.class);
        startActivity(intent);
    }

    public void onGravitySelect(View view) {
        Intent intent = new Intent(this, Gravity.class);
        startActivity(intent);
    }
}