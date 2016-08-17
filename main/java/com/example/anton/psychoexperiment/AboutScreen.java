package com.example.anton.psychoexperiment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.widget.TextView;

public class AboutScreen extends AppCompatActivity {

    private static final int SPAN_INCLUSIVE_INCLUSIVE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView help = (TextView) findViewById(R.id.help);
        int textSize1 = getResources().getDimensionPixelSize(R.dimen.text_size_1);
        int textSize2 = getResources().getDimensionPixelSize(R.dimen.text_size_2);
        String text1 = "Ranking System";
        String text2 = "1. Unacceptable\nCannot watch anymore / Cannot bear to sit through this quality\n\n2: Satisfactory\nCan sit through the video at this quality, but it might be annoying\n\n3: Good\nCan definitely sit through the video, but the quality does detract a bit of enjoyment\n\n4. Excellent\nNo complaints";
        String text3 = "About the Experiment";
        String text4 = "The goal of this experiment is to evaluate the difference between actual video quality and perceived video quality. You will be shown 30 two-minute clips of the movie streamed through varying quality of WiFi. In the last 20 seconds of every clip, your phone will vibrate and you will be prompted for a rating.";
        SpannableString span1 = new SpannableString(text1);
        span1.setSpan(new AbsoluteSizeSpan(textSize1), 0, text1.length(), SPAN_INCLUSIVE_INCLUSIVE);
        SpannableString span2 = new SpannableString(text2);
        span2.setSpan(new AbsoluteSizeSpan(textSize2), 0, text2.length(), SPAN_INCLUSIVE_INCLUSIVE);
        SpannableString span3 = new SpannableString(text3);
        span3.setSpan(new AbsoluteSizeSpan(textSize1), 0, text3.length(), SPAN_INCLUSIVE_INCLUSIVE);
        SpannableString span4 = new SpannableString(text4);
        span4.setSpan(new AbsoluteSizeSpan(textSize2), 0, text4.length(), SPAN_INCLUSIVE_INCLUSIVE);
        CharSequence finalText = TextUtils.concat(span1, "\n\n", span2, "\n\n", span3, "\n\n", span4);
        help.append(finalText);
    }

}
