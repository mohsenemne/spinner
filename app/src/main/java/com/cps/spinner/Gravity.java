package com.cps.spinner;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    private int ballWidth;
    private int ballHeight;

    private int navBarHeight = 0;

    private float ballX = 0;
    private float ballY = 0;

    private float ballVX = 0;
    private float ballVY = 0;

    private float ballAX = 0;
    private float ballAY = 0;

    private float timestamp = 0;

    private Handler handler = new Handler();
    private Timer timer = new Timer();

    private final float dt = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gravity);

        int resourceId = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            navBarHeight = getResources().getDimensionPixelSize(resourceId);
        }

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        ball = findViewById(R.id.ball);

        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        ball.measure(disp.getWidth(), disp.getHeight());
        ballWidth = ball.getMeasuredWidth();
        ballHeight = ball.getMeasuredHeight();

        ball.setX(0);
        ball.setY(0);

        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changePos();
                    }
                });
            }
        },0, (long) dt);
    }


    public void changePos() {
        ballX += (ballAX * dt * dt / 2000000.) + ballVX * dt / 1000.;
        ballY += (ballAY * dt * dt / 2000000.) + ballVY * dt / 1000.;

        ballVX += ballAX * dt / 1000.;
        ballVY += ballAY * dt / 1000.;

        if (ballX < 0) {
            ballX = 0;
            ballVX = 0;
        }
        if(ballY < 0) {
            ballY = 0;
            ballVY = 0;
        }
        if(ballX > screenWidth - ballWidth) {
            ballX = screenWidth - ballWidth;
            ballVX = 0;
        }
        if(ballY > screenHeight - ballHeight - navBarHeight){
            ballY = screenHeight - ballHeight - navBarHeight;
            ballVY = 0;
        }

        Log.d("pos", String.valueOf(ballHeight) + ' ' + String.valueOf(ballWidth));

        ball.setX(ballX);
        ball.setY(ballY);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        ballAX = event.values[0];
        ballAY = event.values[1];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
