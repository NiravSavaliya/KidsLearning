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
import com.example.kidslearning.Adapter.FlowerAdapter;
import com.example.kidslearning.R;

import java.util.Locale;

public class FlowerActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    int[] image={R.drawable.carnation,R.drawable.daffodils,R.drawable.daisy,R.drawable.hibiscus,R.drawable.iris,R.drawable.jasmine,R.drawable.lily,R.drawable.lotus,R.drawable.magnolia,R.drawable.marigold,R.drawable.rose,R.drawable.sunflower};
    String[] name={"Carnation","Daffodils","Daisy","Hibiscus","Iris","Jasmine","Lily","Lotus","Magnolia","Marigold","Rose","Sunflower"};

    ImageView home;
    GridView grid;

    TextToSpeech tts;
    PowerManager.WakeLock wakeLock;

    @SuppressLint("InvalidWakeLockTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_flower);

        tts = new TextToSpeech(this, this);
        home = (ImageView) findViewById(R.id.homebutton);

        grid=findViewById(R.id.gridflower);
        PowerManager powerManager= (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock=powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK,"my lock");
        wakeLock.acquire();

        home.bringToFront();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FlowerActivity.this, DashboardActivity.class);
                startActivity(intent);
                wakeLock.release();
            }
        });

        FlowerAdapter flowerAdapter =new FlowerAdapter(getApplicationContext(),name,image);
        grid.setAdapter(flowerAdapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    case 0:
                        speakOut("Carnation");
                        break;

                    case 1:
                        speakOut("Daffodils");
                        break;

                    case 2:
                        speakOut("Daisy");
                        break;

                    case 3:
                        speakOut("Hibiscus");
                        break;

                    case 4:
                        speakOut("Iris");
                        break;

                    case 5:
                        speakOut("Jasmine");
                        break;

                    case 6:
                        speakOut("Lily");
                        break;

                    case 7:
                        speakOut("Lotus");
                        break;

                    case 8:
                        speakOut("Magnolia");
                        break;

                    case 9:
                        speakOut("Marigold");
                        break;

                    case 10:
                        speakOut("Rose");
                        break;

                    case 11:
                        speakOut("Sunflower");
                        break;

                    default:
                        Toast.makeText(FlowerActivity.this, "please select Flower ", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(FlowerActivity.this, "This Language is not supported", Toast.LENGTH_SHORT).show();
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
