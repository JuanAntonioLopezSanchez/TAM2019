package mx.edu.ittepic.laboratorio_sqlite;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class Modificar extends AppCompatActivity {

    private static final int DATE_ID = 0;
    int anio, mes, dia, anioActual, mesActual, diaActual;
    Calendar calendario = Calendar.getInstance();
    Button btnActualizar, btnRegresar;
    EditText txtidpaciente,txtNOMBRE, txtRFC, txtCEL, txtMAIL, txtFECHA;
    BasedeDatos bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        txtidpaciente = findViewById(R.id.idpaciente);
        txtNOMBRE = findViewById(R.id.nombre);
        txtRFC = findViewById(R.id.RFC);
        txtCEL = findViewById(R.id.CEL);
        txtMAIL = findViewById(R.id.MAIL);
        txtFECHA = findViewById(R.id.FECHA);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnRegresar = findViewById(R.id.btnregresar);

        txtFECHA.setInputType(InputType.TYPE_NULL);
        anioActual = calendario.get(Calendar.YEAR);
        mesActual = calendario.get(Calendar.MONTH);
        diaActual = calendario.get(Calendar.DAY_OF_MONTH);
        txtFECHA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_ID);
            }
        });


        txtidpaciente.setText(getIntent().getExtras().getString("ID"));


        bd = new BasedeDatos(this,"BASE", null,1);

        try{
            SQLiteDatabase databse = bd.getReadableDatabase();
            String SQL = "SELECT * FROM PACIENTES WHERE IDPACIENTE="+txtidpaciente.getText().toString();
            Cursor c = databse.rawQuery(SQL,null);
            if(c.moveToLast()){
                txtNOMBRE.setText(c.getString(1));
                txtRFC.setText(c.getString(2));
                txtCEL.setText(c.getString(3));
                txtMAIL.setText(c.getString(4));
                txtFECHA.setText(c.getString(5));
            }
            else{
                Toast.makeText(this, "No existe registro", Toast.LENGTH_SHORT).show();
            }
        }
        catch (SQLiteException e){
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Modificar(txtidpaciente,txtNOMBRE,txtRFC,txtCEL,txtMAIL,txtFECHA );
                Intent regresar = new Intent(Modificar.this,Consultar.class);
                startActivity(regresar);

            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regresar = new Intent(Modificar.this,Consultar.class);
                startActivity(regresar);
            }
        });
    }

    private void Modificar(EditText id, EditText nom, EditText rfc, EditText cel, EditText mail, EditText fecha){
        Insertar a = new Insertar();
        try{
            if(a.vacio(nom) || a.vacio(rfc) || a.vacio(cel) || a.vacio(mail) || a.vacio(fecha)){
                Toast.makeText(Modificar.this, "Uno o mas no se han completado", Toast.LENGTH_SHORT).show();
            }
            else{
                if(!idFound(id)){
                    Toast.makeText(this, "El id del paciente no se ha registrado", Toast.LENGTH_SHORT).show();
                }
                else{
                    SQLiteDatabase database = bd.getWritableDatabase();
                    String SQL ="UPDATE PACIENTES " +
                            "SET NOMBRE='"+a.cad(nom)+"', " +
                            "CEL='"+a.cad(cel)+"', " +
                            "MAIL='"+a.cad(mail)+"', " +
                            "FECHA='"+a.cad(fecha)+"'"+
                            "WHERE IDPACIENTE="+a.cad(id);
                    database.execSQL(SQL);
                    Toast.makeText(Modificar.this, "Registro actualizado exitosamente", Toast.LENGTH_SHORT).show();
                }
            }
        }catch (SQLiteException e){
            Toast.makeText(Modificar.this, ""+e, Toast.LENGTH_SHORT).show();
        }
    }

    public boolean idFound(EditText edt){
        try{
            SQLiteDatabase databse = bd.getReadableDatabase();
            String SQL = "SELECT IDPACIENTE FROM PACIENTES WHERE IDPACIENTE="+edt.getText().toString();

            Cursor c = databse.rawQuery(SQL,null);

            if(c.moveToFirst()){
                return true;
            }
            else{
                return false;
            }

        }catch(SQLiteException e){
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    anio = year;
                    mes = monthOfYear;
                    dia = dayOfMonth;
                    txtFECHA.setText(anio + "/" + (mes+1) + "/" + dia);
                }
            };
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_ID:
                return new DatePickerDialog(this, mDateSetListener, anioActual, mesActual, diaActual);
        }
        return null;
    }
}