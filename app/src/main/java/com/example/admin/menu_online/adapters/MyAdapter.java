package com.example.admin.menu_online.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.menu_online.R;

import java.util.ArrayList;

import com.example.admin.menu_online.models.MonAn;

/**
 * Created by Admin on 4/13/2017.
 */

public class MyAdapter extends ArrayAdapter<MonAn> {

    Context context;
    int resource;
    ArrayList<MonAn> objects;
    public MyAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<MonAn> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        MonAn monAn = objects.get(position);

        if(convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(this.context);
            convertView = inflater.inflate(this.resource, parent, false);
            viewHolder.imgMonAn = (ImageView) convertView.findViewById(R.id.imgMonAn);
            viewHolder.txtRenTen = (TextView) convertView.findViewById(R.id.txtRenTen);
            viewHolder.txtRenSoLuong = (TextView) convertView.findViewById(R.id.txtRenSoLuong);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imgMonAn.setBackgroundResource(monAn.getImage());
        viewHolder.txtRenTen.setText(monAn.getTenMonAn());
        viewHolder.txtRenSoLuong.setText(String.valueOf(monAn.getSoLuong()));

        return convertView;

    }
    private class ViewHolder{
        public ImageView imgMonAn;
        public TextView txtRenTen, txtRenSoLuong;
    }
}
