package com.sunbotu.androidmouse.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.sunbotu.androidmouse.utils.MouseController;

public class ControlIndicatorActivity extends Activity {
    private float center, centerY;
    private float currentX = 0, currentZ = 0;
    private float height, width;
    private SurfaceView sf;
    private SurfaceHolder sfh; // surfaceView的 控制器
    private MouseController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sf = new SurfaceView(this.getApplicationContext());
        sfh = sf.getHolder();
        sfh.addCallback(new Redraw());
        controller = new MouseController(
                (SensorManager) this.getSystemService(Context.SENSOR_SERVICE), null);
        controller.startCursorControl();
        setContentView(sf);
    }

    public void update(float x, float y, float z) {
        currentX = x;
        currentZ = z;
        // Log.d("Sensor update received", String.valueOf(x) +
        // String.valueOf(z));
    }

    private class Redraw implements SurfaceHolder.Callback {
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {
            // 在surface的大小发生改变时激发
            System.out.println("surfaceChanged");
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            new Thread() {
                public void run() {
                    while (sfh.getSurface().isValid()) {

                        // 1.这里就是核心了， 得到画布 ，然后在你的画布上画出要显示的内容
                        Canvas c = sfh.lockCanvas();
                        c.drawColor(Color.WHITE);
                        // 2.开画
                        Paint p = new Paint();
                        p.setStrokeWidth(8);
                        p.setColor(Color.BLACK);
                        c.drawLine(sfh.getSurfaceFrame().centerX(), sfh
                                        .getSurfaceFrame().centerY(),
                                sfh.getSurfaceFrame().centerX() + -currentZ
                                        * 2000, sfh.getSurfaceFrame().centerY()
                                        + currentX * 2000, p);
                        c.drawText("----z:" + -currentZ * 2000 + " x:" + currentX
                                * 2000, sfh.getSurfaceFrame().centerX() + -currentZ
                                * 2000, sfh.getSurfaceFrame().centerY()
                                + currentX * 2000, p);
                        // c.drawLine(width / 2, height / 2, width / 2 +
                        // -currentZ* 1000, height / 2 + currentX * 1000, p);
                        // 3. 解锁画布 更新提交屏幕显示内容
                        sfh.unlockCanvasAndPost(c);
                        try {
                            Thread.sleep(50);

                        } catch (Exception e) {
                        }
                    }
                }

                ;
            }.start();

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            // 销毁时激发，一般在这里将画图的线程停止、释放。
            System.out.println("surfaceDestroyed==");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        controller.stopCursorControl();
        this.finish();
    }
}

