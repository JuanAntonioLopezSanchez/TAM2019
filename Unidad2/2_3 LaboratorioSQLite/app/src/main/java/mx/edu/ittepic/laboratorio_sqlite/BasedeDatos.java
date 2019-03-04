package mx.edu.ittepic.laboratorio_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BasedeDatos extends SQLiteOpenHelper {
    public BasedeDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE PACIENTES" +
                "(" +
                "IDPACIENTE INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "NOMBRE VARCHAR(200)," +
                "RFC VARCHAR(20)," +
                "CEL VARCHAR(100)," +
                "MAIL VARCHAR(100)," +
                "FECHA VARCHAR(100)" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
