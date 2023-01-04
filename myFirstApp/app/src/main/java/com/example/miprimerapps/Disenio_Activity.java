package com.example.miprimerapps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Disenio_Activity extends Activity {

    private ImageView  imagen_portada;
    private TextView etiqueta_usuario;
    private TextView etiqueta_clave;
    private EditText ing_usuario;
    private EditText ing_clave;
    private Button boton_aceptar;
    private Button boton_cancelar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.primerdiseno);

        imagen_portada = (ImageView)findViewById(R.id.imagen_1);
        etiqueta_usuario = (TextView) findViewById(R.id.etiqueta_usuario);
        etiqueta_clave = (TextView) findViewById(R.id.etiqueta_clave);
        ing_usuario = (EditText) findViewById(R.id.txt_usuario_ingreso);
        ing_clave = (EditText) findViewById(R.id.txt_clave_ingresa);
        boton_aceptar = (Button) findViewById(R.id.bt_aceptar);
        boton_cancelar = (Button) findViewById(R.id.bt_cancelar);


        boton_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ing_usuario.getText().toString().isEmpty())
                {
                    Toast.makeText(Disenio_Activity.this,"Ingrese el Usuario",Toast.LENGTH_SHORT).show();
                    ing_usuario.requestFocus();
                    return;

                }

                if(ing_clave.getText().toString().isEmpty())
                {
                    Toast.makeText(Disenio_Activity.this,"Ingrese la Clave",Toast.LENGTH_SHORT).show();
                    ing_clave.requestFocus();
                    return;

                }

                Intent pantallas= new Intent(Disenio_Activity.this,Principal_Actividad.class);
                startActivity(pantallas);




            }
        });



    }
}
