package com.example.registercar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private EditText ed1, ed2, ed3, ed4, ed5, ed6, ed7, ed8, ed9, ed10;
    private Button bt1, bt2;

    private static ListaVehiculos lista_autos = new ListaVehiculos();

    @SuppressLint("WrongViewCast")
    public void Enviar (View view){
        ed1 = (EditText) findViewById(R.id.txt_marca);
        ed2 = (EditText) findViewById(R.id.txt_modelo);
        ed3 = (EditText) findViewById(R.id.txt_cilindraje);
        ed4 = (EditText)findViewById(R.id.txt_manejo);
        ed5 = (EditText)findViewById(R.id.txt_color);
        ed6 = (EditText) findViewById(R.id.txt_año);
        ed7 = (EditText) findViewById(R.id.txt_placa);
        ed8 = (EditText) findViewById(R.id.txt_matricula);
        ed9 = (EditText) findViewById(R.id.txt_importacion);
        ed10 = (EditText) findViewById(R.id.txt_precio);


        try {
            lista_autos.Añadir(ed1.getText().toString(), ed2.getText().toString(), ed3.getText().toString(),
                    ed4.getText().toString(), ed5.getText().toString(), ed6.getText().toString(),
                    ed7.getText().toString(), ed8.getText().toString(), ed9.getText().toString(),
                    ed10.getText().toString());
            Clear(ed1, ed2, ed3, ed4, ed5, ed6, ed7, ed8, ed9, ed10);
            Toast.makeText(MainActivity.this, "Registro exitoso",Toast.LENGTH_SHORT).show();
        }

        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(MainActivity.this,"Erorr en "+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void verLista (View view){
        Intent ver = new Intent(MainActivity.this, SegundoActivity.class);
        try {
            if(ListaVehiculos.cars.size()>0){
                startActivity(ver);
            }
            else{
                Toast.makeText(MainActivity.this,"No se puede ver la lista", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(MainActivity.this,"Error en: "+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void Clear(EditText ed1, EditText ed2, EditText ed3, EditText ed4, EditText ed5,
                      EditText ed6, EditText ed7, EditText ed8, EditText ed9, EditText ed10){
        ed1.setText("");
        ed2.setText("");
        ed3.setText("");
        ed4.setText("");
        ed5.setText("");
        ed6.setText("");
        ed7.setText("");
        ed8.setText("");
        ed9.setText("");
        ed10.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_registro);

    }
}