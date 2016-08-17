package com.example.anton.psychoexperiment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class RankScreen extends AppCompatActivity implements View.OnClickListener {

    int orange = Color.parseColor("#FF8000");
    int skyblue = Color.parseColor("#87CEFF");
    int select = 0;
    AlertDialog closebox;
    AlertDialog helpbox;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        closebox = builder.create();
        helpbox = builder.create();

        TextView timer = (TextView) findViewById(R.id.timer);
        Button button_1 = (Button) findViewById(R.id.button1);
        Button button_2 = (Button) findViewById(R.id.button2);
        Button button_3 = (Button) findViewById(R.id.button3);
        Button button_4 = (Button) findViewById(R.id.button4);

        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);

        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(1000);

        handler = new Handler();

        Runnable runnable = new Runnable() {

            long millis = 20000;

            @Override
            public void run() {
                TextView timer = (TextView) findViewById(R.id.timer);
                timer.setText(Long.toString(millis / 1000));
                millis-= 1000;
                handler.postDelayed(this,1000);
            }
        };

        Runnable runnable2 = new Runnable() {

            @Override
            public void run() {
                SharedPreferences rankings = getSharedPreferences("rankings", 0);
                SharedPreferences.Editor edit = rankings.edit();
                int vidnum = rankings.getInt("vidnum",0);
                vidnum++;
                edit.putInt("vidnum",vidnum);
                edit.putInt(Integer.toString(vidnum),select);
                Intent intent = new Intent (RankScreen.this,MainService.class);
                intent.putExtra("message",Integer.toString(select));
                startService(intent);
                edit.commit();
                if (vidnum<30) {
                    Intent intent2 = new Intent(RankScreen.this,LoadScreen.class);
                    startActivity(intent2);
                } else {
                    Intent intent3 = new Intent(RankScreen.this,FinalScreen.class);
                    startActivity(intent3);
                }
            }
        };
        handler.post(runnable);
        handler.postDelayed(runnable2,20000);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_rank_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.help) {
            helpbox = new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_menu_help)
                    .setTitle("Ranking System")
                    .setMessage("1. Unacceptable\nCannot watch anymore / Cannot bear to sit through this quality\n\n2: Satisfactory\nCan sit through the video at this quality, but it might be annoying\n\n3: Good\nCan definitely sit through the video, but the quality does detract a bit of enjoyment\n\n4. Excellent\nNo complaints")
                    .show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        switch (v.getId()){
            case R.id.button1:
                button1.setBackgroundColor(orange);
                button2.setBackgroundColor(skyblue);
                button3.setBackgroundColor(skyblue);
                button4.setBackgroundColor(skyblue);
                select = 1;
                break;
            case R.id.button2:

                button2.setBackgroundColor(orange);
                button1.setBackgroundColor(skyblue);
                button3.setBackgroundColor(skyblue);
                button4.setBackgroundColor(skyblue);
                select = 2;
                break;
            case R.id.button3:
                button3.setBackgroundColor(orange);
                button1.setBackgroundColor(skyblue);
                button2.setBackgroundColor(skyblue);
                button4.setBackgroundColor(skyblue);
                select = 3;
                break;
            case R.id.button4:
                button4.setBackgroundColor(orange);
                button1.setBackgroundColor(skyblue);
                button2.setBackgroundColor(skyblue);
                button3.setBackgroundColor(skyblue);
                select = 4;
                break;
        }
    }

    @Override
    public void onDestroy(){
        handler.removeCallbacksAndMessages(null);
        if (helpbox.isShowing()) {
            helpbox.dismiss();
        }
        if (closebox.isShowing()) {
            closebox.dismiss();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        closebox = new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Quit the Experiment")
                .setMessage("Are you sure you want to quit the experiment? This cannot be undone!")
                .setPositiveButton("Quit", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        Intent back = new Intent(RankScreen.this, HomeScreen.class);
                        startActivity(back);
                    }

                })
                .setNegativeButton("Cancel", null)
                .show();
    }

}
