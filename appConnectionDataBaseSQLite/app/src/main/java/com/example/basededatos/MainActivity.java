package com.example.basededatos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class MainActivity extends AppCompatActivity {
    //view
    private ImageView imagen;
    private EditText name, cel, email, fecha, descripcion;
    private FloatingActionButton button;

    //AcctionBar
    private ActionBar actionBar;
    //permisos de la clase constant
    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int STORAGE_REQUEST_CODE = 101;
    //seleccion de imagen constant
    private static final int IMAGE_PICK_CAMERA_CODE = 102;
    private static final int IMAGE_PICK_GALLERY_CODE = 102;
    //matrices de permisos
    private String[]cameraPermission; //camara y almacenamiento
    private String[] storagePermission; //solo almacenamiento
    //variables (constants datos para guardar)
    private Uri imageUri;
    private String nombre, celular, correo, fechaU, description;

    //db helper
    private CreateSQLiteHelp db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_registro);

        //inicializando coenxion de los elementos
        actionBar = getSupportActionBar();
        //titulo
        actionBar.setTitle("Agregar registro");
        //boton negro
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        imagen = (ImageView)findViewById(R.id.id_imagenusuario);
        name = (EditText)findViewById(R.id.id_nombreusuario);
        cel = (EditText)findViewById(R.id.id_telefonousuario);
        fecha = (EditText)findViewById(R.id.id_fechausuario);
        descripcion = (EditText)findViewById(R.id.id_descripcion);

        button = (FloatingActionButton)findViewById(R.id.id_buttonSave);

        //incializar db
        db = new CreateSQLiteHelp(getApplicationContext(), Constants.DB_NAME, null, Constants.DB_VERSION);

        //inicializando permisos array
        cameraPermission = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //muestra el cuadro de dialogo de seleccion de imagen
                imagePickDialogo();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputData();
            }
        });

    }

    private void inputData() {
        //get data
        nombre = ""+name.getText().toString();
        celular = ""+cel.getText().toString();
        correo = ""+email.getText().toString();
        fechaU = ""+fecha.getText().toString();
        description = ""+descripcion.getText().toString();

        //guardar en la base de datos
        String timestamp = ""+System.currentTimeMillis();
        long id = db.insertRecord(
                ""+name,
                ""+imageUri,
                ""+description,
                ""+celular,
                ""+correo,
                ""+fechaU,
                ""+timestamp,
                ""+timestamp
        );
        Toast.makeText(this, "Registro agregado con ID: "+id, Toast.LENGTH_SHORT).show();
    }

    private void imagePickDialogo() {
        //mostar opciones de dialogo
        String[] options = {"Camara", "Galeria"};
        //dialogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        //titulo
        builder.setTitle("Seleccione imagen");
        //establecer elementos / opciones
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //manejar click
                if (which == 0){
                    //click camara
                    if (!checkCameraPermission()){
                        requestCameraPermission();
                    }
                    else {
                        PickFromCamera();

                    }
                }
                else if (which==1){
                    if (!checkStoragePermission()){
                        requestStoragePermission();
                    }
                    else{
                        //permiso ya otorgado
                        PickFromGallery();
                    }
                }
            }
        });
        //crear y mostrar dialogo
        builder.create().show();


    }

    private void PickFromGallery() {
        //intento de elegir la imagen de la galeria, la imagen se devolvera en el metodo onactivityResult
        Intent gelleryIntent = new Intent(Intent.ACTION_PICK);
        gelleryIntent.setType("image/");
        startActivityForResult(gelleryIntent, IMAGE_PICK_GALLERY_CODE);
    }

    private void PickFromCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "Titulo de la imagen");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Descripcion de la imagen");
        //put imagen uri
        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        //intento de camara
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(cameraIntent, IMAGE_PICK_CAMERA_CODE);
    }

    private boolean checkStoragePermission() {
        //comprobar si el permiso de almacenamietno esta habilitado o no
        boolean result = ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(this, storagePermission, STORAGE_REQUEST_CODE);
    }

    private boolean checkCameraPermission() {
        //verificar si el permiso de la camara esta habilitado o no
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);

        return result && result1;

    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this, cameraPermission, CAMERA_REQUEST_CODE);
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed(); //regrese haciendo click en la barra de accion
        return super.onSupportNavigateUp();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //resultado del permiso permitido / denegado
        switch (requestCode){
            case CAMERA_REQUEST_CODE:{
                if (grantResults.length>0){
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (cameraAccepted && storageAccepted){
                        PickFromCamera();
                    }
                    else {
                        Toast.makeText(this, "Se requiere permisos de camara y almacenamiento", Toast.LENGTH_SHORT).show();
                    }

                }
            }
            break;
            case STORAGE_REQUEST_CODE:{
                if (grantResults.length>0){
                    //si se permite devolver verdadero de lo contrario falso
                    boolean storageAcepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (storageAcepted){
                        //permiso de almacenamiento permitido
                        PickFromCamera();
                    }
                    else{
                        Toast.makeText(this, "Se requiere permisos de almacenamiento", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK){
            if (requestCode == IMAGE_PICK_GALLERY_CODE){
                CropImage.activity(data.getData())
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1,1)
                        .start(this);
            }
            else if (requestCode == IMAGE_PICK_CAMERA_CODE){
                CropImage.activity(imageUri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1,1)
                        .start(this);
            }
            else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK){
                    Uri resultUri = result.getUri();
                    imageUri = resultUri;
                    imagen.setImageURI(resultUri);
                }
                else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                    Exception error = result.getError();
                    Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}