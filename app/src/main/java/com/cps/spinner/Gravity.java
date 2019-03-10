package com.cps.spinner;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Gravity extends AppCompatActivity implements SensorEventListener{

    private SensorManager sensorManager;
    private Sensor sensor;

    private int screenWidth;
    private int screenHeight;

    private ImageView ball;

    private float ballX = 0;
    private float ballY = 0;

    private float ballVX = 0;
    private float ballVY = 0;

    private float timestamp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gravity);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);

        ball = findViewById(R.id.ball);

        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        ball.setX(0);
        ball.setY(0);

        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float ax = event.values[0];
        float ay = event.values[1];

        float dt = event.timestamp - timestamp;
        timestamp = event.timestamp;

        ballX += (ax * dt * dt / 2.) + ballVX * dt;
        ballY += (ay * dt * dt / 2.) + ballVY * dt;

        ballVX += ax * dt;
        ballVY += ay * dt;

        ball.setX(ballX);
        ball.setY(ballY);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
