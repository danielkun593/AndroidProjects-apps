package com.example.registercar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class SegundoActivity extends Activity {
    private ListaVehiculoAdapter adaptador;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_lista_de_registros);

        lv = (ListView) findViewById(R.id.lv_items);

        ArrayList<ArrayList<String>> registros = ListaVehiculos.cars;

        try{
            adaptador = new ListaVehiculoAdapter(this, registros);
            lv.setAdapter(adaptador);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                    Intent vista = new Intent(SegundoActivity.this, TercerActivity.class);
                    vista.putExtra("Marca", registros.get(i).get(0));
                    vista.putExtra("Modelo", registros.get(i).get(1));
                    vista.putExtra("Cilindraje", registros.get(i).get(2));
                    vista.putExtra("Manejo", registros.get(i).get(3));
                    vista.putExtra("Color", registros.get(i).get(4));
                    vista.putExtra("Año", registros.get(i).get(5));
                    vista.putExtra("Placa", registros.get(i).get(6));
                    vista.putExtra("Matricula", registros.get(i).get(7));
                    vista.putExtra("Pais", registros.get(i).get(8));
                    vista.putExtra("Precio", registros.get(i).get(9));
                    startActivity(vista);

                    //Toast.makeText(SegundoActivity.this, parent.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
                    //Toast.makeText(SegundoActivity.this, parent.getItemIdAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            Log.e("Error", "onCreate: ", e);
            //Toast.makeText(SegundoActivity.this,"Error en "+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void volver (View view){
        Intent regresar = new Intent(this, MainActivity.class);
        startActivity(regresar);
    }
}
