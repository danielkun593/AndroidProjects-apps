package com.example.pedidomedicamentos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class PantallaPedidosActivity extends AppCompatActivity {
    private TextInputEditText nombre, cantidad;
    private AutoCompleteTextView tipo;
    private RadioButton op1, op2, op3;
    private CheckBox ch1, ch2;
    private Button ok, cancelar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_pedidos);

        nombre = (TextInputEditText)findViewById(R.id.id_nombre);
        tipo = (AutoCompleteTextView)findViewById(R.id.id_tipo_medicamento);
        cantidad = (TextInputEditText)findViewById(R.id.id_cantidad);

        op1 = (RadioButton)findViewById(R.id.id_opcion1);
        op2 = (RadioButton)findViewById(R.id.id_opcion2);
        op3 = (RadioButton)findViewById(R.id.id_opcion3);

        ch1 = (CheckBox)findViewById(R.id.id_checkOpcion1);
        ch2 = (CheckBox)findViewById(R.id.id_checkOpcion2);

        ok = (Button)findViewById(R.id.id_ok);
        cancelar = (Button)findViewById(R.id.id_cancelar);

        String[] tipo_medicamento = new String[]{
                "Analgesico",
                "Delirante",
                "Laxante",
                "otro"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, tipo_medicamento);
        tipo.setAdapter(adapter);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent enviar = new Intent(PantallaPedidosActivity.this, PantallaConfirmacionActivity.class);
                    enviar.putExtra("nombre", nombre.getText().toString());
                    enviar.putExtra("tipo", tipo.getText().toString());
                    enviar.putExtra("cantidad", cantidad.getText().toString());

                    //Radiobutton
                    if (op1.isChecked()==true){
                        enviar.putExtra("opcion1", op1.getText().toString());
                    }else if (op2.isChecked() ==true){
                        enviar.putExtra("opcion2", op2.getText().toString());
                    }else {
                        enviar.putExtra("opcion3", op3.getText().toString());
                    }

                    //checkbox
                    if (ch1.isChecked() == true){
                        enviar.putExtra("sucursal1", ch1.getText().toString());
                    }else {
                        enviar.putExtra("sucursal2", ch2.getText().toString());
                    }

                    startActivity(enviar);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Error en "+e.toString(), Toast.LENGTH_LONG).show();
                }

            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
