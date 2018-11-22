package com.example.edu.pedometeractivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
TextView textViewGx,textViewGy,textViewGz,textViewSteps;

    float acceleration;
    private SensorManager sensorManager;
    int threshold,previousY,steps,currentY;
    SeekBar seekBarSensitive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBarSensitive=findViewById(R.id.seekBarSensitive);
        threshold = seekBarSensitive.getProgress();
        textViewGx=findViewById(R.id.textViewGx);
        textViewGy=findViewById(R.id.textViewGy);
        textViewGz=findViewById(R.id.textViewGz);
        textViewSteps=findViewById(R.id.textViewSteps);
        threshold=3;
        previousY = currentY = steps = 0;
        acceleration = 0;
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(
                Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        currentY = (int) y;
        if(Math.abs(currentY - previousY) > threshold){
            steps++;
            textViewSteps.setText(String.valueOf(steps));
        }
        textViewGx.setText(String.valueOf(x));
        textViewGy.setText(String.valueOf(x));
        textViewGz.setText(String.valueOf(x));
        previousY = (int) y;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
