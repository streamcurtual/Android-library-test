package com.parobot.pluginlib.plugin;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class PluginManager {

    private static final PluginManager instance = new PluginManager();
    String TAG = getClass().getSimpleName();
    private Context context;

    public PluginAPK getApk() {
        return apk;
    }

    private PluginAPK apk;

    public static PluginManager getInstance(){
        return instance;
    }

    public void init(Context context){
        this.context = context.getApplicationContext();
    }

    public void loadApk(String path){
        PackageInfo packageInfo = context.getPackageManager().getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES |
                PackageManager.GET_SERVICES);
        if(packageInfo == null){
            Log.d(TAG, "loadApk: failed ");
            return;
        }
        Log.d(TAG, "loadApk: "+packageInfo.toString());
        DexClassLoader classLoader = createDexClassLoader(path);
        AssetManager assetManager = createAssetManager(path);
        Resources resources = createResources(assetManager);
        apk = new PluginAPK(packageInfo,resources,classLoader);
    }

    private DexClassLoader createDexClassLoader(String path) {
        Log.i(TAG, "loadApk: path = "+path);
        File file = context.getDir("aa.apk",Context.MODE_PRIVATE);
        return new DexClassLoader(path,file.getAbsolutePath(),null,context.getClassLoader());
    }

    private AssetManager createAssetManager(String path) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath",String.class);
            method.invoke(assetManager,path);
            return assetManager;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Resources createResources(AssetManager assetManager) {
        Resources res = context.getResources();
        return new Resources(assetManager,res.getDisplayMetrics(),res.getConfiguration());
    }
}
