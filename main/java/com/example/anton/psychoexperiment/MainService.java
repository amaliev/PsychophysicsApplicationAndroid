package com.example.anton.psychoexperiment;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;

import com.elirex.fayeclient.FayeClient;
import com.elirex.fayeclient.FayeClientListener;
import com.elirex.fayeclient.MetaMessage;

public class MainService extends Service {

    private static final String LOG_TAG = MainService.class.getSimpleName();
    FayeClient mClient;

    @Override
    public void onCreate() {

        MetaMessage meta = new MetaMessage();
        SharedPreferences connection = getSharedPreferences("connection",0);
        String serverip = connection.getString("serverip","");
        int port = connection.getInt("port",0);
        String destination = "ws://"+serverip+":"+port+"/faye";
        mClient = new FayeClient(destination, meta, getApplicationContext());

        mClient.setListener(new FayeClientListener() {
            @Override
            public void onConnectedServer(FayeClient fc) {
                Log.i(LOG_TAG, "Connected");
                fc.subscribeChannel("/psycho");
            }

            @Override
            public void onDisconnectedServer(FayeClient fc) {
                Log.i(LOG_TAG, "Disconnected");
            }

            @Override
            public void onReceivedMessage(FayeClient fc, String msg) {
                msg.toLowerCase();
                if (msg.contains("start")){
                    Intent dialogIntent = new Intent(MainService.this, LoadScreen.class);
                    dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(dialogIntent);
                }
                if (msg.contains("switch")){
                    Intent dialogIntent = new Intent(MainService.this, RankScreen.class);
                    dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    dialogIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(dialogIntent);
                }
                Log.e(LOG_TAG, "Message: " + msg);
            }
        });

        mClient.connectServer();
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mClient.isConnectedServer()&&intent.hasExtra("message")) {
            if (intent.hasExtra("message")){
                String message = intent.getStringExtra("message");
                mClient.publish("/psycho",mClient.getClientId()+":"+message);
            }
        } else {
            this.onCreate();
        }
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public void onDestroy(){
        if (mClient.isConnectedServer()){
            mClient.disconnectServer();
        }
    }
}