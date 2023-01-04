package com.example.cacaomobile2;

import android.content.ContentValues;
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
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cacaomobile2.SQLiteOpenHelper.SQLiteOpenHelper;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;

public class PantallaRegistroUsuario extends AppCompatActivity {

    private byte[] blob_foto;
    private ImageView image;
    private TextInputEditText ed_dni, ed_nombres, ed_cel, ed_correo, ed_usuario, ed_contraseña;
    private AutoCompleteTextView ed_ciudad, ed_genero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_reigstro_usuario);

        image = (ImageView) findViewById(R.id.imageAdd);

        ed_dni = (TextInputEditText)findViewById(R.id.txt_dni);
        ed_nombres = (TextInputEditText)findViewById(R.id.txt_name);
        ed_cel = (TextInputEditText)findViewById(R.id.txt_phone);
        ed_ciudad = (AutoCompleteTextView) findViewById(R.id.txt_state);
        ed_correo = (TextInputEditText)findViewById(R.id.txt_email);
        ed_genero = (AutoCompleteTextView) findViewById(R.id.txt_genero);
        ed_usuario = (TextInputEditText)findViewById(R.id.txt_username);
        ed_contraseña = (TextInputEditText)findViewById(R.id.txt_pasword);

        //Campos estaticos de seleccion para el usuario
        String[] ciudades = new String[]{
                "Baba",
                "Montalvo",
                "Palenque",
                "Puebloviejo",
                "Quevedo",
                "Vinces",
                "Babahoyo",
                "Buena Fe",
                "Mocache",
                "Urdaneta",
                "Valencia",
                "Ventanas"
        };
        String[] genero = new String[]{
                "Femenino",
                "Masculino",
        };

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.item_drop ,ciudades);
        ed_ciudad.setAdapter(adapter1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.item_drop ,genero);
        ed_genero.setAdapter(adapter2);

    }

    //funcion para añadir imagen
    public void AddImageUser(View view){
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
            image.setImageURI(add);
            //compresion de imagen para poder ser almacenada en la base de datos
            try{
                Bitmap bit = Bitmap.createBitmap(image.getWidth(), image.getHeight(), Bitmap.Config.RGB_565);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bit.compress(Bitmap.CompressFormat.JPEG, 0, baos);
                blob_foto = baos.toByteArray();

                Log.i("Imagen", "Imagen"+baos.toByteArray().length);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //Registro de usuario
    public void RegistroUsuario(View view){
        String dni = ed_dni.getText().toString(),
                name = ed_nombres.getText().toString(),
                phone = ed_cel.getText().toString(),
                city = ed_ciudad.getText().toString(),
                email = ed_correo.getText().toString(),
                genero = ed_genero.getText().toString(),
                user = ed_usuario.getText().toString(),
                passsword = ed_contraseña.getText().toString();

        if (dni.length()==0 && name.length() == 0 && phone.length() == 0 && city.length() == 0 &&
                email.length() == 0 && genero.length() == 0 && user.length() == 0 && passsword.length() == 0){
            Toast.makeText(getApplicationContext(), "Debe rellenar todos los campos del registro", Toast.LENGTH_LONG).show();
        }else {
            registarUsuario();
        }
    }

    private void registarUsuario() {
        //conexion con la base de datos
        SQLiteOpenHelper conn = new SQLiteOpenHelper(this, "bd_cacao", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        //ContentValues values = new ContentValues();
        try {
            db.execSQL("INSERT INTO Agricultor(Cedula, Nombre, Telefono, Ciudad, Correo, Genero, Usuario, Contraseña, ImagenAgricultor)"+
                    "VALUES('"+ed_dni.getText().toString()+"', '"+ed_nombres.getText().toString()+"', '"+ed_cel.getText().toString()+"', '"+ed_ciudad.getText().toString()+"',"+
                    "'"+ed_correo.getText().toString()+"', '"+ed_genero.getText().toString()+"', '"+ed_usuario.getText().toString()+"', '"+ed_contraseña.getText().toString()+"', '"+blob_foto+"')");

        }catch (Exception e){
            Log.e("Error en:", " "+e.toString());
        }
        Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
        db.close();

        image.clearFocus();
        ed_dni.setText("");
        ed_nombres.setText("");
        ed_cel.setText("");
        ed_ciudad.setText("");
        ed_correo.setText("");
        ed_genero.setText("");
        ed_usuario.setText("");
        ed_contraseña.setText("");
    }
    //

    //Regresar a la pantalla de inicio de sesion
    public void Return (View view){
        Intent volver = new Intent(this, PantallaLogin.class);
        startActivity(volver);
        finish();
    }
}
