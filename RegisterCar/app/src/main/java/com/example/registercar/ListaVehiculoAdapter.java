package com.example.registercar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListaVehiculoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ArrayList<String>> autos;

    public ListaVehiculoAdapter(Context context, ArrayList<ArrayList<String>> autos){
        this.context = context;
        this.autos = autos;
    }
    @Override
    public int getCount() {
        return autos.size();
    }

    @Override
    public Object getItem(int position) {
        return autos.get(position);
    }

    @Override
    public long getItemId(int position) {
            return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater lty = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = lty.inflate(R.layout.list_item_registro_1, null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.txt_marcaje);
        TextView item2 = (TextView) convertView.findViewById(R.id.txt_model);
        TextView item7 = (TextView) convertView.findViewById(R.id.txt_plaquis);
        TextView item10 = (TextView) convertView.findViewById(R.id.txt_pre);

        item.setText("Marca: "+autos.get(position).get(0));
        item2.setText("Modelo: "+ autos.get(position).get(1));
        item7.setText("Placa: "+autos.get(position).get(6));
        item10.setText("Valor: $"+autos.get(position).get(9));

        return convertView;
    }
}
