package com.example.examenprogramacion56sa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.examenprogramacion56sa.SQLite_DateBase.ConexionBaseDeDatos;
import com.google.android.material.textfield.TextInputEditText;

public class Pantalla0 extends AppCompatActivity {
    private ConexionBaseDeDatos conn;
    private TextInputEditText usuario, contraseña;
    private Button iniciar, registrar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla0);

        usuario = (TextInputEditText)findViewById(R.id.txt_user);
        contraseña = (TextInputEditText)findViewById(R.id.txt_pass);

        iniciar = (Button)findViewById(R.id.btn_login);
        registrar = (Button)findViewById(R.id.btn_registrar);

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user, password;
                user = usuario.getText().toString();
                password = contraseña.getText().toString();

                if (user.length() ==0 && password.length() == 0){
                    Toast.makeText(getApplicationContext(), "Rellenar todos los campos", Toast.LENGTH_SHORT).show();
                }
                else if (user.length() ==0){
                    Toast.makeText(getApplicationContext(), "Rellenar campo usuario", Toast.LENGTH_SHORT).show();
                }
                else  if (password.length() == 0){
                    Toast.makeText(getApplicationContext(), "Rellenar campo contraseña", Toast.LENGTH_SHORT).show();
                }
                else{
                    validarLogin();
                }
            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrar = null;
                switch (v.getId()){
                    case R.id.btn_registrar:
                        registrar = new Intent(Pantalla0.this, Pantalla2.class);
                        break;
                }
                if (registrar != null){
                    startActivity(registrar);
                    finish();
                }
            }
        });
    }

    private void validarLogin() {
        conn = new ConexionBaseDeDatos(getApplicationContext(), "DBExamen", null, 1);
        String user, password;
        user = usuario.getText().toString();
        password = contraseña.getText().toString();

        Boolean iniciar = conn.InicioSession(user, password);
        if (iniciar == true){
            Toast.makeText(getApplicationContext(), "Bienvenido "+user, Toast.LENGTH_SHORT).show();
            Intent login = new Intent(this, Pantalla1.class);
            startActivity(login);
            finish();
        }
        else {
            Toast.makeText(getApplicationContext(), "Usuario y/o contraseña incorrecto", Toast.LENGTH_SHORT).show();
        }
    }
}
