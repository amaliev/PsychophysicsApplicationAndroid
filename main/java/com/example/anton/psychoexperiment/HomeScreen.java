package com.example.anton.psychoexperiment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity {

    AlertDialog destinationbox;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        destinationbox = builder.create();

        SharedPreferences rankings = getSharedPreferences("rankings",0);
        SharedPreferences.Editor editor = rankings.edit();
        editor.clear();
        editor.commit();
    }

    public void connect(View view){
        LayoutInflater inflater = getLayoutInflater();
        final View layout = inflater.inflate(R.layout.destinationbox,null);
        destinationbox = new AlertDialog.Builder(this)
                .setView(layout)
                .setPositiveButton("Submit", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences connection = getSharedPreferences("connection", 0);
                        SharedPreferences.Editor edit = connection.edit();
                        EditText text1 = (EditText) layout.findViewById(R.id.textip);
                        String ip = text1.getText().toString();
                        edit.putString("serverip",ip);
                        EditText text2 = (EditText) layout.findViewById(R.id.textport);
                        String port = text2.getText().toString();
                        try {
                            edit.putInt("port",Integer.parseInt(port));
                        } catch (NumberFormatException e) {
                            edit.putInt("port",0);
                        }
                        edit.putBoolean("connected",false);
                        edit.commit();

                        Intent intent = new Intent(HomeScreen.this, MainService.class);
                        startService(intent);

                        handler = new Handler();
                        Runnable runnable = new Runnable(){
                            @Override
                            public void run(){
                                TextView t = (TextView) findViewById(R.id.server);
                                SharedPreferences connection = getSharedPreferences("connection", 0);
                                if (connection.getBoolean("connected",false)){
                                    t.setText("Server: connected");
                                } else {
                                    t.setText("Server: none");
                                }
                            }
                        };
                        handler.postDelayed(runnable,1000);
                    }

                })
                .show();
    }

    public void toabout(View view){
        Intent int2 = new Intent(this, AboutScreen.class);
        startActivity(int2);
    }

    @Override
    public void onStop() {
        if (destinationbox.isShowing()) {
            destinationbox.dismiss();
        }
        super.onStop();
    }

    @Override
    public void onDestroy(){
        Intent intent = new Intent(this,MainService.class);
        stopService(intent);
        super.onDestroy();
    }
}
