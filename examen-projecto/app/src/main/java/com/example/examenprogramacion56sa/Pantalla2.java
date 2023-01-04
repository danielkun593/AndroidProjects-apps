package com.example.examenprogramacion56sa;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.examenprogramacion56sa.SQLite_DateBase.ConexionBaseDeDatos;
import com.google.android.material.textfield.TextInputEditText;

public class Pantalla2 extends AppCompatActivity {
    private TextInputEditText cedula, nombre, celular, correo, usuario, contraseña;
    private Button registrar, cancelar;
    private ConexionBaseDeDatos conn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla2);

        cedula = (TextInputEditText)findViewById(R.id.id_cedula);
        nombre = (TextInputEditText)findViewById(R.id.id_nombre);
        celular = (TextInputEditText)findViewById(R.id.id_celular);
        correo = (TextInputEditText)findViewById(R.id.id_correo);
        usuario = (TextInputEditText)findViewById(R.id.id_usuario);
        contraseña = (TextInputEditText)findViewById(R.id.id_contraseña);

        registrar = (Button)findViewById(R.id.btn_registrarUsusario);
        cancelar = (Button)findViewById(R.id.btn_cancelar);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dni, name, phone, email, user, pass;
                dni= cedula.getText().toString();
                name = nombre.getText().toString();
                phone = celular.getText().toString();
                email = correo.getText().toString();
                user = usuario.getText().toString();
                pass = contraseña.getText().toString();

                if (dni.length() == 0 && name.length() == 0 && phone.length() == 0 && email.length() == 0 && user.length() == 0 && pass.length() == 0){
                    Toast.makeText(getApplicationContext(), "Rellenar todo los campos", Toast.LENGTH_SHORT).show();
                }
                else {
                    registarUsuario();
                }
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volver = new Intent(Pantalla2.this, Pantalla0.class);
                startActivity(volver);
                finish();
            }
        });
    }

    private void registarUsuario() {
        conn = new ConexionBaseDeDatos(getApplicationContext(), "DBExamen", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        int dni = Integer.parseInt(cedula.getText().toString());
        String name = nombre.getText().toString();
        int phone = Integer.parseInt(celular.getText().toString());
        String email = correo.getText().toString();
        String user = usuario.getText().toString();
        String pass = contraseña.getText().toString();

        String SQLinsert = "insert into Usuario(cedula, nombre, celular, correo, usuario, contraseña) values("+dni+", '"+name+"', "+phone+", '"+email+"', '"+user+"', '"+pass+"')";

        try {
            if (db != null){
                db.execSQL(SQLinsert);
                Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                cedula.setText("");
                nombre.setText("");
                celular.setText("");
                correo.setText("");
                usuario.setText("");
                contraseña.setText("");
                db.close();
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error en: "+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
