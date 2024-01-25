package com.example.pinturasbasededatos.Vista;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.example.pinturasbasededatos.Controlador.dbPinturas;
import com.example.pinturasbasededatos.Modelo.Pinturas;
import com.example.pinturasbasededatos.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Pinturas> registro = getFullList();  // aqui guardamos en registro el listado de pinturas que se han ido guardando desde la bd
        // mediante return de getfullList

        RecyclerView recyclerView = findViewById(R.id.RecyclerViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Crea un adaptador y configúralo en el RecyclerView
       PinturasAdapter adapter = new PinturasAdapter(registro, this);
        recyclerView.setAdapter(adapter);

        for (Pinturas p : registro ) {

            Log.d("PMDM", p.toString());
        }
    }


    // en esta funcion creamos un listado de tipo pìnturas, iniciamos la base de datos y hacemos el select para sacar todos los datos de la tabla pintura.
    // luego nos movemos por todas las tablas guardando en variables los datos que nos encontramos y creando un objeto Pintura y añadiendolo al listado
    // que luego pasaremos al adaptador del recicleview
    public List<Pinturas> getFullList(){

        List<Pinturas> listado = new ArrayList<>();

        SQLiteDatabase db = new dbPinturas(this).getReadableDatabase();  // aqui iniciamos la base de datos
        Cursor cursor = db.rawQuery("SELECT * FROM pintura", null);  // aqui hacemos la consulta


        if (cursor.getCount() == 0) {
            Log.e("PMDM", "La base de datos está vacía.");
        } else {
            cursor.moveToFirst();
            do {  // Recorremos el conjunto de registros recibidos en el cursor
                long dataId       = cursor.getLong(cursor.getColumnIndexOrThrow("id"));
                String dataTitulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo"));
                String dataAutor  = cursor.getString(cursor.getColumnIndexOrThrow("autor"));
                int dataAnio      = cursor.getInt(cursor.getColumnIndexOrThrow("anio"));
                Pinturas p = new Pinturas(dataId, dataTitulo, dataAutor, dataAnio);
                listado.add(p); // almacenamos en el List cada pintura recuperada
            } while (cursor.moveToNext());

            cursor.close();
            db.close();
        }

        return listado;

    }
}