package com.example.examenprogramacion56sa;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.examenprogramacion56sa.SQLite_DateBase.ConexionBaseDeDatos;

import java.util.ArrayList;

public class Pantalla3 extends AppCompatActivity {
    private ListView listView;
    private Button button;
    private ConexionBaseDeDatos conn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla3);

        conn = new ConexionBaseDeDatos(getApplicationContext(), "DBExamen", null, 1);

        listView = (ListView)findViewById(R.id.list_producto);
        button = (Button)findViewById(R.id.id_volver);

        try {
            ArrayList<String> listado = new ArrayList<>();
            Cursor data = conn.getListaContenido();
            if (data.getCount()==0){
                Toast.makeText(getApplicationContext(), "No existen elementos para mostrar", Toast.LENGTH_SHORT).show();
            }else {
                while (data.moveToNext()){
                    listado.add("Codigo: "+data.getString(0)+"\n"+"Nombre: "+data.getString(1)+"\n"+"Stock: "+data.getString(2)+"\n"+"Valor/unidad: "+data.getString(3)
                            +"\n"+"Categoria: "+data.getString(4)+"\n"+"Ubicacion: \n"+data.getString(5));
                    ListAdapter listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listado);
                    listView.setAdapter(listAdapter);
                }
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error en "+e.toString(), Toast.LENGTH_LONG).show();
        }

        //botton
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volver = new Intent(Pantalla3.this, Pantalla1.class);
                startActivity(volver);
                finish();
            }
        });

    }
}
