package com.sunbotu.androidmouse.activities;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.sunbotu.androidmouse.R;

public class SensorsTestActivity extends Activity {

    Sensor sensorGyroscope;
    Sensor sensorRawGyroscope;
    Sensor sensorAccelerometer;
    Sensor sensorMagneticField;
    Sensor sensorRawMagneticField;
    Sensor sensorOrientation;
    Sensor sensorRotation;
    Sensor sensorGameRotation;
    private float x, y, z;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors_test);
        // 从系统服务中获得传感器管理器
        SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // 准备显示信息的UI组建
        final TextView gyroscope = (TextView) findViewById(R.id.gyroscope);
        final TextView rawGyroscope = (TextView) findViewById(R.id.rawGyroscope);
        final TextView accelerometer = (TextView) findViewById(R.id.accelerometer);
        final TextView magneticField = (TextView) findViewById(R.id.magneticField);
        final TextView rawMagneticField = (TextView) findViewById(R.id.rawMagneticField);
        final TextView orientation = (TextView) findViewById(R.id.orientation);
        final TextView rotation = (TextView) findViewById(R.id.rotation);
        final TextView gameRotation = (TextView) findViewById(R.id.gameRotation);

        sensorGyroscope = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorRawGyroscope = sm
                .getDefaultSensor(Sensor.TYPE_GYROSCOPE_UNCALIBRATED);
        sensorAccelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorMagneticField = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sensorRawMagneticField = sm
                .getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED);
        sensorOrientation = sm.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        sensorRotation = sm.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        sensorGameRotation = sm
                .getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR);

        SensorEventListener lsnGyroscope = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent e) {
                x = e.values[SensorManager.DATA_X];
                y = e.values[SensorManager.DATA_Y];
                z = e.values[SensorManager.DATA_Z];
                gyroscope.setText("Gyroscope \n x="
                        + String.format("%1$5.2f", x) + "," + "y="
                        + String.format("%1$5.2f", y) + "," + "z="
                        + String.format("%1$5.2f", z));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        SensorEventListener lsnRawGyroscope = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent e) {
                x = e.values[SensorManager.DATA_X];
                y = e.values[SensorManager.DATA_Y];
                z = e.values[SensorManager.DATA_Z];
                rawGyroscope.setText("RawGyroscope \n x="
                        + String.format("%1$5.2f", x) + "," + "y="
                        + String.format("%1$5.2f", y) + "," + "z="
                        + String.format("%1$5.2f", z));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        SensorEventListener lsnAccelerometer = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent e) {
                x = e.values[SensorManager.DATA_X];
                y = e.values[SensorManager.DATA_Y];
                z = e.values[SensorManager.DATA_Z];
                accelerometer.setText("Accelerometer \n x="
                        + String.format("%1$5.2f", x) + "," + "y="
                        + String.format("%1$5.2f", y) + "," + "z="
                        + String.format("%1$5.2f", z));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        SensorEventListener lsnMagneticField = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent e) {
                x = e.values[SensorManager.DATA_X];
                y = e.values[SensorManager.DATA_Y];
                z = e.values[SensorManager.DATA_Z];
                magneticField.setText("MagneticField \n x="
                        + String.format("%1$5.2f", x) + "," + "y="
                        + String.format("%1$5.2f", y) + "," + "z="
                        + String.format("%1$5.2f", z));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        SensorEventListener lsnRawMagneticField = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent e) {
                x = e.values[SensorManager.DATA_X];
                y = e.values[SensorManager.DATA_Y];
                z = e.values[SensorManager.DATA_Z];
                rawMagneticField.setText("RawMagneticField \n x="
                        + String.format("%1$5.2f", x) + "," + "y="
                        + String.format("%1$5.2f", y) + "," + "z="
                        + String.format("%1$5.2f", z));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        SensorEventListener lsnOrientation = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent e) {
                x = e.values[SensorManager.DATA_X];
                y = e.values[SensorManager.DATA_Y];
                z = e.values[SensorManager.DATA_Z];
                orientation.setText("Orientation \n x="
                        + String.format("%1$5.2f", x) + "," + "y="
                        + String.format("%1$5.2f", y) + "," + "z="
                        + String.format("%1$5.2f", z));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        SensorEventListener lsnRotation = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent e) {
                x = e.values[SensorManager.DATA_X];
                y = e.values[SensorManager.DATA_Y];
                z = e.values[SensorManager.DATA_Z];
                rotation.setText("Rotation \n x=" + String.format("%1$5.2f", x)
                        + "," + "y=" + String.format("%1$5.2f", y) + "," + "z="
                        + String.format("%1$5.2f", z));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        SensorEventListener lsnGameRotation = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent e) {
                x = e.values[SensorManager.DATA_X];
                y = e.values[SensorManager.DATA_Y];
                z = e.values[SensorManager.DATA_Z];
                gameRotation.setText("GameRotation \n x="
                        + String.format("%1$5.2f", x) + "," + "y="
                        + String.format("%1$5.2f", y) + "," + "z="
                        + String.format("%1$5.2f", z));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        sm.registerListener(lsnGyroscope, sensorGyroscope, 10);
        sm.registerListener(lsnRawGyroscope, sensorRawGyroscope, 10);
        sm.registerListener(lsnAccelerometer, sensorAccelerometer, 10);
        sm.registerListener(lsnMagneticField, sensorMagneticField, 10);
        sm.registerListener(lsnRawMagneticField, sensorRawMagneticField, 10);
        sm.registerListener(lsnOrientation, sensorOrientation, 10);
        sm.registerListener(lsnRotation, sensorRotation, 10);
        sm.registerListener(lsnGameRotation, sensorGameRotation, 10);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sensors_test, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

}
