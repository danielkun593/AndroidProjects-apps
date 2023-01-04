package com.example.miprimerapps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class IngresoActividad extends Activity {

    private ImageView  foto;
    private TextView   etiqueta;
    private EditText   usuario;
    private EditText   clave_usuario;
    private Button     btn_aceptar;
    private Spinner   combo_sede;
    private  String univerdidad_sede;

    //declaracion de los radios
    private RadioGroup   grupo_radio;
    private RadioButton  radio_ecuador;
    private RadioButton radio_peru;
    private RadioButton radio_colombia;

    private CheckBox  chk_volley;
    private CheckBox chk_basket;
    private CheckBox chk_futbol;
    private CheckBox chk_tennis;

    private  boolean  chekeado_ecuador;
    private  boolean  chekeado_peru;
    private  boolean  chekeado_colombia;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejemplolayout);

        foto = (ImageView) findViewById(R.id.imagenfigura);
        etiqueta = (TextView) findViewById(R.id.lbl_texto);
        usuario =(EditText) findViewById(R.id.txt_usuario);
        clave_usuario = (EditText) findViewById(R.id.txt_clave);
        btn_aceptar = (Button) findViewById(R.id.btn_aceptar);
        combo_sede = (Spinner) findViewById(R.id.cbx_sede);

        chk_volley = (CheckBox) findViewById(R.id.chekeo1);
        chk_basket = (CheckBox) findViewById(R.id.chekeo2);
        chk_futbol = (CheckBox) findViewById(R.id.chekeo3);
        chk_tennis = (CheckBox) findViewById(R.id.chekeo4);

        grupo_radio = (RadioGroup) findViewById(R.id.grupo1);
        radio_ecuador = (RadioButton) findViewById(R.id.radio1);
        radio_peru =  (RadioButton) findViewById(R.id.radio2);
        radio_colombia = (RadioButton) findViewById(R.id.radio3);

        ArrayAdapter<CharSequence> adaptador= ArrayAdapter.createFromResource(IngresoActividad.this,R.array.sede_universidad, android.R.layout.simple_spinner_dropdown_item  );
        adaptador.setDropDownViewResource(android.R.layout.select_dialog_multichoice);
        combo_sede.setAdapter(adaptador);

        combo_sede.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                univerdidad_sede = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(IngresoActividad.this,"La sede es :"+univerdidad_sede,Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        radio_ecuador.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {
                chekeado_ecuador = ((RadioButton) view).isChecked();

                if(chekeado_ecuador)
                {
                    Toast.makeText(IngresoActividad.this,"Selecciono Ecuador",Toast.LENGTH_SHORT).show();

                }

            }
        });

        radio_peru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chekeado_peru =     ((RadioButton) view).isChecked();

                if(chekeado_peru)
                {
                    Toast.makeText(IngresoActividad.this,"Selecciono Peru",Toast.LENGTH_SHORT).show();

                }

            }
        });

        radio_colombia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                chekeado_colombia =     ((RadioButton) view).isChecked();

                if(chekeado_colombia)
                {
                    Toast.makeText(IngresoActividad.this,"Selecciono Colombia",Toast.LENGTH_SHORT).show();

                }

            }
        });


        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 //kjdkjsdjskjdsd
                /* validacion de caja de texto*/
                if(usuario.getText().toString().isEmpty())
                {
                    Toast.makeText(IngresoActividad.this,"Ingrese el usuario",Toast.LENGTH_LONG).show();
                    usuario.requestFocus();
                    return;


                }

                if(clave_usuario.getText().toString().isEmpty())
                {
                    Toast.makeText(IngresoActividad.this,"ingrese la clave",Toast.LENGTH_SHORT).show();
                    clave_usuario.requestFocus();
                    return;

                }

                if(chk_volley.isChecked()==false && chk_basket.isChecked()==false && chk_futbol.isChecked()==false && chk_tennis.isChecked()==false)
                {
                    Toast.makeText(IngresoActividad.this,"ingrese una Actividad",Toast.LENGTH_SHORT).show();
                        return;
                }

                String concatenar_actividad="";

                if(chk_volley.isChecked())
                {
                    concatenar_actividad = concatenar_actividad +"Volley ";
                }
                if(chk_basket.isChecked())
                {
                    concatenar_actividad = concatenar_actividad +"Basket ";
                }
                if(chk_futbol.isChecked())
                {
                    concatenar_actividad = concatenar_actividad +"Futbol ";
                }
                if(chk_tennis.isChecked())
                {
                    concatenar_actividad = concatenar_actividad +"Tennis ";
                }

               /* Persona p=new Persona("0921906046",usuario.getText().toString(),"Lopez","09934");
                Lista_Personas.lista_datos_persona.add(p);
                */


                Intent pantalla = new Intent(IngresoActividad.this,DetalleActividad.class);
                pantalla.putExtra("nombre_usuario", usuario.getText().toString() );
                pantalla.putExtra("clave_usuario",clave_usuario.getText().toString());
                pantalla.putExtra("radio_ecuador",radio_ecuador.isChecked());
                pantalla.putExtra("radio_peru",radio_peru.isChecked());
                pantalla.putExtra("radio_colombia",radio_colombia.isChecked());
                pantalla.putExtra("activiades",concatenar_actividad);

                startActivity(pantalla);







                Toast.makeText(IngresoActividad.this,"mis Actividad:"+concatenar_actividad,Toast.LENGTH_SHORT).show();

            }
        });

    }
}
