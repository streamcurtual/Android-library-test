package com.parobot.pluginlib.plugin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class PluginActivity extends Activity implements IPlugin{
    private Activity mProxyActivity;
    private int mfrom = FROM_INTERNAL;
    String TAG = getClass().getSimpleName();
    @Override
    public void attachActivity(Activity proxyActivity) {
        mProxyActivity = proxyActivity;
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        if (saveInstanceState != null){
            mfrom = saveInstanceState.getInt("FROM");
        }
        if(mfrom == FROM_INTERNAL){
            super.onCreate(saveInstanceState);
        }
    }

    @Override
    public void onStart() {
        if(mfrom == FROM_INTERNAL){
            super.onStart();
        }
    }

    @Override
    public void onRestart() {
        if(mfrom == FROM_INTERNAL){
            super.onRestart();
        }
    }

    @Override
    public void onResume() {
        if(mfrom == FROM_INTERNAL){
            super.onResume();
        }
    }

    @Override
    public void onPause() {
        if(mfrom == FROM_INTERNAL){
            super.onPause();
        }
    }

    @Override
    public void onStop() {
        if(mfrom == FROM_INTERNAL){
            super.onStop();
        }
    }

    @Override
    public void onDestroy() {
        if(mfrom == FROM_INTERNAL){
            super.onDestroy();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(mfrom == FROM_INTERNAL){
            super.onActivityResult( requestCode,  resultCode,  data);
        }
    }

    @Override
    public void setContentView(int resId) {
        Log.i(TAG, "setContentView: "+mfrom);
        if(mfrom == FROM_INTERNAL) {
            super.setContentView(resId);
        }else {
            mProxyActivity.setContentView(resId);
        }
    }
}
