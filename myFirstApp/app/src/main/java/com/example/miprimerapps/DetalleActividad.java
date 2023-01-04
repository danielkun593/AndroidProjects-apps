package com.example.miprimerapps;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DetalleActividad  extends Activity {
    private TextView txt_mensaje_texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalleejemplo);

        txt_mensaje_texto = (TextView) findViewById(R.id.txtmensaje);

        String nombre_usuario= getIntent().getStringExtra("nombre_usuario");
        String clave_usuario = getIntent().getStringExtra("clave_usuario");
        boolean radio_ecuador = getIntent().getBooleanExtra("radio_ecuador",false);
        boolean radio_peru = getIntent().getBooleanExtra("radio_peru",false);
        boolean radio_colombia = getIntent().getBooleanExtra("radio_colombia",false);
        String cadena_actividad = getIntent().getStringExtra("activiades");

        String pais="";

        if(radio_ecuador)
        {
            pais="Ecuador";
        }

        if(radio_peru)
        {
            pais="Peru";
        }

        if(radio_colombia)
        {
            pais="Colombia";

        }


        txt_mensaje_texto.setText(" "+nombre_usuario+" :"+clave_usuario +":"+pais+" "+cadena_actividad);

        /*

        pantalla.putExtra("radio_ecuador",radio_ecuador.isChecked());
                pantalla.putExtra("radio_peru",radio_peru.isChecked());
                pantalla.putExtra("radio_colombia",radio_colombia.isChecked());
        */


    }
}
