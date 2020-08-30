package com.parobot.pluginlib.plugin;

import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

public class PluginAPK {
    PackageInfo packageInfo;
    Resources resources;
    AssetManager assetManager;
    DexClassLoader dexClassLoader;

    public PluginAPK(PackageInfo packageInfo, Resources resources, DexClassLoader dexClassLoader) {
        this.packageInfo = packageInfo;
        this.resources = resources;
        this.dexClassLoader = dexClassLoader;
        assetManager = resources.getAssets();
    }
}
