package com.parobot.pluginlib.plugin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public interface IPlugin {
    int FROM_INTERNAL = 0;
    int FROM_EXTERNAL = 1;

    void attachActivity(Activity proxyActivity);

    void onCreate(Bundle saveInstanceState);

    void onStart();

    void onRestart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void onActivityResult(int requestCode, int resultCode, Intent data);


}
