package com.example.kidslearning.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kidslearning.Activity.AlphabetActivity;
import com.example.kidslearning.Activity.AnimalActivity;
import com.example.kidslearning.Activity.BirdsActivity;
import com.example.kidslearning.Activity.BodyActivity;
import com.example.kidslearning.Activity.ColorActivity;
import com.example.kidslearning.Activity.DrawingActivity;
import com.example.kidslearning.Activity.FlowerActivity;
import com.example.kidslearning.Activity.FruitActivity;
import com.example.kidslearning.Activity.MonthActivity;
import com.example.kidslearning.Activity.NumberActivity;
import com.example.kidslearning.Activity.PuzzleActivity;
import com.example.kidslearning.Activity.SeaanimalActivity;
import com.example.kidslearning.Activity.ShapeActivity;
import com.example.kidslearning.Activity.VegetableActivity;
import com.example.kidslearning.Activity.VehicalActivity;
import com.example.kidslearning.Activity.WeekActivity;
import com.example.kidslearning.Model.Category;
import com.example.kidslearning.R;
import com.example.kidslearning.Rhymes.RhymesActivity;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder>
{
    private Context mContext;
    private List<Category> categoryList;

    public CategoryAdapter(Context mContext, List<Category> categoryList) {
        this.mContext = mContext;
        this.categoryList = categoryList;
    }


    @NonNull
    @Override
    public CategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.MyViewHolder holder, int position) {

        Category category=categoryList.get(position);
        holder.title.setText(category.getName());
       Glide.with(mContext).load(category.getThumbnail()).into(holder.thumbnail);




    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public ImageView thumbnail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mContext=itemView.getContext();

            title = (TextView) itemView.findViewById(R.id.title);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final Intent intent;
                    switch (getAdapterPosition())
                    {
                        case 0:
                            intent=new Intent(mContext, AlphabetActivity.class);
                            break;

                        case 1:
                            intent=new Intent(mContext, RhymesActivity.class);
                            break;

                        case 2:
                            intent=new Intent(mContext, NumberActivity.class);
                            break;

                        case 3:
                            intent=new Intent(mContext, WeekActivity.class);
                            break;

                        case 4:
                            intent=new Intent(mContext, MonthActivity.class);
                            break;

                        case 5:
                            intent=new Intent(mContext, BodyActivity.class);
                            break;

                        case 6:
                            intent=new Intent(mContext, PuzzleActivity.class);
                            break;

                        case 7:
                            intent=new Intent(mContext, DrawingActivity.class);
                            break;

                        case 8:
                            intent=new Intent(mContext, FruitActivity.class);
                            break;


                        case 9:
                            intent=new Intent(mContext, FlowerActivity.class);
                            break;

                        case 10:
                            intent=new Intent(mContext, VegetableActivity.class);
                            break;

                        case 11:
                            intent=new Intent(mContext, BirdsActivity.class);
                            break;

                        case 12:
                            intent=new Intent(mContext, AnimalActivity.class);
                            break;

                        case 13:
                            intent=new Intent(mContext, SeaanimalActivity.class);
                            break;

                        case 14:
                            intent=new Intent(mContext, VehicalActivity.class);
                            break;

                        case 15:
                            intent=new Intent(mContext, ShapeActivity.class);
                            break;

                        case 16:
                            intent=new Intent(mContext, ColorActivity.class);
                            break;


                        default:
                            throw new IllegalStateException("Unexpected value: " + getAdapterPosition());
                    }
                    mContext.startActivity(intent);

                }
            });

        }
    }
}
