package com.example.examenprogramacion56sa.SQLite_DateBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionBaseDeDatos extends SQLiteOpenHelper {

    public static final String SQLCreate = "create table Registros(codigo int primary key, producto text, stock int, precio double, area text, ubicacion_coordenadas text)";
    public static final String SQLCreate2 = "create table Usuario(cedula int primary key, nombre text, celular int, correo text, usuario text, contraseña text)";

    public ConexionBaseDeDatos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLCreate);
        db.execSQL(SQLCreate2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Registros");
        db.execSQL("drop table if exists Usuario");
        db.execSQL(SQLCreate);
    }

    public boolean InicioSession(String user, String pass){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor curso = db.rawQuery("select * from Usuario where usuario = ? and contraseña = ?", new String[]{user, pass});
        if(curso.getCount()>0) return true;
        else return false;
    }

    public Cursor getListaContenido(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("select * from Registros", null);
        return data;
    }
}
