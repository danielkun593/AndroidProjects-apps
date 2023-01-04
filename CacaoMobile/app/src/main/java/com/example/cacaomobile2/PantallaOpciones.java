package com.example.cacaomobile2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class PantallaOpciones extends AppCompatActivity {

    private MaterialButton btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_opciones);

        btn1 = (MaterialButton) findViewById(R.id.btn_opcion1);
        btn2 = (MaterialButton) findViewById(R.id.btn_opcion2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent opcion1 = new Intent(PantallaOpciones.this, PantallaRegistroCacao.class);
                startActivity(opcion1);
                finish();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent opcion2 = new Intent(PantallaOpciones.this, PantallaListaRegistroCacao.class);
                startActivity(opcion2);
                finish();
            }
        });
    }

    //item opcion menu
    public boolean onCreateOptionsMenu(Menu myMenu){
        getMenuInflater().inflate(R.menu.menu_options, myMenu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem items){
        int id = items.getItemId();

        if (id == R.id.it_optionsClose){
            Intent cerrar_sesion = new Intent(this, PantallaLogin.class);
            startActivity(cerrar_sesion);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(items);
    }
}
