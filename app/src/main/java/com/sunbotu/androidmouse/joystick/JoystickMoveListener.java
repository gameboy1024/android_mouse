package com.sunbotu.androidmouse.joystick;

/**
 * Created by sbt on 1/16/15.
 */
public interface JoystickMoveListener {
    public void OnMoved(int pan, int tilt);

    public void OnReleased();

    public void OnReturnedToCenter();
}
