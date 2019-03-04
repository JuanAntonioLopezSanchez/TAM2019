package mx.edu.ittepic.laboratorio_sqlite;


import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsertar,btnConsultar;
    BasedeDatos bd;
    ArrayList<Paciente> listaPa;
    RecyclerView listaPacientes;
    Adaptador adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btnInsertar = findViewById(R.id.btninsertar);
        btnConsultar = findViewById(R.id.btnconsultar);
        listaPacientes = findViewById(R.id.recycler_view);
        listaPacientes.setLayoutManager(new LinearLayoutManager(this));
        listaPa=new ArrayList<>();


        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent insert = new Intent(MainActivity.this,Insertar.class);
                startActivity(insert);
            }
        });

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent select = new Intent(MainActivity.this,Consultar.class);
                startActivity(select);
            }
        });
        listardatos();

    }
    private void listardatos() {
        try {

            listaPa = new ArrayList<>();
            bd = new BasedeDatos(this,"BASE", null,1);
            SQLiteDatabase databse = bd.getReadableDatabase();
            String SQL = "SELECT * FROM PACIENTES";
            Cursor cursor = databse.rawQuery(SQL,null);

            for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
                listaPa.add(new Paciente(cursor.getInt(0), cursor.getString(1)));
            }
            adaptador = new Adaptador(listaPa, getApplicationContext());
            listaPacientes.setAdapter(adaptador);

        }catch (SQLException e) {

        }

    }
}
