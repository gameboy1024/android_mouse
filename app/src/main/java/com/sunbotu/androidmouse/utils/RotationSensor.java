package com.sunbotu.androidmouse.utils;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * This is a wrapper class that manage gyroscope sensor data.
 * Created by Botu Sun on 11/5/14.
 */
public class RotationSensor {
    private float x0, y0, z0;
    private boolean resetAnchor;
    private float x, y, z;
    private MouseController controller;
    private SensorManager sensorManager;
    private Sensor sensorGameRotation;
    private SensorEventListener lsnGameRotation;


    public RotationSensor(MouseController controller, SensorManager sensorManager) {
        this.controller = controller;
        this.sensorManager = sensorManager;
        resetAnchor = true;
        initiateListener();
    }

    private void initiateListener() {
        sensorGameRotation = sensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR);
        lsnGameRotation = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent e) {
                if (resetAnchor) {
                    x0 = e.values[SensorManager.DATA_X];
                    y0 = e.values[SensorManager.DATA_Y];
                    z0 = e.values[SensorManager.DATA_Z];
                    resetAnchor = false;
                }
                x = e.values[SensorManager.DATA_X];
                y = e.values[SensorManager.DATA_Y];
                z = e.values[SensorManager.DATA_Z];
                controller.updateLocation(x - x0, y - y0, z - z0);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
    }

    public void pause(){
        stop();
    }

    public void resume() {
        resetAnchor = true;
        sensorManager.registerListener(lsnGameRotation, sensorGameRotation,
                SensorManager.SENSOR_DELAY_GAME);
    }

    public void stop() {
        sensorManager.unregisterListener(lsnGameRotation, sensorGameRotation);
    }
}
