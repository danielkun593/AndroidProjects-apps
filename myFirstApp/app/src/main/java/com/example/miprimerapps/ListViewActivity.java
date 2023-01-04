package com.example.miprimerapps;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewActivity extends Activity {

    private ListView lista;
    private ListViewPersonaAdapter adaptador_persona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.litsview_layout);

        lista = (ListView) findViewById(R.id.id_lista_pais);

        ArrayList<String> datos=new ArrayList<>();
        datos.add("Jorge Lopez");
        datos.add("Daniel Arce");
        datos.add("Jose Hernandez");
        datos.add("Omar Mendoza");

        adaptador_persona = new ListViewPersonaAdapter(this,datos);
        lista.setAdapter(adaptador_persona);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListViewActivity.this,adapterView.getItemAtPosition(i).toString(),Toast.LENGTH_SHORT).show();

            }
        });
     /*   ArrayList<String> datos=new ArrayList<>();
        datos.add("Ecuador");
        datos.add("Peru");
        datos.add("Colombia");
        datos.add("Argentina");
        datos.add("Venezuela");


        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
        lista.setAdapter(adaptador);*/



    }
}
