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

import com.example.kidslearning.Adapter.BirdsAdapter;
import com.example.kidslearning.R;

import java.util.Locale;

public class BirdsActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{


    int[] image={R.drawable.crane,R.drawable.crow,R.drawable.cuckoo,R.drawable.nightangle,R.drawable.owl,R.drawable.parrot,R.drawable.peacock,R.drawable.pigeon,R.drawable.pinguin,R.drawable.sparrow,R.drawable.swan,R.drawable.vulture};
    String[] name={"Crane","Crow","Cuckoo","Nightangle","Owl","Parrot","Peacock","Pigeon","Pinguin","Spparow","Swan","Vulture"};

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
        setContentView(R.layout.activity_birds);

        tts = new TextToSpeech(this, this);
        home = (ImageView) findViewById(R.id.homebutton);

        grid=findViewById(R.id.gridbird);

        PowerManager powerManager= (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock=powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK,"my lock");

        wakeLock.acquire();

        home.bringToFront();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BirdsActivity.this, DashboardActivity.class);
                startActivity(intent);
                wakeLock.release();
            }
        });

        BirdsAdapter birdsAdapter =new BirdsAdapter(getApplicationContext(),name,image);
        grid.setAdapter(birdsAdapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    case 0:
                        speakOut("Crane");
                        break;

                    case 1:
                        speakOut("Crow");
                        break;

                    case 2:
                        speakOut("Cuckoo");
                        break;

                    case 3:
                        speakOut("Nightangle");
                        break;

                    case 4:
                        speakOut("Owl");
                        break;

                    case 5:
                        speakOut("Parrot");
                        break;

                    case 6:
                        speakOut("Peacock");
                        break;

                    case 7:
                        speakOut("Pigeon");
                        break;

                    case 8:
                        speakOut("Pinguin");
                        break;

                    case 9:
                        speakOut("Sparrow");
                        break;

                    case 10:
                        speakOut("Swan");
                        break;

                    case 11:
                        speakOut("Vulture");
                        break;

                    default:
                        Toast.makeText(BirdsActivity.this, "please select Bird ", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(BirdsActivity.this, "This Language is not supported", Toast.LENGTH_SHORT).show();
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
