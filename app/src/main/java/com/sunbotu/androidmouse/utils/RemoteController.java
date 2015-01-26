package com.sunbotu.androidmouse.utils;

import android.hardware.SensorManager;

/**
 * This class is responsible for control modules (like RotationSensor).
 * Created by sbt on 11/6/14.
 */
public class RemoteController {

    public static final int INIT_SENSIBILITY = 50;

    public enum State {IDLE, CONTROLLING}

    ;
    private State state = State.IDLE;
    private RotationSensor rotationSensor;
    private SocketConnector connector;
    private int sensibility;

    public RemoteController(SensorManager sensorManager, SocketConnector connector) {
        this.connector = connector;
        rotationSensor = new RotationSensor(this, sensorManager);
        sensibility = INIT_SENSIBILITY;
    }

    public void setSensibility(int sensibility) {
        this.sensibility = sensibility;
    }

    public void startCursorControl() {
        rotationSensor.resume();
        state = State.CONTROLLING;
    }

    public void stopCursorControl() {
        rotationSensor.stop();
        state = State.IDLE;
    }

    public boolean isControlling() {
        return state == State.CONTROLLING;
    }

    public void clickLeftDown() {
        connector.sendMessage(MessageGenerator.mouseLeftBtnDown());
    }

    public void clickLeftUp() {
        connector.sendMessage(MessageGenerator.mouseLeftBtnUp());
    }

    public void clickRightDown() {
        connector.sendMessage(MessageGenerator.mouseRightBtnDown());
    }

    public void clickRightUp() {
        connector.sendMessage(MessageGenerator.mouseRightBtnUp());
    }

    public void updateLocation(float x, float y, float z) {
        connector.sendMessage(
                MessageGenerator.location(-z * 2 * sensibility, -x * 1.2f * sensibility));
    }

    public void move(int x, int y) {
        connector.sendMessage(MessageGenerator.move(x, y));
    }

}
