package com.example.cacaomobile2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cacaomobile2.SQLiteOpenHelper.SQLiteOpenHelper;
import com.google.android.material.textfield.TextInputEditText;

public class PantallaLogin extends AppCompatActivity {
    private TextInputEditText user, pass;
    SQLiteOpenHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_login);

        user = (TextInputEditText) findViewById(R.id.txt_user);
        pass = (TextInputEditText) findViewById(R.id.txt_pass);

        db = new SQLiteOpenHelper(PantallaLogin.this, "bd_cacao", null, 1);

    }

    //funciones para el registro
    public void Register(View view){
        Intent registrar = null;
        switch (view.getId()){
            case R.id.btn_registrar:
                registrar = new Intent(this, PantallaRegistroUsuario.class);
                break;
        }
        if (registrar != null){
            startActivity(registrar);
            finish();
        }
    }

    //Login inicial de la app (functions)
    public void Login(View view){
        String usuario = user.getText().toString(),
                contraseña = pass.getText().toString();

        if (usuario.length()==0 && contraseña.length()==0){
            Toast.makeText(getApplicationContext(), "Rellenar campos usuario y/o contraseña", Toast.LENGTH_SHORT).show();
        }else if (usuario.length() ==0){
            Toast.makeText(getApplicationContext(), "Por favor ingresar usuario", Toast.LENGTH_SHORT).show();
        }else if (contraseña.length()==0){
            Toast.makeText(getApplicationContext(), "Por favor ingresar contraseña", Toast.LENGTH_SHORT).show();
        }else {
            validarLogin();
        }
    }
    private void validarLogin() {
        //desarrollo de metodos para el login
        db = new SQLiteOpenHelper(PantallaLogin.this, "bd_cacao", null, 1);
        String user1, pass2;
        user1 = user.getText().toString();
        pass2 = pass.getText().toString();

        Boolean InicioSecion = db.InicioSession(user1, pass2);
        if(InicioSecion == true){
            Toast.makeText(getApplicationContext(), "Inicio realizado con exito", Toast.LENGTH_SHORT).show();
            Intent login = new Intent(this, PantallaOpciones.class);
            startActivity(login);
            finish();
        }else {
            Toast.makeText(getApplicationContext(), "Usuario y/o contraseña incorrecto", Toast.LENGTH_SHORT).show();
        }
    }

    //item opcion menu
    public boolean onCreateOptionsMenu(Menu myMenu){
        getMenuInflater().inflate(R.menu.menu_pantalla_inicial, myMenu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem items){
        int id = items.getItemId();

        if (id == R.id.it_optionsCloseApp){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(items);
    }
}
