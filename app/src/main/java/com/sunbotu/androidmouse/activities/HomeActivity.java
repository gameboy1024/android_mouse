package com.sunbotu.androidmouse.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.sunbotu.androidmouse.R;


public class HomeActivity extends Activity {

    private TextView serverIpTextView;
    private TextView serverPortTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        serverIpTextView = (TextView) findViewById(R.id.ip);
        serverPortTextView = (TextView) findViewById(R.id.port);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void OpenSensorTestActivity(View view) {
        Intent intent = new Intent(this, SensorsTestActivity.class);
        startActivity(intent);
    }

    public void OpenGameRotationSensorTestActivity(View view) {
        Intent intent = new Intent(this, ControlIndicatorActivity.class);
        startActivity(intent);
    }

    public void OpenMouseControllerActivity(View view) {
        Intent intent = new Intent(this, ControllerActivity.class);
        // TODO: valid text before invoking...
        intent.putExtra("ip", serverIpTextView.getText().toString());
        intent.putExtra("port", serverPortTextView.getText().toString());
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
