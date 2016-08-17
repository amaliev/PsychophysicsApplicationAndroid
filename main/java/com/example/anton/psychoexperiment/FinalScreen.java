package com.example.anton.psychoexperiment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class FinalScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_screen);

        Intent intent = new Intent(this,MainService.class);
        stopService(intent);

        TextView blank1 = (TextView) findViewById(R.id.slot1);
        TextView blank2 = (TextView) findViewById(R.id.slot2);
        TextView blank3 = (TextView) findViewById(R.id.slot3);
        TextView blank4 = (TextView) findViewById(R.id.slot4);
        TextView blank5 = (TextView) findViewById(R.id.slot5);
        TextView blank6 = (TextView) findViewById(R.id.slot6);
        TextView blank7 = (TextView) findViewById(R.id.slot7);
        TextView blank8 = (TextView) findViewById(R.id.slot8);
        TextView blank9 = (TextView) findViewById(R.id.slot9);
        TextView blank10 = (TextView) findViewById(R.id.slot10);
        TextView blank11 = (TextView) findViewById(R.id.slot11);
        TextView blank12 = (TextView) findViewById(R.id.slot12);
        TextView blank13 = (TextView) findViewById(R.id.slot13);
        TextView blank14 = (TextView) findViewById(R.id.slot14);
        TextView blank15 = (TextView) findViewById(R.id.slot15);
        TextView blank16 = (TextView) findViewById(R.id.slot16);
        TextView blank17 = (TextView) findViewById(R.id.slot17);
        TextView blank18 = (TextView) findViewById(R.id.slot18);
        TextView blank19 = (TextView) findViewById(R.id.slot19);
        TextView blank20 = (TextView) findViewById(R.id.slot20);
        TextView blank21 = (TextView) findViewById(R.id.slot21);
        TextView blank22 = (TextView) findViewById(R.id.slot22);
        TextView blank23 = (TextView) findViewById(R.id.slot23);
        TextView blank24 = (TextView) findViewById(R.id.slot24);
        TextView blank25 = (TextView) findViewById(R.id.slot25);
        TextView blank26 = (TextView) findViewById(R.id.slot26);
        TextView blank27 = (TextView) findViewById(R.id.slot27);
        TextView blank28 = (TextView) findViewById(R.id.slot28);
        TextView blank29 = (TextView) findViewById(R.id.slot29);
        TextView blank30 = (TextView) findViewById(R.id.slot30);
        SharedPreferences rankings = getSharedPreferences("rankings",0);
        blank1.setText(Integer.toString(rankings.getInt("1",0)));
        blank2.setText(Integer.toString(rankings.getInt("2",0)));
        blank3.setText(Integer.toString(rankings.getInt("3",0)));
        blank4.setText(Integer.toString(rankings.getInt("4",0)));
        blank5.setText(Integer.toString(rankings.getInt("5",0)));
        blank6.setText(Integer.toString(rankings.getInt("6",0)));
        blank7.setText(Integer.toString(rankings.getInt("7",0)));
        blank8.setText(Integer.toString(rankings.getInt("8",0)));
        blank9.setText(Integer.toString(rankings.getInt("9",0)));
        blank10.setText(Integer.toString(rankings.getInt("10",0)));
        blank11.setText(Integer.toString(rankings.getInt("11",0)));
        blank12.setText(Integer.toString(rankings.getInt("12",0)));
        blank13.setText(Integer.toString(rankings.getInt("13",0)));
        blank14.setText(Integer.toString(rankings.getInt("14",0)));
        blank15.setText(Integer.toString(rankings.getInt("15",0)));
        blank16.setText(Integer.toString(rankings.getInt("16",0)));
        blank17.setText(Integer.toString(rankings.getInt("17",0)));
        blank18.setText(Integer.toString(rankings.getInt("18",0)));
        blank19.setText(Integer.toString(rankings.getInt("19",0)));
        blank20.setText(Integer.toString(rankings.getInt("20",0)));
        blank21.setText(Integer.toString(rankings.getInt("21",0)));
        blank22.setText(Integer.toString(rankings.getInt("22",0)));
        blank23.setText(Integer.toString(rankings.getInt("23",0)));
        blank24.setText(Integer.toString(rankings.getInt("24",0)));
        blank25.setText(Integer.toString(rankings.getInt("25",0)));
        blank26.setText(Integer.toString(rankings.getInt("26",0)));
        blank27.setText(Integer.toString(rankings.getInt("27",0)));
        blank28.setText(Integer.toString(rankings.getInt("28",0)));
        blank29.setText(Integer.toString(rankings.getInt("29",0)));
        blank30.setText(Integer.toString(rankings.getInt("30",0)));
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Quit the Experiment")
                .setMessage("Are you sure you want to quit the experiment? This cannot be undone!")
                .setPositiveButton("Quit", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        Intent back = new Intent(FinalScreen.this, HomeScreen.class);
                        startActivity(back);
                    }

                })
                .setNegativeButton("Cancel", null)
                .show();
    }

}
