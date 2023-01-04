package com.example.miprimerapps;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CrearSqliteHeader extends SQLiteOpenHelper {

          String SqlCreater ="CREATE TABLE usuarios(codigo INTEGER, nombre TEXT)";

    public  CrearSqliteHeader(Context context, String name, SQLiteDatabase.CursorFactory factory,  int version)
        {
            super(context,name,factory,version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SqlCreater);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL(SqlCreater);

    }
}
