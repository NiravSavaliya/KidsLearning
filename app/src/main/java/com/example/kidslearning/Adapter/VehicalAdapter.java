package com.example.kidslearning.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kidslearning.R;

public class VehicalAdapter extends BaseAdapter
{
    Context context;
    final String[] name;
    final int[] image;
    private static LayoutInflater inflater = null;

    public VehicalAdapter(Context context, String[] name, int[] image) {
        this.name = name;
        this.image = image;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return name.length;

    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder {
        TextView text;
        ImageView image;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        VehicalAdapter.Holder holder = new VehicalAdapter.Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.designview, null);
        holder.text = rowView.findViewById(R.id.txtimg);
        holder.image = rowView.findViewById(R.id.img);

        holder.text.setText(name[position]);
        holder.image.setImageResource(image[position]);

        return rowView;

    }
}