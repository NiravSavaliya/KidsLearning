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

import com.example.kidslearning.Adapter.BodyAdapter;
import com.example.kidslearning.R;

import java.util.Locale;

public class BodyActivity2 extends AppCompatActivity implements TextToSpeech.OnInitListener{


    GridView grid;
    ImageView home;
    TextToSpeech tts;
    PowerManager.WakeLock wakeLock;

    int[] image={R.drawable.ankle,R.drawable.arm,R.drawable.chest,R.drawable.ear1,R.drawable.elbow,R.drawable.eyes,R.drawable.finger,R.drawable.foot,R.drawable.hair,R.drawable.hand,R.drawable.head,R.drawable.knee,R.drawable.leg,R.drawable.lips,R.drawable.mouth1,R.drawable.neck,R.drawable.noses,R.drawable.shoulder,R.drawable.teeth,R.drawable.thumb};
    String[] name={"Ankle","Arm","Chest","Ear","Elbow","Eyes","Finger","Foot","Hair","Hand","Head","Knee","Leg","Lips","Mouth","Neck","Noses","Shoulder","Teeth","Thumb"};


    @SuppressLint("InvalidWakeLockTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_body2);

        tts = new TextToSpeech(this, this);
        grid=findViewById(R.id.grid);
        home=findViewById(R.id.homebutton);

        PowerManager powerManager= (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock=powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK,"my lock");

        wakeLock.acquire();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(BodyActivity2.this,DashboardActivity.class);
                startActivity(intent);
                wakeLock.release();
            }
        });

        BodyAdapter bodyAdapter=new BodyAdapter(getApplicationContext(),name,image);
        grid.setAdapter(bodyAdapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    case 0:
                        speakOut("Ankle");
                        break;

                    case 1:
                        speakOut("Arm");
                        break;

                    case 2:
                        speakOut("Chest");
                        break;

                    case 3:
                        speakOut("Ear");
                        break;

                    case 4:
                        speakOut("Elbow");
                        break;

                    case 5:
                        speakOut("Eyes");
                        break;

                    case 6:
                        speakOut("Finger");
                        break;

                    case 7:
                        speakOut("Foot");
                        break;

                    case 8:
                        speakOut("Hair");
                        break;

                    case 9:
                        speakOut("Hand");
                        break;

                    case 10:
                        speakOut("Head");
                        break;

                    case 11:
                        speakOut("Knee");
                        break;


                    case 12:
                        speakOut("Leg");
                        break;

                    case 13:
                        speakOut("Lips");
                        break;

                    case 14:
                        speakOut("Mouth");
                        break;

                    case 15:
                        speakOut("Neck");
                        break;

                    case 16:
                        speakOut("Noses");
                        break;


                    case 17:
                        speakOut("shoulder");
                        break;

                    case 18:
                        speakOut("teeth");
                        break;

                    case 19:
                        speakOut("thumb");
                        break;

                    default:
                        Toast.makeText(BodyActivity2.this, "please select rhymes ", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS)
        {

            int result = tts.setLanguage(Locale.US);
            tts.setPitch(1.4f);
            tts.setSpeechRate(0.8f);


            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(BodyActivity2.this, "This Language is not supported", Toast.LENGTH_SHORT).show();
            } else {
                grid.setEnabled(true);
                speakOut("");
            }

        } else {
            Log.e("TTS", "Initialization Failed!");
        }

    }

    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
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
