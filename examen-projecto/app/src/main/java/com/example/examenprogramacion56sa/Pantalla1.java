package com.example.examenprogramacion56sa;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.examenprogramacion56sa.SQLite_DateBase.ConexionBaseDeDatos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class Pantalla1 extends AppCompatActivity {

    private TextInputEditText cod, nombre, stock, valor;
    private TextView gps_text;
    private AutoCompleteTextView area;
    private FloatingActionButton guardar, eliminar, actualizar, buscar;
    private Button limpiar, gps;
    private ConexionBaseDeDatos conn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla1);

        int permissionCheck = ContextCompat.checkSelfPermission(Pantalla1.this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck == PackageManager.PERMISSION_DENIED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(Pantalla1.this, Manifest.permission.ACCESS_FINE_LOCATION)){

            }else {
                ActivityCompat.requestPermissions(Pantalla1.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }

        cod = (TextInputEditText) findViewById(R.id.codigo);
        nombre = (TextInputEditText) findViewById(R.id.producto);
        stock = (TextInputEditText) findViewById(R.id.stock);
        valor = (TextInputEditText) findViewById(R.id.precio);
        gps_text = (TextView)findViewById(R.id.gps_location);
        area = (AutoCompleteTextView) findViewById(R.id.area_producto);

        guardar = (FloatingActionButton) findViewById(R.id.guardar);
        eliminar = (FloatingActionButton) findViewById(R.id.eliminar);
        actualizar = (FloatingActionButton) findViewById(R.id.actualizar);
        buscar = (FloatingActionButton) findViewById(R.id.buscar);

        limpiar = (Button) findViewById(R.id.limpiar);
        gps = (Button)findViewById(R.id.gps_insert);


        String[] medio = new String[]{
                "Tecnologia e Informatica",
                "Cultura",
                "Entretenimiento"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.item_drop, medio);
        area.setAdapter(adapter);

        conn = new ConexionBaseDeDatos(Pantalla1.this, "DBExamen", null, 1);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = conn.getWritableDatabase();

                int codigo =Integer.parseInt(cod.getText().toString());
                String producto = nombre.getText().toString();
                int invetario = Integer.parseInt(stock.getText().toString());
                double costo = Double.parseDouble(valor.getText().toString());
                String tipo = area.getText().toString();
                String coordenadas = gps_text.getText().toString();

                String SQLinsert = "insert into Registros(codigo, producto, stock, precio, area, ubicacion_coordenadas) values("+codigo+", '"+producto+"', "+invetario+", "+costo+", '"+tipo+"', '"+coordenadas+"')";
                try {
                    if (db != null){
                        db.execSQL(SQLinsert);
                        Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                        cod.setText("");
                        nombre.setText("");
                        stock.setText("");
                        valor.setText("");
                        area.setText("");
                        db.close();
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Error en "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = conn.getWritableDatabase();

                int codigo =Integer.parseInt(cod.getText().toString());

                String SQLdelete = "delete from Registros where codigo = "+codigo+"";

                try {
                    if (db != null){
                        db.execSQL(SQLdelete);
                        Toast.makeText(getApplicationContext(), "Eliminacion exitosa", Toast.LENGTH_SHORT).show();
                        cod.setText("");
                        nombre.setText("");
                        stock.setText("");
                        valor.setText("");
                        area.setText("");
                        gps_text.setText("");
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Error en "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = conn.getWritableDatabase();

                int codigo =Integer.parseInt(cod.getText().toString());
                String producto = nombre.getText().toString();
                int invetario = Integer.parseInt(stock.getText().toString());
                double costo = Double.parseDouble(valor.getText().toString());
                String tipo = area.getText().toString();
                String coordenadas = gps_text.getText().toString();

                String SQLupdate = "update Registros set producto = '"+producto+"', stock = "+invetario+", precio = "+costo+", area = '"+tipo+"', ubicacion_coordenadas = '"+coordenadas+"' where codigo = "+codigo+"";

                try {
                    if (db != null);
                    db.execSQL(SQLupdate);
                    Toast.makeText(getApplicationContext(), "Actualizacion completa", Toast.LENGTH_SHORT).show();
                    nombre.setText("");
                    stock.setText("");
                    valor.setText("");
                    area.setText("");

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Error en "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = conn.getWritableDatabase();
                int codigo =Integer.parseInt(cod.getText().toString());
                String SQLconsulta = "select * from Registros where codigo = "+codigo+"";

                try {
                    if (db != null){
                        Cursor cursor = db.rawQuery(SQLconsulta, null);
                        if (cursor.moveToFirst()){
                            do {
                                String name = cursor.getString(1);
                                nombre.setText(name);
                                String stocket = cursor.getString(2);
                                stock.setText(stocket);
                                String billete = cursor.getString(3);
                                valor.setText(billete);
                                String lugar = cursor.getString(4);
                                area.setText(lugar);
                                String gps1 = cursor.getString(5);
                                gps_text.setText(gps1);
                            }while (cursor.moveToNext());

                        }
                        Toast.makeText(getApplicationContext(), "Busqueda exitosa", Toast.LENGTH_SHORT).show();

                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Error en "+e.toString(), Toast.LENGTH_SHORT).show();

                }
            }
        });

        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cod.setText("");
                nombre.setText("");
                stock.setText("");
                valor.setText("");
                area.setText("");
                gps_text.setText("");
            }
        });

        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationManager locationManager = (LocationManager) Pantalla1.this.getSystemService(Context.LOCATION_SERVICE);
                LocationListener locationListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(@NonNull Location location) {
                        gps_text.setText("Latitud:"+location.getLatitude()+" Longitud:"+location.getLongitude());
                    }
                    public void onStatusChanged(String provider, int status, Bundle extras){}
                    public void onProviderEnabled(String provider){}
                    public void onProviderDisabled(String provider){}
                };

                int permissionCheck = ContextCompat.checkSelfPermission(Pantalla1.this, Manifest.permission.ACCESS_FINE_LOCATION);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            }
        });


    }

    public boolean onCreateOptionsMenu(Menu myMenu){
        getMenuInflater().inflate(R.menu.menu_options, myMenu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem items){
        int id = items.getItemId();

        if (id == R.id.it_optionsClose){
            Intent cerrar = new Intent(Pantalla1.this, Pantalla0.class);
            startActivity(cerrar);
            finish();
            return true;
        }
        else if (id == R.id.it_lista_productos){
            Intent lista = new Intent(Pantalla1.this, Pantalla3.class);
            startActivity(lista);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(items);
    }
}
