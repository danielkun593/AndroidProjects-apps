package com.example.basededatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CreateSQLiteHelp extends SQLiteOpenHelper{

    public CreateSQLiteHelp(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //crear tabla de la base de datos
        db.execSQL(Constants.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //actualizador de la base de datos

        //descartar la tabla anterior
        db.execSQL("DROP TABLE IF EXISTS "+Constants.TABLE_NAME);
        //crear tabla de nuevo
        onCreate(db);
    }
    public long insertRecord(String name, String image, String bio, String phone, String email, String dob,
                             String addedTime, String updateTime){

        //get database grabable porqe queremos escribir datos
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //la identificacion se insertara  automaticamente  cuado configuremos AUTOICREMENTO EN LA CONSULTA
        //insert datos
        values.put(Constants.C_NAME, name);
        values.put(Constants.C_IMAGEN, image);
        values.put(Constants.C_DESCRIPCION, bio);
        values.put(Constants.C_PHONE, phone);
        values.put(Constants.C_EMAIL, email);
        values.put(Constants.C_FECHA, dob);
        values.put(Constants.C_ADDED_TIMESTAMP, addedTime);
        values.put(Constants.C_UPDATE_TIMESTAMP, updateTime);

        //insertar fila devolvera identificacion del registro guardado
        long id = db.insert(Constants.TABLE_NAME, null, values);
        //cerrar coexion
        db.close();

        //devolver id del registro ingresado
        return id;
    }
}
