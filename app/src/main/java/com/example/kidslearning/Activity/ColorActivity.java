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

import com.example.kidslearning.Adapter.ColorAdapter;
import com.example.kidslearning.R;

import java.util.Locale;

public class ColorActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{


    int[] image={R.drawable.black_color,R.drawable.blue_color,R.drawable.brown,R.drawable.gray,R.drawable.green_color,R.drawable.orange,R.drawable.pink,R.drawable.purple,R.drawable.red_color,R.drawable.turquoise,R.drawable.white,R.drawable.yellow_color};
    String[] name={"Black","Blue","Brown","Gray","Green","Orange","Pink","Purple","Red","Turquoise","White","Yellow"};

    GridView grid;

    ImageView home;
    TextToSpeech tts;
    PowerManager.WakeLock wakeLock;

    @SuppressLint("InvalidWakeLockTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_color);

        tts = new TextToSpeech(this, this);
        home = (ImageView) findViewById(R.id.homebutton);

        grid=findViewById(R.id.gridcolor);

        PowerManager powerManager= (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock=powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK,"my lock");
        wakeLock.acquire();

        home.bringToFront();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ColorActivity.this, DashboardActivity.class);
                startActivity(intent);
                wakeLock.release();
            }
        });


        ColorAdapter colorAdapter =new ColorAdapter(getApplicationContext(),name,image);
        grid.setAdapter(colorAdapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    case 0:
                        speakOut("Black");
                        break;

                    case 1:
                        speakOut("Blue");
                        break;

                    case 2:
                        speakOut("Brown");
                        break;

                    case 3:
                        speakOut("Gray");
                        break;

                    case 4:
                        speakOut("Green");
                        break;

                    case 5:
                        speakOut("Orange");
                        break;

                    case 6:
                        speakOut("Pink");
                        break;

                    case 7:
                        speakOut("Purple");
                        break;

                    case 8:
                        speakOut("Red");
                        break;

                    case 9:
                        speakOut("Turquoise");
                        break;

                    case 10:
                        speakOut("White");
                        break;

                    case 11:
                        speakOut("Yellow");
                        break;

                    default:
                        Toast.makeText(ColorActivity.this, "please select Color ", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(ColorActivity.this, "This Language is not supported", Toast.LENGTH_SHORT).show();
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
        wakeLock.release();
        finish();
    }

    private void speakOut(String text) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);


    }
}
