package com.test.testmvvm.mqtttest;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;


public class MyServiceConnection implements ServiceConnection {

    private MQTTService mqttService;
    private IGetMessageCallBack IGetMessageCallBack;

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        mqttService = ((MQTTService.MyBinder)service).getService();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    public MQTTService getMqttService(){
        return mqttService;
    }

    public void setIGetMessageCallBack(IGetMessageCallBack IGetMessageCallBack){
        this.IGetMessageCallBack = IGetMessageCallBack;
    }
}
