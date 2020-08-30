package com.parobot.pluginlib.plugin;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

public class ProxyActivity extends Activity {
    private String className;
    private PluginAPK pluginAPK;
    private IPlugin iPlugin;
    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        className = getIntent().getStringExtra("className");
        pluginAPK = PluginManager.getInstance().getApk();
        launcherPluginActivity();
    }

    private void launcherPluginActivity() {
        if(pluginAPK == null){
            Log.i(TAG, "launcherPluginActivity: 读取apk失败");
            return;
        }
        try {
            Class<?> clz = pluginAPK.dexClassLoader.loadClass(className);
            Object object = clz.newInstance();
            if (object instanceof IPlugin){
                iPlugin = (IPlugin) object;
                iPlugin.attachActivity(this);
                Bundle bundle = new Bundle();
                bundle.putInt("FROM",IPlugin.FROM_EXTERNAL);
                iPlugin.onCreate(bundle);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Resources getResources() {
        return pluginAPK != null ? pluginAPK.resources:super.getResources();
    }

    @Override
    public AssetManager getAssets() {
        return pluginAPK != null ? pluginAPK.assetManager:super.getAssets();
    }

    @Override
    public ClassLoader getClassLoader() {
        return pluginAPK != null ? pluginAPK.dexClassLoader:super.getClassLoader();
    }
}
