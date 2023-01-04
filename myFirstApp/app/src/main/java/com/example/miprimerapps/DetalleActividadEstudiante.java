package com.example.miprimerapps;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DetalleActividadEstudiante extends Activity {

    private TextView Mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_actividad_layout);

        Mensaje = (TextView) findViewById(R.id.txt_resulltado_mensaje);

        String Nota_estudiante_1 = getIntent().getStringExtra("nota1");
        String Nota_estudiante_2 = getIntent().getStringExtra("nota2");
        String Nota_estudiante_3 = getIntent().getStringExtra("nota3");

        Boolean guayaquil = getIntent().getBooleanExtra("radio_guayaquil",false);
        boolean milagro = getIntent().getBooleanExtra("radio_milagro",false);
        boolean naranjal = getIntent().getBooleanExtra("radio_naranjal", false);

        String sede="";

        if (guayaquil)
        {
            sede="Guayaquil";
        }
        if (milagro)
        {
            sede="Milagro";
        }
        if (naranjal)
        {
            sede="Naranjal";
        }



        String mensaje_alumno =" El alumno tiene Nota1:"+Nota_estudiante_1+ " Nota 2:"+Nota_estudiante_2+ " Nota 3:"+Nota_estudiante_3 +" se encuentra en la sede de :"+sede;
        Mensaje.setText(mensaje_alumno);





    }
}










