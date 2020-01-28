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
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kidslearning.Adapter.SeaAnimalAdapter;
import com.example.kidslearning.R;

import java.util.Locale;

public class SeaanimalActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    GridView grid;
    ImageView home;
    TextToSpeech tts;

    int[] image={R.drawable.blue_whale,R.drawable.clownfish,R.drawable.crab,R.drawable.dolphin,R.drawable.octopus,R.drawable.sea_lion,R.drawable.seahorse,R.drawable.shark,R.drawable.shell_crown_conch,R.drawable.starfish,R.drawable.turtle,R.drawable.walrus};
    String[] name={"Blue Whale","ClowFish","Crab","Dolphin","Octopus","Sea Lion","Sea Horse","Shark","Shell Crown Conch","Starfish","Turtle","Walrus"};
    PowerManager.WakeLock wakeLock;

    @SuppressLint("InvalidWakeLockTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_seaanimal);


        tts = new TextToSpeech(this, this);
        home = (ImageView) findViewById(R.id.homebutton);
        grid=findViewById(R.id.gridseaanimal);

        PowerManager powerManager= (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock=powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK,"my lock");
        wakeLock.acquire();

        home.bringToFront();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeaanimalActivity.this, DashboardActivity.class);
                startActivity(intent);
                wakeLock.release();
            }
        });


        SeaAnimalAdapter seaAnimalAdapter =new SeaAnimalAdapter(getApplicationContext(),name,image);
        grid.setAdapter(seaAnimalAdapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    case 0:
                        speakOut("Blue Whale");
                        break;

                    case 1:
                        speakOut("Clow Fish");
                        break;

                    case 2:
                        speakOut("Crab");
                        break;

                    case 3:
                        speakOut("Dolphin");
                        break;

                    case 4:
                        speakOut("Octopus");
                        break;

                    case 5:
                        speakOut("Sea Lion");
                        break;

                    case 6:
                        speakOut("Sea horse");
                        break;

                    case 7:
                        speakOut("Shark");
                        break;

                    case 8:
                        speakOut("Shell Crown Conch");
                        break;

                    case 9:
                        speakOut("Starfish");
                        break;

                    case 10:
                        speakOut("Turtle");
                        break;

                    case 11:
                        speakOut("Walrus");
                        break;


                    default:
                        Toast.makeText(SeaanimalActivity.this, "please select rhymes ", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS)
        {

            int result = tts.setLanguage(Locale.US);
            tts.setPitch(1.4f);
            tts.setSpeechRate(0.8f);


            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(SeaanimalActivity.this, "This Language is not supported", Toast.LENGTH_SHORT).show();
            } else {

                grid.setEnabled(true);
                speakOut("");
            }

        } else {
            Log.e("TTS", "Initialization Failed!");
        }

    }

    @Override
    public void onBackPressed()
    {
        finish();
        wakeLock.release();

    }

    private void speakOut(String text) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

}
