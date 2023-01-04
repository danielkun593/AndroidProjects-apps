package com.example.cacaomobile2.SQLiteOpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class SQLiteOpenHelper extends android.database.sqlite.SQLiteOpenHelper{

    //TABLA AGRICULTOR
    public static final String TABLA_AGRICULTOR = "Agricultor";
    public static final String CAMPO_CEDULA = "Cedula";
    public static final String CAMPO_NOMBRE = "Nombre";
    public static final String CAMPO_TELEFONO= "Telefono";
    public static final String CAMPO_CIUDAD = "Ciudad";
    public static final String CAMPO_CORREO = "Correo";
    public static final String CAMPO_GENERO = "Genero";
    public static final String CAMPO_USUARIO = "Usuario";
    public static final String CAMPO_CONTRASEÑA = "Contraseña";
    public static final String CAMPO_IMAGEN_AGRICULTOR = "ImagenAgricultor";

    public static final String CREAR_TABLA_AGRICULTOR = "create table "+TABLA_AGRICULTOR+"("+CAMPO_CEDULA+" integer primary key, "+CAMPO_NOMBRE+" text, "+CAMPO_TELEFONO+" text, "+CAMPO_CIUDAD+" text," +
            ""+CAMPO_CORREO+" text, "+CAMPO_GENERO+" text, "+CAMPO_USUARIO+" text, "+CAMPO_CONTRASEÑA+" text, "+CAMPO_IMAGEN_AGRICULTOR+" blob)";


    //TABLA TRATAMIENTO
    public static final String TABLA_TRATAMIENTO = "Tratamiento";
    public static final String CAMPO_HORA = "Hora";
    public static final String CAMPO_FECHA_TRATAMIENTO = "Fecha_tratamiento";
    public static final String CAMPO_FECHA_SIEMBRA = "Fecha_siembra";
    public static final String CAMPO_TIPO_CACAO = "Tipo_cacao";
    public static final String CAMPO_FERTILIZANTE = "Fertilizante";
    public static final String CAMPO_RIEGO = "Riego";
    public static final String CAMPO_PODA = "Poda";
    public static final String CAMPO_DESCRIPCION = "Descripcion";
    public static final String CAMPO_IMAGEN_CACAO = "ImagenCacao";

    public static final String CREAR_TABLA_TRATAMIENTO = "create table "+TABLA_TRATAMIENTO+"("+CAMPO_HORA+" text primary key, "+CAMPO_FECHA_TRATAMIENTO+" text, "+CAMPO_FECHA_SIEMBRA+" text, "+CAMPO_TIPO_CACAO+" text," +
            ""+CAMPO_FERTILIZANTE+" text, "+CAMPO_RIEGO+" text, "+CAMPO_PODA+" text, "+CAMPO_DESCRIPCION+" text, "+CAMPO_IMAGEN_CACAO+" blob)";


    //Conexion a base de datos interna SQLite
    public SQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "bd_cacao", factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_AGRICULTOR);
        db.execSQL(CREAR_TABLA_TRATAMIENTO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Agricultor");
        db.execSQL("drop table if exists Tratamiento");
        onCreate(db);
    }

    public boolean InicioSession(String user2, String pass3){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor curso = db.rawQuery("select * from Agricultor where usuario = ? and contraseña = ?", new String[]{user2, pass3});
        if(curso.getCount()>0) return true;
        else return false;
    }


}
