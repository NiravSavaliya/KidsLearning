package com.example.kidslearning.Rhymes;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.widget.ImageView;

import com.example.kidslearning.R;

public class BaaBlackSheepActivity extends AppCompatActivity {

    MediaPlayer mp;
    PowerManager.WakeLock wakeLock;

    @SuppressLint("InvalidWakeLockTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baa_black_sheep);

        ImageView home=findViewById(R.id.homebutton);

        PowerManager powerManager= (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock=powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK,"my lock");

        wakeLock.acquire();


        mp=new MediaPlayer();
        mp= MediaPlayer.create(this,R.raw.baabaablack);
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mp.setLooping(false);
        mp.start();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                wakeLock.release();

                Intent intent=new Intent(getApplicationContext(), RhymesActivity.class);
                startActivity(intent);
                mp.stop();
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        wakeLock.release();

        super.onBackPressed();
        mp.stop();
    }
}
