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

import com.example.kidslearning.Adapter.FruitAdapter;
import com.example.kidslearning.R;

import java.util.Locale;

public class FruitActivity extends AppCompatActivity implements TextToSpeech.OnInitListener
{

    int[] image={R.drawable.apple,R.drawable.banana,R.drawable.cherry,R.drawable.chikoo,R.drawable.custard_apple,R.drawable.grape,R.drawable.guava,R.drawable.kiwi,R.drawable.mangos,R.drawable.melon,R.drawable.orange1,R.drawable.papaya,R.drawable.pineapple,R.drawable.pomegranate,R.drawable.strawberry,R.drawable.watermelon};
    String[] name={"Apple","Banana","Cherry","Chikoo","Custard Apple","Grapes","Guava","Kiwi","Mango","Melon","Orange","Papaya","PineApple","Pomegranate","StrawBerry","WaterMelon"};

    ImageView home;
    TextToSpeech tts;
    PowerManager.WakeLock wakeLock;
    GridView grid;


    @SuppressLint("InvalidWakeLockTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fruit);


        tts = new TextToSpeech(this, this);
        home = (ImageView) findViewById(R.id.homebutton);
        grid=findViewById(R.id.gridfruit);

        PowerManager powerManager= (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock=powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK,"my lock");
        wakeLock.acquire();

        home.bringToFront();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FruitActivity.this, DashboardActivity.class);
                startActivity(intent);
                wakeLock.release();
            }
        });

        FruitAdapter fruitAdapter =new FruitAdapter(getApplicationContext(),name,image);
        grid.setAdapter(fruitAdapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    case 0:
                        speakOut("Apple");
                        break;

                    case 1:
                        speakOut("Banana");
                        break;

                    case 2:
                        speakOut("Cherry");
                        break;

                    case 3:
                        speakOut("Chikoo");
                        break;

                    case 4:
                        speakOut("Custard Apple");
                        break;

                    case 5:
                        speakOut("Grapes");
                        break;

                    case 6:
                        speakOut("Guava");
                        break;

                    case 7:
                        speakOut("Kiwi");
                        break;

                    case 8:
                        speakOut("Mango");
                        break;

                    case 9:
                        speakOut("Melon");
                        break;

                    case 10:
                        speakOut("Orange");
                        break;

                    case 11:
                        speakOut("Papaya");
                        break;
                    case 12:
                        speakOut("Pineapple");
                        break;

                    case 13:
                        speakOut("Pomegranate");
                        break;

                    case 14:
                        speakOut("Strawberry");
                        break;

                    case 15:
                        speakOut("Watermelon");
                        break;


                    default:
                        Toast.makeText(FruitActivity.this, "please select fruit ", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(FruitActivity.this, "This Language is not supported", Toast.LENGTH_SHORT).show();
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