package com.example.miprimerapps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class GridView_Activity extends Activity {
    private GridView lista;
    private LibrosAdapter listado_adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview_layout);

        lista = (GridView) findViewById(R.id.id_gridview);

        ArrayList<String> nombres_libros = new ArrayList<>();
        nombres_libros.add("Fisica-Serway");
        nombres_libros.add("Viaje al Centro de la Tierra");
        nombres_libros.add("El principito");
        nombres_libros.add("Madame Bouvary");
        nombres_libros.add("Frida");


        listado_adaptador = new LibrosAdapter(this,nombres_libros);

        lista.setAdapter(listado_adaptador);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GridView_Activity.this,parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();

                Intent intento =new Intent(GridView_Activity.this, DetalleItemGridViewActivity.class);
                intento.putExtra("nombre",listado_adaptador.getItem(position).toString());
                startActivity(intento);
            }
        });

        /*
         lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListViewActivity.this,adapterView.getItemAtPosition(i).toString(),Toast.LENGTH_SHORT).show();

            }
        });
        *
        * */

       /* ArrayList<String> lista_numeros=new ArrayList<>();
        for(int i=1; i<=10; i++)
        {
            lista_numeros.add(""+i);
        }

        ArrayAdapter<String> lista_adaptador= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,lista_numeros);

        lista.setAdapter(lista_adaptador);
        */



    }
}
