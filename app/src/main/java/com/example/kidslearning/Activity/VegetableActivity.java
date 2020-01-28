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

import com.example.kidslearning.Adapter.VegetableAdapter;
import com.example.kidslearning.R;

import java.util.Locale;

public class VegetableActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{


    int[] image={R.drawable.beet_root,R.drawable.bitter_gourd,R.drawable.bottle_gourd,R.drawable.brinjal,R.drawable.broccoli,R.drawable.cabbage,R.drawable.capsicum,R.drawable.carrot,R.drawable.cauliflower,R.drawable.chillies,R.drawable.cucumber,R.drawable.drumsticks,R.drawable.fenugreek,R.drawable.fresh_beans,R.drawable.garlic,R.drawable.ginger,R.drawable.green_peas,R.drawable.lady_finger,R.drawable.onions,R.drawable.potato,R.drawable.pumpkin,R.drawable.radishes,R.drawable.spinach,R.drawable.tomatoes};
    String[] name={"Beet Root","Bitter Gourd","bottle Gourd","Brinjal","Broccoli","Cabbage","Capsicum","Carrot","Cauliflower","Chillies","Cucumber","Drumsticks","Fenugreek","Fresh Beans","Garlic","Ginger","GreenPeas","Lady Finger","Onions","Potato","Pumpkin","Radishes","Spinach","Tomato"};

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
        setContentView(R.layout.activity_vegetable);

        tts = new TextToSpeech(this, this);
        home = (ImageView) findViewById(R.id.homebutton);
        grid=findViewById(R.id.gridvegetable);

        PowerManager powerManager= (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock=powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK,"my lock");
        wakeLock.acquire();

        home.bringToFront();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VegetableActivity.this, DashboardActivity.class);
                startActivity(intent);
                wakeLock.release();
            }
        });

        VegetableAdapter vegetableAdapter =new VegetableAdapter(getApplicationContext(),name,image);
        grid.setAdapter(vegetableAdapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    case 0:
                        speakOut("Beet Root");
                        break;

                    case 1:
                        speakOut("Bitter Gourd");
                        break;

                    case 2:
                        speakOut("Bottle Gourd");
                        break;

                    case 3:
                        speakOut("Brinjal");
                        break;

                    case 4:
                        speakOut("Broccoli");
                        break;

                    case 5:
                        speakOut("Cabbage");
                        break;

                    case 6:
                        speakOut("Capsicum");
                        break;

                    case 7:
                        speakOut("Carrot");
                        break;

                    case 8:
                        speakOut("Cauliflower");
                        break;

                    case 9:
                        speakOut("Chillies");
                        break;

                    case 10:
                        speakOut("Cucumber");
                        break;

                    case 11:
                        speakOut("DrumSticks");
                        break;
                    case 12:
                        speakOut("Fenugreek");
                        break;

                    case 13:
                        speakOut("Fresh beans");
                        break;

                    case 14:
                        speakOut("Garlic");
                        break;

                    case 15:
                        speakOut("Ginger");
                        break;

                    case 16:
                        speakOut("green Peas");
                        break;

                    case 17:
                        speakOut("lady Finger");
                        break;
                    case 18:
                        speakOut("Onions");
                        break;

                    case 19:
                        speakOut("Potato");
                        break;

                    case 20:
                        speakOut("Pumpkin");
                        break;

                    case 21:
                        speakOut("radishes");
                        break;


                    case 22:
                        speakOut("Spinach");
                        break;

                    case 23:
                        speakOut("Tomato");
                        break;


                    default:
                        Toast.makeText(VegetableActivity.this, "please select Vegetable", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(VegetableActivity.this, "This Language is not supported", Toast.LENGTH_SHORT).show();
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
