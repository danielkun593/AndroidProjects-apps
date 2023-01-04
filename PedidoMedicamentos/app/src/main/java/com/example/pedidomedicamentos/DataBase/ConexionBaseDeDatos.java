package com.example.pedidomedicamentos.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionBaseDeDatos extends SQLiteOpenHelper {

    public static final String SQLCreate = "create table Medicamentos(pedido text primary key, medicamento_solicitado text, direccion_envio text)";

    public ConexionBaseDeDatos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Medicamentos");
        db.execSQL(SQLCreate);
    }
}
