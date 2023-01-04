package com.example.cacaomobile2.Utilidad;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.SimpleCursorAdapter;
import com.example.cacaomobile2.R;
import com.example.cacaomobile2.SQLiteOpenHelper.SQLiteOpenHelper;

public class DataBaseAdaptador {
    SQLiteOpenHelper conn;
    SQLiteDatabase db;
    Context context;

    public DataBaseAdaptador(Context context){
        conn = new SQLiteOpenHelper(context, "bd_cacao", null, 1);
        db = conn.getReadableDatabase();
        this.context = context;

    }
    //metodo para rellenar GridView
    public SimpleCursorAdapter populateGridViewFromDB(){
        String columns[] = {conn.CAMPO_HORA, conn.CAMPO_FECHA_TRATAMIENTO, conn.CAMPO_TIPO_CACAO, conn.CAMPO_DESCRIPCION};
        //Cursor cursor = db.query(conn.TABLA_TRATAMIENTO, columns, null, null, null, null, null, null);
        Cursor cursor = db.rawQuery("select "+conn.CAMPO_HORA+", "+conn.CAMPO_FECHA_TRATAMIENTO+", "+conn.CAMPO_TIPO_CACAO+", "+conn.CAMPO_DESCRIPCION+" from "+conn.TABLA_TRATAMIENTO+"", null);
        String [] fromFieldNames = new String[]{
                conn.CAMPO_HORA, conn.CAMPO_FECHA_TRATAMIENTO, conn.CAMPO_TIPO_CACAO, conn.CAMPO_DESCRIPCION
        };
        int[] toViewsIDs = new int[]{R.id.item_gd_hora, R.id.item_gd_fecha, R.id.item_gd_cacao, R.id.item_gd_descripcion};
        SimpleCursorAdapter contact = new SimpleCursorAdapter(
                context,
                R.layout.item_girdview,
                cursor,
                fromFieldNames,
                toViewsIDs
        );
        return contact;
    }
}
