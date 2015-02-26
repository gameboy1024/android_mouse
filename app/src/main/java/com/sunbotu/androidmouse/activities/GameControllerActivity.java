package com.sunbotu.androidmouse.activities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sunbotu.androidmouse.R;
import com.sunbotu.androidmouse.joystick.JoystickMoveListener;
import com.sunbotu.androidmouse.joystick.JoystickView;
import com.sunbotu.androidmouse.utils.RemoteController;
import com.sunbotu.androidmouse.utils.SocketConnector;

import java.util.concurrent.ExecutionException;

public class GameControllerActivity extends Activity {
    private static final String PREF_SENSIBILITY = "sensibility_3d_game";

    private TextView connectionStatusTextView;
    private TextView statusTextView;
    private SeekBar sensibilitySeekBar;

    private TextView txtX, txtY;
    private JoystickView joystick_move;

    private RemoteController controller;
    private String ip, port;

    private SocketConnector connector;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game_controller);

        this.ip = getIntent().getStringExtra("ip");
        this.port = getIntent().getStringExtra("port");

        setupConnection();
        getViews();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        controller = new RemoteController(
                (SensorManager) this.getSystemService(Context.SENSOR_SERVICE),
                connector);
        setSeekBarListeners();
        joystick_move.setOnJoystickMovedListener(getMoveMoveListener());
    }

    private void getViews() {
        connectionStatusTextView = (TextView) findViewById(R.id.connection_status);
        connectionStatusTextView.setText("Connected to " + ip + ":" + port);
        statusTextView = (TextView) findViewById(R.id.control_status);
        statusTextView.setText("Not controlling");
        sensibilitySeekBar = (SeekBar) findViewById(R.id.seekBar_sensibility);
//        txtX = (TextView) findViewById(R.id.text_x);
//        txtY = (TextView) findViewById(R.id.text_y);
        joystick_move = (JoystickView) findViewById(R.id.view_joystick_move);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_controller_3d, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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

    private JoystickMoveListener getMoveMoveListener() {
        return new JoystickMoveListener() {
            @Override
            public void OnMoved(int x, int y) {
                if (!controller.isControlling()) {
                    controller.startCursorControl();
                }
                controller.move(x, -y);
                statusTextView.setText("Controlling");
            }

            @Override
            public void OnReleased() {
            }

            @Override
            public void OnReturnedToCenter() {
                controller.stopCursorControl();
                statusTextView.setText("Not controlling");
            }
        };
    }

    public void sendAction(View view) {
        controller.clickLeft();
    }
}
