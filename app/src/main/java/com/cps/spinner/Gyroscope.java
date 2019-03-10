package com.cps.spinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Gyroscope extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);

        ImageView ball = findViewById(R.id.ball);
        ball.setY(100);
        ball.setX(200);
    }
}
