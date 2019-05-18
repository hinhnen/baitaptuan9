package com.canhdinh.bai2_tuan9;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SanPhamAdapter extends ArrayAdapter<SanPham> {

    Context context;
    int layoutResourceId;
    ArrayList<SanPham> data;

    public SanPhamAdapter(Context context, int layoutResourceId, ArrayList<SanPham> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    static class SanPhamHolder
    {
        ImageView imgIcon;
        TextView txtTitle;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        SanPhamHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new SanPhamHolder();
            holder.imgIcon = row.findViewById(R.id.imgIcon);
            holder.txtTitle = row.findViewById(R.id.txtTitle);

            row.setTag(holder);
        }
        else
        {
            holder = (SanPhamHolder)row.getTag();
        }

        SanPham sanPham = data.get(position);
        holder.txtTitle.setText(sanPham.getTensp());
        Picasso.get()
                .load(sanPham.getHinhAnh())
                .into(holder.imgIcon);
        return row;
    }

}
