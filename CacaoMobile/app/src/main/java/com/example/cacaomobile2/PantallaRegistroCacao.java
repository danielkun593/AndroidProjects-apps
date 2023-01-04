package com.example.cacaomobile2;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cacaomobile2.SQLiteOpenHelper.SQLiteOpenHelper;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

public class PantallaRegistroCacao extends AppCompatActivity {

    private byte[] blob_foto;
    private ImageView img;
    private Button btn, btn2, btn3;
    private TextInputEditText txt, txt1, txt6, txt7;
    private AutoCompleteTextView txt2, txt3, txt4, txt5;
    private int dia, mes, anio;
    private int hora, minutos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_registrocacao);

        img = (ImageView) findViewById(R.id.imageCacao);

        btn = (Button)findViewById(R.id.btn_date);
        txt = (TextInputEditText)findViewById(R.id.txt_date);

        btn3 = (Button)findViewById(R.id.btn_time);
        txt7 = (TextInputEditText) findViewById(R.id.txt_time);

        btn2 = (Button) findViewById(R.id.btn_date_sembrio);
        txt1 = (TextInputEditText)findViewById(R.id.txt_date_sembrio);

        txt6 = (TextInputEditText)findViewById(R.id.txt_descripcion);

        txt2 = (AutoCompleteTextView) findViewById(R.id.txt_fertilizante);
        txt3 = (AutoCompleteTextView)findViewById(R.id.txt_tipo_cacao);
        txt4 = (AutoCompleteTextView)findViewById(R.id.txt_riego);
        txt5 = (AutoCompleteTextView)findViewById(R.id.txt_poda);

        //Campos de seleccion para el usuario
        String[] tipo_cacao = new String[]{
                "Cacao CCN-51/Cacao clonado",
                "Cacao NACIONAL",
                "Otro"
        };
        String[] fertilizante = new String[]{
                "Abono organico",
                "Abono sintetico de desarrollo",
                "Fertilizante liquido",
                "Ninguna"
        };
        String[] riego = new String[]{
                "Por gravedad",
                "Por goteo",
                "Por aspersion",
                "Por micro-aspersion",
                "Ninguna"
        };

        String[] poda = new String[]{
                "De formacion ",
                "De mantenimiento",
                "Fito-sanitaria",
                "De Rehabilitacion",
                "Ninguna"
        };

        //adaptadores para las lista de opciones
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.item_drop, tipo_cacao);
        txt3.setAdapter(adapter1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.item_drop, riego);
        txt4.setAdapter(adapter2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, R.layout.item_drop, poda);
        txt5.setAdapter(adapter3);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(this, R.layout.item_drop, fertilizante);
        txt2.setAdapter(adapter4);
    }

    //funcion para la añadir foto
    public void AddImageCacao(View view){
        addImage();
    }
    private void addImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione la aplicacion"), 10);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Uri add = data.getData();
            img.setImageURI(add);

            try{
                Bitmap bitmap = Bitmap.createBitmap(img.getWidth(), img.getHeight(), Bitmap.Config.RGB_565);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 0, baos);//

                blob_foto = baos.toByteArray();
                Log.i("trama", " "+baos.toByteArray().length);

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    //funcion registrar tratamiento de cacao
    public void RegistrarCacao(View view){
        registarTratamientoCacao();
    }

    private void registarTratamientoCacao() {
        SQLiteOpenHelper conn = new SQLiteOpenHelper(PantallaRegistroCacao.this, "bd_cacao", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        try {
            db.execSQL("INSERT INTO Tratamiento(Hora, Fecha_tratamiento, Fecha_siembra, Tipo_cacao, Fertilizante, Riego, Poda, Descripcion, ImagenCacao)" +
                    "VALUES('"+txt7.getText().toString()+"', '"+txt.getText().toString()+"', '"+txt3.getText().toString()+"', '"+txt1.getText().toString()+"', '"+txt2.getText().toString()+"', " +
                    "'"+txt4.getText().toString()+"', '"+txt5.getText().toString()+"', '"+txt6.getText().toString()+"', '"+blob_foto+"')");

            Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
            db.close();

            img.clearFocus();
            txt.setText("");
            txt1.setText("");
            txt2.setText("");
            txt3.setText("");
            txt4.setText("");
            txt5.setText("");
            txt6.setText("");
            txt7.setText("");

        }catch (Exception e){
            Log.e("Error en insertar foto:", "En "+e.toString());
        }
    }

    //funcion para el boton de agregar fecha de registro de tratamiento
    public void Fecha(View view){
        if(view == btn){
            Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            anio = c.get(Calendar.YEAR);

            DatePickerDialog datepick = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    txt.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                }
            }, anio, mes, dia);
            datepick.show();
        }

    }

    //funcion para el boton de agregar hora de registro de tratamiento
    public void Hora(View view){
        if(view == btn3){
            Calendar c = Calendar.getInstance();
            hora = c.get(Calendar.HOUR_OF_DAY);
            minutos = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    txt7.setText(hourOfDay+":"+minute);

                }
            }, hora, minutos, false);
            timePickerDialog.show();
        }

    }

    //funcion para el boton de agregar fecha de registro de siembra
    public void FechaSiembra(View view){
        if(view == btn2){
            Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            anio = c.get(Calendar.YEAR);

            DatePickerDialog datepick = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    txt1.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                }
            }, anio, mes, dia);
            datepick.show();
        }
    }

    //funcion para el boton cancelar
    public void Cancelar (View view){
        Intent intent = new Intent(this, PantallaOpciones.class);
        startActivity(intent);
        finish();
    }
}
