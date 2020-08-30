package com.test.testmvvm.mqtttest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.test.testmvvm.R;

public class MainActivity extends AppCompatActivity implements IGetMessageCallBack {

    private TextView textView;
    private Button button;
    private MyServiceConnection serviceConnection;
    private MQTTService mqttService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView =  findViewById(R.id.text);
        button =  findViewById(R.id.button);

        serviceConnection = new MyServiceConnection();
        serviceConnection.setIGetMessageCallBack(MainActivity.this);

        Intent intent = new Intent(this, MQTTService.class);

        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MQTTService.publish("测试一下子");
            }
        });
    }

    @Override
    public void getMessage(String message) {
        textView.setText(message);
        mqttService = serviceConnection.getMqttService();
        mqttService.toCreateNotification(message);
    }

    @Override
    protected void onDestroy() {
        unbindService(serviceConnection);
        super.onDestroy();
    }

}
