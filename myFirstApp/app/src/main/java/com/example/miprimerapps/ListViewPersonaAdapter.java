package com.example.miprimerapps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewPersonaAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<String> names;

    public ListViewPersonaAdapter(Context context,ArrayList<String> names){
        this.context = context;
        this.names =names;

    }
    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int i) {
        return names.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view==null)
        {
            LayoutInflater lytinflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = lytinflater.inflate(R.layout.itemview_layout,null);
        }
        TextView titulotv = (TextView) view.findViewById(R.id.id_texto_item_list);
        titulotv.setText(names.get(i));

        return view;
    }
}
