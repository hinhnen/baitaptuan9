package com.canhdinh.bai1_tuan9;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ColorAdapter extends ArrayAdapter<ColorObject> {
    Context context;
    int layoutResourceId;
    ArrayList<ColorObject> data;

    public ColorAdapter(Context context, int layoutResourceId, ArrayList<ColorObject> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    static class ColorHolder
    {
        ImageView imgIcon;
        TextView txtTitle;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ColorHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ColorHolder();
            holder.imgIcon = row.findViewById(R.id.imgIcon);
            holder.txtTitle = row.findViewById(R.id.txtTitle);

            row.setTag(holder);
        }
        else
        {
            holder = (ColorHolder)row.getTag();
        }
        // xử lý
        ColorObject colorObject = data.get(position);
        holder.txtTitle.setText(colorObject.getColor());
        holder.txtTitle.setBackground(new ColorDrawable(Color.parseColor(colorObject.getColor())));
        return row;
    }

}
