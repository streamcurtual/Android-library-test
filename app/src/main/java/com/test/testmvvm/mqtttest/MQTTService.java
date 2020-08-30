package com.test.testmvvm.mqtttest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;


import com.test.testmvvm.R;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MQTTService extends Service {

    public static final String TAG = MQTTService.class.getSimpleName();
    private static MqttAndroidClient client;
    private MqttConnectOptions mco;
    private String host = "tcp://192.168.0.11:61613";
    private String userName = "admin";
    private String passWord = "password";
    private static String myTopic = "ForTest";      //要订阅的主题
    private String clientId = "androidId" + System.currentTimeMillis();//客户端标识
    private com.test.testmvvm.mqtttest.IGetMessageCallBack IGetMessageCallBack;
//     MQTT监听并且接受消息
    private MqttCallback mqttCallback = new MqttCallbackExtended() {
        @Override
        public void connectComplete(boolean reconnect, String serverURI) {
            Log.d("===", "连接完成");
        }

        @Override
        public void connectionLost(Throwable cause) {
            Log.d("===", "失去连接");

        }

        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {
            Log.d("===", "收到消息：" + topic + " message: " + message);
            if (IGetMessageCallBack != null){
                String str1 = new String(message.getPayload());
                IGetMessageCallBack.getMessage(str1);
                String str2 = topic + ";qos:" + message.getQos() + ";retained:" + message.isRetained();
                Log.i(TAG, "messageArrived:" + str1);
                Log.i(TAG, str2);
            }
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken token) {
            Log.d("===", "分发消息成功");
        }
    };

    // MQTT是否连接成功
    private IMqttActionListener mqttActionListener = new IMqttActionListener() {
        @Override
        public void onSuccess(IMqttToken asyncActionToken) {
            Log.d("===", "连接成功");
            try {
                client.subscribe(myTopic,1);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
            Log.d(TAG, "onFailure: 连接失败");
            Log.d(TAG, "onFailure: " + exception.getMessage());
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");
        return new MyBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        client = new MqttAndroidClient(this, host, clientId);
        client.setCallback(mqttCallback);
        mco = new MqttConnectOptions();
        mco.setCleanSession(true);
        mco.setConnectionTimeout(10);
        mco.setKeepAliveInterval(20);
        mco.setUserName(userName);
        mco.setPassword(passWord.toCharArray());
        boolean doConnect = true;
        String message = "{\"terminal_uid\":\"" + clientId + "\"}";
        Log.e(getClass().getName(), "message是:" + message);
        String topic = myTopic;
        int qos = 0;
        if ((!message.equals("")) || (!topic.equals(""))) {
            // 最后的遗嘱
            // MQTT本身就是为信号不稳定的网络设计的，所以难免一些客户端会无故的和Broker断开连接。
            //当客户端连接到Broker时，可以指定LWT，Broker会定期检测客户端是否有异常。
            //当客户端异常掉线时，Broker就往连接时指定的topic里推送当时指定的LWT消息。

            try {
                mco.setWill(topic, message.getBytes(), qos, false);
            } catch (Exception e) {
                Log.i(TAG, "Exception Occured", e);
                doConnect = false;
                mqttActionListener.onFailure(null, e);
            }
        }

        if (doConnect) {
            doClientConnection();
        }
    }

    /** 连接MQTT服务器 */
    private void doClientConnection() {
        if (!client.isConnected() && isConnectIsNormal()) {
            try {
                client.connect(mco, null, mqttActionListener);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }

    }

    class MyBinder extends Binder{
        MQTTService getService(){
            return MQTTService.this;
        }
    }

    /**
     * 判断网络是否连接
     */
    private boolean isConnectIsNormal() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            String name = info.getTypeName();
            Log.i(TAG, "MQTT当前网络名称：" + name);
            return true;
        } else {
            Log.i(TAG, "MQTT 没有可用网络");
            return false;
        }
    }

    public  void toCreateNotification(String message){
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, new Intent(this,MQTTService.class), PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);//3、创建一个通知，属性太多，使用构造器模式

        Notification notification = builder
                .setTicker("测试标题")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("")
                .setContentText(message)
                .setContentInfo("")
                .setContentIntent(pendingIntent)//点击后才触发的意图，“挂起的”意图
                .setAutoCancel(true)        //设置点击之后notification消失
                .build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        startForeground(0, notification);
        notificationManager.notify(0, notification);

    }

    public static void publish(String msg){
        String topic = myTopic;
        Integer qos = 0;
        Boolean retained = false;
        try {
            if (client != null){
                client.publish(topic, msg.getBytes(), qos.intValue(), retained.booleanValue());
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
