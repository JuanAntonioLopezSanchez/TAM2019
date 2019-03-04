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
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Calendar;

public class Insertar extends AppCompatActivity {
    private static final int DATE_ID = 0;
    int anio, mes, dia, anioActual, mesActual, diaActual;
    Calendar calendario = Calendar.getInstance();
    Button btnInsertar, btnRegresar;
    EditText txtidpaciente,txtNombre, txtRFC, txtCEL, txtMAIL, txtFECHA;
    BasedeDatos bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);

        txtidpaciente = findViewById(R.id.idpaciente);
        txtNombre = findViewById(R.id.nombre);
        txtRFC = findViewById(R.id.RFC);
        txtCEL = findViewById(R.id.CEL);
        txtMAIL = findViewById(R.id.MAIL);
        txtFECHA = findViewById(R.id.FECHA);
        txtFECHA.setInputType(InputType.TYPE_NULL);
        int id=0;
        anioActual = calendario.get(Calendar.YEAR);
        mesActual = calendario.get(Calendar.MONTH);
        diaActual = calendario.get(Calendar.DAY_OF_MONTH);
        txtFECHA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_ID);
            }
        });

        btnInsertar = findViewById(R.id.btninsertar);
        btnRegresar = findViewById(R.id.btnregresar);

        bd = new BasedeDatos(this,"BASE", null,1);

        try{
            SQLiteDatabase databse = bd.getReadableDatabase();
            String SQL = "SELECT * FROM PACIENTES";
            Cursor c = databse.rawQuery(SQL,null);
            if(c.moveToLast()){
                id = Integer.parseInt(c.getString(0));
                txtidpaciente.setText("ID: "+(id+1));
            }
            else{
                Toast.makeText(this, "No existe registro", Toast.LENGTH_SHORT).show();
            }
        }
        catch (SQLiteException e){
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnInsertarCliente(txtNombre,txtRFC,txtCEL, txtMAIL, txtFECHA);
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void fnInsertarCliente(EditText nom, EditText rfc, EditText cel, EditText mail, EditText fecha) {
        try {
            if(vacio(nom) || vacio(rfc) || vacio(cel) || vacio(mail) || vacio(fecha)){
                Toast.makeText(this, "Uno o mas campos no han sido completados", Toast.LENGTH_SHORT).show();
            }
            else{
                SQLiteDatabase database = bd.getReadableDatabase();
                String SQL = "INSERT INTO PACIENTES VALUES(" +
                        "NULL," +
                        "'"+cad(nom)+"'," +
                        "'"+cad(rfc)+"'," +
                        "'"+cad(cel)+"'," +
                        "'"+cad(mail)+"'," +
                        "'"+cad(fecha)+"')";

                database.execSQL(SQL);
                Toast.makeText(this, "Se inserto correctamente", Toast.LENGTH_SHORT).show();
                Intent regresar = new Intent (Insertar.this, MainActivity.class);
                startActivity(regresar);
            }


        } catch (SQLiteException e) {
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }
    }
    public boolean vacio(EditText edt){
        if(cad(edt).isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }
    public String cad(EditText edt){
        return edt.getText().toString();
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
