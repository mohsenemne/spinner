package com.cps.spinner;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import static android.util.Half.EPSILON;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

public class Gyroscope extends AppCompatActivity implements SensorEventListener{

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
        setContentView(R.layout.activity_gyroscope);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);

        ball = findViewById(R.id.ball);

        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        ball.setY(100);
        ball.setX(200);

        sensorManager.registerListener( this, sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    private static final float NS2S = 1.0f / 1000000000.0f;
    private final float[] deltaRotationVector = new float[4];



    @Override
    public void onSensorChanged(SensorEvent event) {
        // This timestep's delta rotation to be multiplied by the current rotation
        // after computing it from the gyro sample data.

        timestamp = event.timestamp - timestamp;
        float deltaRotationOfX = event.values[0];
        float deltaRotationOfY = event.values[1];







    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
