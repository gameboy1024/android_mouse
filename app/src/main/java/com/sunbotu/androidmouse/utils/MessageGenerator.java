package com.sunbotu.androidmouse.utils;

/**
 * Created by sbt on 11/6/14.
 */
public class MessageGenerator {

    final static String MOUSE_LEFT_BTN_DOWN = "LD";
    final static String MOUSE_LEFT_BTN_UP = "LU";
    final static String MOUSE_RIGHT_BTN_DOWN = "RD";
    final static String MOUSE_RIGHT_BTN_UP = "RU";
    final static String MOUSE_MIDDLE_BTN_DOWN = "MD";
    final static String MOUSE_MIDDLE_BTN_UP = "MU";
    final static String LOCATION = "XY";

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

    public static String location(float x, float y) {
        return LOCATION + SEPARATOR + Float.valueOf(x) + SEPARATOR + Float.valueOf(y);
    }
}
