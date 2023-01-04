package com.example.miprimerapps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Principal_Actividad extends Activity {

    //Declarar objetos de los elementos o controles que se van usar o accionar.
    private EditText nota1;
    private EditText nota2;
    private EditText nota3;

    private RadioButton radio_guayaquil;
    private RadioButton radio_milagro;
    private RadioButton radio_naranjal;


    private CheckBox  check_futbol;
    private CheckBox check_gym;
    private CheckBox check_tennis;

    private Spinner materias_globales;
    private Button registrar;
    private Button cancelar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_layout);

        nota1 = (EditText) findViewById(R.id.txt_nota1);
        nota2 =  (EditText) findViewById(R.id.txt_nota2);
        nota3 = (EditText) findViewById(R.id.txt_nota3);
        materias_globales = (Spinner) findViewById(R.id.combo_materias);
        registrar = (Button) findViewById(R.id.btn_registrar);
        cancelar = (Button) findViewById(R.id.btn_cancelar);

        radio_guayaquil = (RadioButton) findViewById(R.id.rd_1);
        radio_milagro = (RadioButton) findViewById(R.id.rd_2);
        radio_naranjal = (RadioButton) findViewById(R.id.rd_3);

        check_futbol =(CheckBox) findViewById(R.id.chk_futbol);
        check_gym =(CheckBox) findViewById(R.id.chk_gym);
        check_tennis =(CheckBox) findViewById(R.id.chk_tennis);

        ArrayList<String> datos = new ArrayList<>();
        datos.add("Fisica");
        datos.add("Quimica");
        datos.add("Programacion 5");
        datos.add("Sistema Operativo");
        datos.add("Ingeniera Software");

        ArrayAdapter<String> adaptador= new ArrayAdapter<String>(this,android.R.layout.select_dialog_multichoice,datos);
        materias_globales.setAdapter(adaptador);

        radio_guayaquil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean cheked =   ((RadioButton) view).isChecked();

                if(cheked)
                {
                    Toast.makeText(Principal_Actividad.this,"Guayaquil",Toast.LENGTH_SHORT).show();

                }
            }
        });

        radio_milagro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked =   ((RadioButton)  view).isChecked();

                if(checked)
                {
                    Toast.makeText(Principal_Actividad.this,"Milagro",Toast.LENGTH_SHORT).show();
                }
            }
        });

        radio_naranjal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked =   ((RadioButton)  view).isChecked();

                if(checked)
                {
                    Toast.makeText(Principal_Actividad.this,"Naranjal",Toast.LENGTH_SHORT).show();
                }
            }
        });


        check_futbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean chekeado =  ((CheckBox) view).isChecked();

                if(chekeado)
                {
                    Toast.makeText(Principal_Actividad.this,"Futbol",Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(Principal_Actividad.this,"Deschekear Futbol",Toast.LENGTH_SHORT).show();

                }

            }
        });


        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(nota1.getText().toString().isEmpty())
                {
                    Toast.makeText(Principal_Actividad.this,"Ingrese Nota 1",Toast.LENGTH_SHORT).show();
                    nota1.requestFocus();
                    return;

                }

                if(nota2.getText().toString().isEmpty())
                {
                    Toast.makeText(Principal_Actividad.this,"Ingrese Nota 2",Toast.LENGTH_SHORT).show();
                    nota2.requestFocus();
                    return;
                }
                if(nota3.getText().toString().isEmpty())
                {
                    Toast.makeText(Principal_Actividad.this,"Ingrese Nota 3",Toast.LENGTH_SHORT).show();
                    nota3.requestFocus();
                    return;
                }

                // validar radio y check box.
                if(radio_guayaquil.isChecked()==false && radio_milagro.isChecked()==false && radio_naranjal.isChecked()==false)
                {
                    Toast.makeText(Principal_Actividad.this,"Ingrese una Sede",Toast.LENGTH_SHORT).show();
                    return;

                }

                if(check_futbol.isChecked()==false && check_gym.isChecked()==false && check_tennis.isChecked()==false)
                {
                    Toast.makeText(Principal_Actividad.this, "Favor Seleccionar una Actividad",Toast.LENGTH_SHORT).show();
                    return;

                }


                Intent Pantalla = new Intent(Principal_Actividad.this, DetalleActividadEstudiante.class);

                Pantalla.putExtra("nota1",nota1.getText());
                Pantalla.putExtra("nota2",nota2.getText());
                Pantalla.putExtra("nota3",nota3.getText());
                Pantalla.putExtra("radio_guayaquil", radio_guayaquil.isChecked());
                Pantalla.putExtra("radio_milagro", radio_milagro.isChecked());
                Pantalla.putExtra("radio_naranjal", radio_naranjal.isChecked());

                startActivity(Pantalla);









            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        /*Primera Forma de Cargar Spinner*/
        /*ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(Principal_Actividad.this, R.array.Listas_Materias, android.R.layout.simple_spinner_dropdown_item);
        adaptador.setDropDownViewResource(android.R.layout.select_dialog_multichoice);
        materias_globales.setAdapter(adaptador);*/

    }
}
