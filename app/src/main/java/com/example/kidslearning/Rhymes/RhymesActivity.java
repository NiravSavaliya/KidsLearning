package com.example.kidslearning.Rhymes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kidslearning.Activity.DashboardActivity;
import com.example.kidslearning.Model.Model;
import com.example.kidslearning.R;

import java.util.ArrayList;
import java.util.List;

public class RhymesActivity extends AppCompatActivity {

    GridView grid;
    ImageView home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_rhymes);

        grid=findViewById(R.id.grid);
        final List<Model> list=getAlldata();
        home=findViewById(R.id.homebutton);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(RhymesActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });

        Customadapter customadapter=new Customadapter(list,getApplicationContext());
        grid.setAdapter(customadapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = null;

                switch (position)
                {
                    case 0:
                        intent=new Intent(RhymesActivity.this,BaaBlackSheepActivity.class);
                        break;

                    case 1:
                        intent=new Intent(RhymesActivity.this,BitsOfPaperActivity.class);
                        break;

                    case 2:
                        intent=new Intent(RhymesActivity.this,ChubbyCheeksActivity.class);
                        break;

                    case 3:
                        intent=new Intent(RhymesActivity.this,DingDongBellActivity.class);
                        break;

                    case 4:
                        intent=new Intent(RhymesActivity.this,EllieTheElephantActivity.class);
                        break;

                    case 5:
                        intent=new Intent(RhymesActivity.this,HumptyDumptySatOnWallActivity.class);
                        break;

                    case 6:
                        intent=new Intent(RhymesActivity.this,IamLittleTeapotActivity.class);
                        break;

                    case 7:
                        intent=new Intent(RhymesActivity.this,JackAndJillActivity.class);
                        break;

                    case 8:
                        intent=new Intent(RhymesActivity.this,JohnyJohnyYesPapaActivity.class);
                        break;

                    case 9:
                        intent=new Intent(RhymesActivity.this,BucklemyshoeActivity.class);
                        break;

                    case 10:
                        intent=new Intent(RhymesActivity.this,RainGoAwayActivity.class);
                        break;

                    case 11:
                        intent=new Intent(RhymesActivity.this,TwinkleLittleStarActivity.class);
                        break;

                    default:
                        Toast.makeText(RhymesActivity.this, "please select rhymes ", Toast.LENGTH_SHORT).show();
                }
                startActivity(intent);



            }
        });

    }

    private List<Model> getAlldata()
    {
        List<Model>list=new ArrayList<>();
        list.add(new Model(R.drawable.baabaa_blacksheep_index));
        list.add(new Model(R.drawable.bitsofpapers_index));
        list.add(new Model(R.drawable.chubbycheeksdimplechin_index));
        list.add(new Model(R.drawable.dingdongbell_index));
        list.add(new Model(R.drawable.ellytheelephant_index));
        list.add(new Model(R.drawable.humptydumpty_index));
        list.add(new Model(R.drawable.iamlittleteapot_index));
        list.add(new Model(R.drawable.jackandjill_index));
        list.add(new Model(R.drawable.johnyjohnyyespapa_index));
        list.add(new Model(R.drawable.onetwobucklemyshoe_index));
        list.add(new Model(R.drawable.rainraingoaway_index));
        list.add(new Model(R.drawable.twinkletwinkle_index));
        return list;
    }

    public class Customadapter extends BaseAdapter
    {

        List<Model>list;
        Context context;

        public Customadapter(List<Model>list,Context context)
        {
            this.context=context;
            this.list=list;

        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            TextView textView;
            RatingBar ratingBar;
            View grid;

            LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            if (convertView==null)
            {

                grid= (View) inflater.inflate(R.layout.design,null);

                imageView = grid.findViewById(R.id.img);

                imageView.setImageResource(list.get(position).getImage());

            }
            else {
                grid = convertView;

            }


            return grid;
        }

    }
}
