package com.example.miprimerapps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class LibrosAdapter extends BaseAdapter {

    private Context contex;
    private ArrayList<String> libros;

    public LibrosAdapter(Context contexto_activity, ArrayList<String> nombres_libros)
    {
        this.contex =contexto_activity;
        this.libros =nombres_libros;

    }




    @Override
    public int getCount() {
        return libros.size();
    }

    @Override
    public Object getItem(int position) {
        return libros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            LayoutInflater lytinflater = (LayoutInflater) contex.getSystemService(contex.LAYOUT_INFLATER_SERVICE);
            convertView = lytinflater.inflate(R.layout.itemgrid_layout,null);

        }
        TextView titulotv=   (TextView) convertView.findViewById(R.id.id_txt_gridview);
        titulotv.setText(libros.get(position));

        return convertView;
    }
}
