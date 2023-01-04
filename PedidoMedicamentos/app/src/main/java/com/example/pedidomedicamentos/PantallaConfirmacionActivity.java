package com.example.pedidomedicamentos;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pedidomedicamentos.DataBase.ConexionBaseDeDatos;

public class PantallaConfirmacionActivity extends AppCompatActivity {
    private TextView pedido, medicamento, direccion;
    private Button enviar, cancelar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_envio);

        pedido = (TextView)findViewById(R.id.id_pedidoRealizado);
        medicamento = (TextView)findViewById(R.id.id_mediSolicitado);
        direccion = (TextView)findViewById(R.id.id_direccionEnvio);

        enviar = (Button)findViewById(R.id.id_enviar);
        cancelar = (Button)findViewById(R.id.id_cancelar2);

        String dato1 = getIntent().getStringExtra("nombre");
        String dato2 = getIntent().getStringExtra("tipo");
        String dato3 = getIntent().getStringExtra("cantidad");

        String dato5 = getIntent().getStringExtra("opcion1");
        String dato6 = getIntent().getStringExtra("opcion2");
        String dato7 = getIntent().getStringExtra("opcion3");

        String dato8 = getIntent().getStringExtra("sucursal1");
        String dato9 = getIntent().getStringExtra("sucursal2");

        pedido.setText("Pedidos al distribuidor "+dato5);

        medicamento.setText(dato3+" unidades de "+dato2+" "+dato1);

        direccion.setText(dato8);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConexionBaseDeDatos conn = new ConexionBaseDeDatos(getApplicationContext(), "DB_Examen", null, 2);
                SQLiteDatabase db = conn.getWritableDatabase();
                String sqlInsert = "insert into Medicamentos(pedido, medicamento_solicitado, direccion_envio)values('"+pedido+"', '"+medicamento+"', '"+direccion+"')";
                try {
                    if (db != null){
                        db.execSQL(sqlInsert);
                        Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                        db.close();
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Error en "+e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PantallaConfirmacionActivity.this, PantallaPedidosActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}
