package com.example.anton.psychoexperiment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;

public class LoadScreen extends AppCompatActivity {

    Handler handler;
    Runnable runnable;
    private ProgressBar spinner;
    AlertDialog closebox;
    boolean done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_screen);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        closebox = builder.create();

        spinner = (ProgressBar)findViewById(R.id.progressBar1);

        done = false;
    }

    @Override
    public void onStop(){
        if (closebox.isShowing()) {
            closebox.dismiss();
        }
        super.onStop();
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
                        Intent back = new Intent(LoadScreen.this, HomeScreen.class);
                        startActivity(back);
                    }

                })
                .setNegativeButton("Cancel", null)
                .show();
    }

}