package com.sunbotu.androidmouse.activities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sunbotu.androidmouse.R;
import com.sunbotu.androidmouse.utils.RemoteController;
import com.sunbotu.androidmouse.utils.SocketConnector;

import java.util.concurrent.ExecutionException;

public class ControllerActivity extends Activity {
    private static final String PREF_SENSIBILITY = "sensibility";

    private TextView connectionStatusTextView;
    private TextView statusTextView;
    private SeekBar sensibilitySeekBar;

    // The cursor control is enabled as long as this button is pressed.
    private Button cursorBtnPress;
    // The cursor control is enabled when this button is clicked (not pressed).
    private Button cursorBtnToggle;
    // Click buttons.
    private Button clickLeft;
    private Button clickRight;
    private Button clickMiddle;

    private boolean toggleEnabled = true;
    private boolean controlling = false;

    private RemoteController controller;
    private String ip, port;

    private SocketConnector connector;

    private SharedPreferences preferences;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_remote_controller);

        this.ip = getIntent().getStringExtra("ip");
        this.port = getIntent().getStringExtra("port");

        setupConnection();
        getViews();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        controller = new RemoteController(
                (SensorManager) this.getSystemService(Context.SENSOR_SERVICE),
                connector);
        setSeekBarListeners();
        setButtonListeners();
    }

    private void setupConnection() {
        connector = new SocketConnector(this.ip, this.port);
        // Connect in background.
        connector.execute();
        try {
            if (!connector.get()) {
                CharSequence text = "Unable to connect to remote server! Please retry.";
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
                this.finish();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.i("Mainflow", "Socket connected...");
    }

    private void getViews() {
        connectionStatusTextView = (TextView) findViewById(R.id.connection_status);
        connectionStatusTextView.setText("Connected to " + ip + ":" + port);
        statusTextView = (TextView) findViewById(R.id.mouse_control_status);
        statusTextView.setText("Not controlling");
        sensibilitySeekBar = (SeekBar) findViewById(R.id.seekBar_sensibility);
        cursorBtnPress = (Button) findViewById(R.id.mouse_control_enable);
        cursorBtnToggle = (Button) findViewById(R.id.mouse_control_enable_toggle);
        clickLeft = (Button) findViewById(R.id.btn_click_left);
        clickRight = (Button) findViewById(R.id.btn_click_right);
        // clickMiddle = (Button) findViewById(R.id.btn_click_middle);
    }

    private void setSeekBarListeners() {
        int progress = preferences.getInt(PREF_SENSIBILITY, RemoteController.INIT_SENSIBILITY);
        sensibilitySeekBar.setProgress(progress);
        controller.setSensibility(progress);
        sensibilitySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progress = progressValue;
                controller.setSensibility(progress);
                // Save the progress.
                preferences.edit()
                        .putInt(PREF_SENSIBILITY, progress)
                        .commit();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }

    private void setButtonListeners() {
        // ToggleButton setup.
        cursorBtnToggle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!toggleEnabled || event.getAction() == MotionEvent.ACTION_CANCEL) {
                    return false;
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (controlling) {
                        controller.stopCursorControl();
                        controlling = false;
                        statusTextView.setText("Not controlling");
                    } else {
                        controller.startCursorControl();
                        controlling = true;
                        statusTextView.setText("Controlling");
                    }
                }
                return false;
            }
        });

        // PressButton setup.
        cursorBtnPress.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        controller.startCursorControl();
                        controlling = true;
                        toggleEnabled = false;
                        statusTextView.setText("Controlling");
                        break;
                    case MotionEvent.ACTION_UP:
                        controller.stopCursorControl();
                        controlling = false;
                        toggleEnabled = true;
                        statusTextView.setText("Not controlling");
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });

        // ClickLeft setup.
        clickLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        controller.clickLeftDown();
                        break;
                    case MotionEvent.ACTION_UP:
                        controller.clickLeftUp();
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });

        // ClickRight setup.
        clickRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        controller.clickRightDown();
                        break;
                    case MotionEvent.ACTION_UP:
                        controller.clickRightUp();
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        controller.stopCursorControl();
        this.finish();
    }
}

