package com.example.miprimerapps;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MenuActividad extends AppCompatActivity {
    private TextView label_mensaje;
    private ListView lista_menu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        label_mensaje =(TextView) findViewById(R.id.lbl_texto);
        lista_menu = (ListView) findViewById(R.id.lstLista);

        ArrayList<String> lista_MenuA= new ArrayList<>();
        lista_MenuA.add("Opcion1");
        lista_MenuA.add("Opcione2");
        lista_MenuA.add("Opcion3");

        ArrayAdapter<String> lista_adaptador= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,lista_MenuA);

        lista_menu.setAdapter(lista_adaptador);

       // registerForContextMenu(label_mensaje);
        //registerForContextMenu(lista_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_actividad,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.MnOpcion1:
                Toast.makeText(this, "Seleccionaste la opcion1 ", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.MnOpcion2:
                Toast.makeText(this, "Seleccionaste la opcion2 ", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.MnOpcion3:
                Toast.makeText(this, "Seleccionaste la opcion3 ", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.SubMenuOpcion3_1:
                Toast.makeText(this, "Seleccionaste la opcion3.1 ", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.SubMenuOpcion3_2:
                Toast.makeText(this, "Seleccionaste la opcion3.2 ", Toast.LENGTH_SHORT).show();
                return true;




        }



        return super.onOptionsItemSelected(item);
    }
}
