package com.test.testmvvm.receiver;

import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Darclass extends DeviceAdminReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

    }
    @Override
    public DevicePolicyManager getManager(Context context) {
        Log.i("XiaoMaGuo", "调用了getManager()方法");
        return super.getManager(context);
    }

    @Override
    public ComponentName getWho(Context context) {
        Log.i("XiaoMaGuo", "调用了getWho()方法");
        return super.getWho(context);
    }

}
