package com.example.miprimerapps;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseDatosActividad extends AppCompatActivity {

    private Button guardar_Datos;
    private Button guardar_Datos_campos;
    private EditText txt_codigo;
    private EditText txt_nombre;

    private Button Actualizar;
    private Button Consultar;
    private Button Eliminar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_datos_layout);

        guardar_Datos = (Button) findViewById(R.id.Guardar_inicio);
        txt_codigo = (EditText) findViewById(R.id.txt_codigo_base);
        txt_nombre = (EditText) findViewById(R.id.txt_nombre_base);
        guardar_Datos_campos = (Button) findViewById(R.id.Guardar_datos);

        Actualizar = (Button) findViewById(R.id.actualizar_datos);
        Consultar = (Button) findViewById(R.id.consultar_datos);
        Eliminar = (Button) findViewById(R.id.eliminar_datos);


        guardar_Datos_campos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrearSqliteHeader crear_base_usuario = new CrearSqliteHeader(BaseDatosActividad.this,"DBUusuarios",null,1);
                SQLiteDatabase base_datos = crear_base_usuario.getWritableDatabase();


                int codigo = Integer.parseInt(txt_codigo.getText().toString());
                String usuario = txt_nombre.getText().toString();

                String Query_insert ="Insert into Usuarios(codigo, nombre) values("+codigo+",'"+usuario+"') ";

                if (base_datos !=null)
                {
                    base_datos.execSQL(Query_insert);
                    Log.i("Inserto","Se inserto exitosamente:"+usuario);
                }

            }
        });

        Consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CrearSqliteHeader bd = new CrearSqliteHeader(BaseDatosActividad.this,"DBUusuarios", null, 1);
                SQLiteDatabase base_datos = bd.getWritableDatabase();

                if (base_datos !=null)
                {
                    String codigo = txt_codigo.getText().toString();

                    String query_consulta =" select codigo, nombre from Usuarios where codigo="+codigo;

                    Cursor c =  base_datos.rawQuery(query_consulta,null);

                    if(c.moveToFirst())
                    {
                        do{
                          String cod = c.getString(0);
                          String nom = c.getString(1);
                        }while(c.moveToNext());
                    }
                }



            }
        });

        Eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrearSqliteHeader bd = new CrearSqliteHeader(BaseDatosActividad.this,"DBUusuarios", null, 1);
                SQLiteDatabase base_datos = bd.getWritableDatabase();

                String cod = txt_codigo.getText().toString();

                String sql_eliminar ="delete from Usuarios where codigo="+cod;

                if(base_datos != null)
                {
                    base_datos.execSQL(sql_eliminar);

                }




            }
        });

        Actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrearSqliteHeader bd = new CrearSqliteHeader(BaseDatosActividad.this,"DBUusuarios", null, 1);
                SQLiteDatabase base_datos = bd.getWritableDatabase();

                //validacion.

                String codigo = txt_codigo.getText().toString();

                String nombre = txt_nombre.getText().toString();

                String Sql_Query_Actualizar ="update Usuarios set nombre='"+nombre+"' where codigo="+codigo;

                if (base_datos != null)
                {
                    base_datos.execSQL(Sql_Query_Actualizar);
                }
                Log.i("Actualizar","Se actualizo el codigo"+codigo);

            }
        });


        guardar_Datos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CrearSqliteHeader crear_base_usuario = new CrearSqliteHeader(BaseDatosActividad.this,"DBUusuarios",null,1);
                SQLiteDatabase base_datos = crear_base_usuario.getWritableDatabase();

                if (base_datos !=null)
                {
                    //Insertar 5 numeros consecutivos.

                    for(int i=1; i<=6; i++)
                    {
                        int codigo = i;
                        String nombre = "usuario:"+i;

                        String Query_insert ="Insert into Usuarios(codigo, nombre) values("+codigo+",'"+nombre+"') ";
                        base_datos.execSQL(Query_insert);
                        Log.i("inserto","numero:"+i);





                    }
                    base_datos.close();





                }


            }
        });
    }
}
