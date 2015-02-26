package com.sunbotu.androidmouse.utils;

/**
 * Created by sbt on 11/6/14.
 */
public class MessageGenerator {
    // Mouse control
    final static String MOUSE_LEFT_BTN_DOWN = "LD";
    final static String MOUSE_LEFT_BTN_UP = "LU";
    final static String MOUSE_RIGHT_BTN_DOWN = "RD";
    final static String MOUSE_RIGHT_BTN_UP = "RU";
    final static String MOUSE_MIDDLE_BTN_DOWN = "MD";
    final static String MOUSE_MIDDLE_BTN_UP = "MU";
    final static String MOUSE_LEFT_CLICK = "LC";
    final static String LOCATION = "XY";
    final static String CURSOR_START = "C1";
    final static String CURSOR_STOP = "C2";
    final static String ROTATE = "RT";
    final static String ROTATE_STOP = "R0";
    // Keyboard control
    final static String MOVE = "MV";

    final static String SEPARATOR = "_";

    public static String mouseLeftBtnDown() {
        return MOUSE_LEFT_BTN_DOWN;
    }

    public static String mouseLeftBtnUp() {
        return MOUSE_LEFT_BTN_UP;
    }

    public static String mouseRightBtnDown() {
        return MOUSE_RIGHT_BTN_DOWN;
    }

    public static String mouseRightBtnUp() {
        return MOUSE_RIGHT_BTN_UP;
    }

    public static String mouseMiddleBtnDown() {
        return MOUSE_MIDDLE_BTN_DOWN;
    }

    public static String mouseMiddleBtnUp() {
        return MOUSE_MIDDLE_BTN_UP;
    }

    public static String mouseLeftClick() {
        return MOUSE_LEFT_CLICK;
    }

    public static String location(float x, float y, float z) {
        return LOCATION + SEPARATOR + String.valueOf(x) + SEPARATOR + String.valueOf(y) + SEPARATOR + String.valueOf(z);
    }

    public static String rotate(float x, float y) {
        return ROTATE + SEPARATOR + String.valueOf(x) + SEPARATOR + String.valueOf(y);
    }

    public static String stopRotating() {
        return ROTATE_STOP;
    }

    public static String startCursorControl() {
        return CURSOR_START;
    }

    public static String stopCursorControl() {
        return CURSOR_STOP;
    }

    public static String move(int x, int y) {
        return MOVE + SEPARATOR + String.valueOf(x) + SEPARATOR + String.valueOf(y);
    }
}
