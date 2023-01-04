 package com.example.cacaomobile2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cacaomobile2.SQLiteOpenHelper.SQLiteOpenHelper;
import com.example.cacaomobile2.Utilidad.DataBaseAdaptador;

 public class PantallaListaRegistroCacao extends AppCompatActivity {

    GridView gd_llenar;
    //SQLiteOpenHelper conn;
    DataBaseAdaptador adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantall_listacacao);
        gd_llenar = (GridView)findViewById(R.id.gd_registerCacao);
        adaptador = new DataBaseAdaptador(this);

        /*SimpleCursorAdapter simpleCursorAdapter =  adaptador.populateGridViewFromDB();
        gd_llenar.setAdapter(simpleCursorAdapter);
        gd_llenar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) simpleCursorAdapter.getItem(position);
                String name = cursor.getString(3);
                Toast.makeText(PantallaListaRegistroCacao.this, name, Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    //funcion boton salir
    public void Salir(View view){
        Intent intent = new Intent(this, PantallaOpciones.class);
        startActivity(intent);
        finish();
    }
}
