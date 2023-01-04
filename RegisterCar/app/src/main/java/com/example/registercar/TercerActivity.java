package com.example.registercar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TercerActivity extends AppCompatActivity {
    private TextView txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8, txt9, txt10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_descripcion);

        txt1 = (TextView) findViewById(R.id.txt_tv1);
        txt2 = (TextView) findViewById(R.id.txt_tv2);
        txt3 = (TextView) findViewById(R.id.txt_tv3);
        txt4 = (TextView) findViewById(R.id.txt_tv4);
        txt5 = (TextView) findViewById(R.id.txt_tv5);
        txt6 = (TextView) findViewById(R.id.txt_tv6);
        txt7 = (TextView) findViewById(R.id.txt_tv7);
        txt8 = (TextView) findViewById(R.id.txt_tv8);
        txt9 = (TextView) findViewById(R.id.txt_tv9);
        txt10 = (TextView) findViewById(R.id.txt_tv10);


        String dt1, dt2, dt3, dt4, dt5, dt6, dt7, dt8, dt9, dt10;
        dt1 = getIntent().getStringExtra("Marca");
        dt2 = getIntent().getStringExtra("Modelo");
        dt3 = getIntent().getStringExtra("Cilindraje");
        dt4 = getIntent().getStringExtra("Manejo");
        dt5 = getIntent().getStringExtra("Color");
        dt6 = getIntent().getStringExtra("Año");
        dt7 = getIntent().getStringExtra("Placa");
        dt8 = getIntent().getStringExtra("Matricula");
        dt9 = getIntent().getStringExtra("Pais");
        dt10 = getIntent().getStringExtra("Precio");

        txt1.setText("Marca: "+dt1);
        txt2.setText("Modelo: "+dt2);
        txt3.setText("Cilindraje: "+dt3);
        txt4.setText("Manejo: "+dt4);
        txt5.setText("Color: "+dt5);
        txt6.setText("Año: "+dt6);
        txt7.setText("Placa: "+dt7);
        txt8.setText("Matricula: "+dt8);
        txt9.setText("Pais: "+dt9);
        txt10.setText("Valor: $"+dt10);
    }

    public void VolverALista(View view){
        Intent aLista = new Intent(this, SegundoActivity.class);
        startActivity(aLista);
    }
}
