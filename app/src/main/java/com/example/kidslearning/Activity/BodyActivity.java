package com.example.kidslearning.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kidslearning.Activity.DashboardActivity;
import com.example.kidslearning.R;

import java.util.Locale;

public class BodyActivity extends AppCompatActivity {


    ImageView img1,home;

    @SuppressLint("InvalidWakeLockTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_body);

        home = (ImageView) findViewById(R.id.home);
        img1 = (ImageView) findViewById(R.id.human);


        home.bringToFront();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BodyActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                speakOut("Kids Let's Learn our External body parts:  Hair, Forehead, Eye, Ear, Nose, Mouth, Lips, Neck, Shoulder, Chest, Arm, Stomach, Elbow, Thumb, Hand, Fingers, Leg, Knee, Foot, Ankle ");
*/
            Intent intent=new Intent(BodyActivity.this,BodyActivity2.class);
            startActivity(intent);

            }
        });

    }




}
