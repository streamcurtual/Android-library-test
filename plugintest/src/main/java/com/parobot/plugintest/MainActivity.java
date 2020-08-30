package com.parobot.plugintest;

import android.os.Bundle;
import android.util.Log;

import com.parobot.pluginlib.plugin.PluginActivity;

public class MainActivity extends PluginActivity {
    String TAG = getClass().getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
    }
}
