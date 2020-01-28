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

import com.example.kidslearning.Activity.DashboardActivity;
import com.example.kidslearning.Adapter.AnimalAdapter;
import com.example.kidslearning.R;

import java.util.Locale;

public class AnimalActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{


    int[] image={R.drawable.camel,R.drawable.cat1,R.drawable.cow,R.drawable.dog,R.drawable.elephant1,R.drawable.fox,R.drawable.girafffe,R.drawable.goat,R.drawable.horse,R.drawable.lion,R.drawable.monkey,R.drawable.sheep,R.drawable.tiger};
    String[] name={"Camel","Cat","Cow","Dog","Elephant","Fox","Giraffe","Goat","Horse","Lion","Monkey","Sheep","Tiger"};

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
        setContentView(R.layout.activity_animal);

        tts = new TextToSpeech(this, this);
        home = (ImageView) findViewById(R.id.homebutton);
        grid=findViewById(R.id.gridanimal);

        PowerManager powerManager= (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock=powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK,"my lock");

        wakeLock.acquire();
        home.bringToFront();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnimalActivity.this, DashboardActivity.class);
                startActivity(intent);
                wakeLock.release();
            }
        });


        AnimalAdapter animalAdapter =new AnimalAdapter(getApplicationContext(),name,image);
        grid.setAdapter(animalAdapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    case 0:
                        speakOut("Camel");
                        break;

                    case 1:
                        speakOut("Cat");
                        break;

                    case 2:
                        speakOut("Cow");
                        break;

                    case 3:
                        speakOut("Dog");
                        break;

                    case 4:
                        speakOut("Elephant");
                        break;

                    case 5:
                        speakOut("Fox");
                        break;

                    case 6:
                        speakOut("Giraffe");
                        break;

                    case 7:
                        speakOut("Goat");
                        break;

                    case 8:
                        speakOut("Horse");
                        break;

                    case 9:
                        speakOut("Lion");
                        break;

                    case 10:
                        speakOut("Monkey");
                        break;

                    case 11:
                        speakOut("Sheep");
                        break;
                    case 12:
                        speakOut("Tiger");
                        break;

                    default:
                        Toast.makeText(AnimalActivity.this, "please select Animal ", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(AnimalActivity.this, "This Language is not supported", Toast.LENGTH_SHORT).show();
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
