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
import com.example.kidslearning.Adapter.VehicalAdapter;
import com.example.kidslearning.R;

import java.util.Locale;

public class VehicalActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{

    int[] image={R.drawable.aeroplane,R.drawable.ambulance,R.drawable.bicycle,R.drawable.bullock_cart,R.drawable.bus,R.drawable.fighter_plane,R.drawable.fire_engine,R.drawable.helicopter,R.drawable.motorcycle,R.drawable.pram,R.drawable.sledge,R.drawable.steamer,R.drawable.tank,R.drawable.tonga,R.drawable.tractor,R.drawable.train,R.drawable.tricycle,R.drawable.truck, R.drawable.vann};
    String[] name={"AeroPlane","Ambulance","Bicycle","Bullock Cart","Bus","Fighter Plane","Fire Brigade","Helicopter","Motor cycle","Children Bycycle","Sledge","Steamer","Tank","Tonga","Tractor","Train","Tricycle","Truck","Van"};

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
        setContentView(R.layout.activity_vehical);

        tts = new TextToSpeech(this, this);
        home = (ImageView) findViewById(R.id.homebutton);
        grid=findViewById(R.id.gridvehical);

        PowerManager powerManager= (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock=powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK,"my lock");
        wakeLock.acquire();

        home.bringToFront();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VehicalActivity.this, DashboardActivity.class);
                startActivity(intent);
                wakeLock.release();
            }
        });

        VehicalAdapter vehicalAdapter =new VehicalAdapter(getApplicationContext(),name,image);
        grid.setAdapter(vehicalAdapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    case 0:
                        speakOut("AeroPlane");
                        break;

                    case 1:
                        speakOut("Ambulance");
                        break;

                    case 2:
                        speakOut("Bicycle");
                        break;

                    case 3:
                        speakOut("Bullock Cart");
                        break;

                    case 4:
                        speakOut("Bus");
                        break;

                    case 5:
                        speakOut("Fighter Plane");
                        break;

                    case 6:
                        speakOut("Fire Brigade");
                        break;

                    case 7:
                        speakOut("Helicopter");
                        break;

                    case 8:
                        speakOut("MotorCycle");
                        break;

                    case 9:
                        speakOut("Children Bicycle");
                        break;

                    case 10:
                        speakOut("Sledge");
                        break;

                    case 11:
                        speakOut("Steamer");
                        break;
                    case 12:
                        speakOut("Tank");
                        break;

                    case 13:
                        speakOut("Tonga");
                        break;

                    case 14:
                        speakOut("Tractor");
                        break;

                    case 15:
                        speakOut("Train");
                        break;


                    case 16:
                        speakOut("Tricycle");
                        break;

                    case 17:
                        speakOut("Truck");
                        break;

                    case 18:
                        speakOut("Van");
                        break;


                    default:
                        Toast.makeText(VehicalActivity.this, "please select Vehical ", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(VehicalActivity.this, "This Language is not supported", Toast.LENGTH_SHORT).show();
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
