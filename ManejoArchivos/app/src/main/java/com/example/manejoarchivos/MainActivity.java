package com.example.manejoarchivos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText et_titulo, et_contenido;
    private Button btn_guardar, btn_consultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_titulo = (EditText) findViewById(R.id.id_titulo);
        et_contenido = (EditText)findViewById(R.id.txt_manejo_archivo);

        btn_guardar = (Button) findViewById(R.id.btn_guardar);
        btn_consultar = (Button) findViewById(R.id.btn_buscar);

        //metodo o funcion para el boton guardar
        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = et_titulo.getText().toString();
                String cuerpo = et_contenido.getText().toString();
                try {
                    File tarjetaSD = Environment.getExternalStorageDirectory();
                    Toast.makeText(getApplicationContext(), tarjetaSD.getPath(), Toast.LENGTH_SHORT).show();
                    File ruta_archivo = new File(tarjetaSD.getPath(), titulo);
                    OutputStreamWriter crear = new OutputStreamWriter(openFileOutput(titulo, Activity.MODE_PRIVATE));

                    crear.write(cuerpo);
                    crear.flush();
                    crear.close();

                    Toast.makeText(getApplicationContext(), "Archivo guardado correctamente", Toast.LENGTH_SHORT).show();;
                    et_titulo.setText("");
                    et_contenido.setText("");

                }catch (IOException e){
                    Toast.makeText(getApplicationContext(), "No se pudo guardar", Toast.LENGTH_SHORT).show();

                }
            }
        });

        btn_consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = et_titulo.getText().toString();

                try {
                    File tarjetaSD = Environment.getExternalStorageDirectory();
                    File ruta_archivo = new File(tarjetaSD.getPath(), titulo);
                    InputStreamReader abrir = new InputStreamReader(openFileInput(titulo));

                    BufferedReader leer = new BufferedReader(abrir);
                    String linea = leer.readLine();
                    String cuerpo = "";

                    while (linea!= null){
                        cuerpo = cuerpo + linea + "\n";
                        linea = leer.readLine();
                    }
                    leer.close();
                    abrir.close();
                    et_contenido.setText(cuerpo);

                }catch (IOException e){
                    Toast.makeText(getApplicationContext(), "Error al leer el archivo", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}