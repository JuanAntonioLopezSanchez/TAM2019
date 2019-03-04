package mx.edu.ittepic.laboratorio_sqlite;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Consultar extends AppCompatActivity {
    Button btnConsultar, btnModificar, btnRegresar;
    EditText Id;
    TextView txtNOMBRE, txtRFC, txtCEL, txtMAIL, txtFECHA;
    BasedeDatos bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);


        Id = findViewById(R.id.id);

        txtNOMBRE = findViewById(R.id.NOMBRE);
        txtRFC = findViewById(R.id.txtRFC);
        txtCEL = findViewById(R.id.CEL);
        txtMAIL = findViewById(R.id.MAIL);
        txtFECHA = findViewById(R.id.FECHA);

        btnConsultar = findViewById(R.id.btnconsultar);
        btnModificar = findViewById(R.id.btnmodificar);
        btnRegresar = findViewById(R.id.btnregresar);

        bd = new BasedeDatos(this,"BASE", null,1);

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConsultarClientes();
            }
        });
        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Modificar = new Intent(Consultar.this,Modificar.class);
                Modificar.putExtra("ID", Id.getText().toString());
                startActivity(Modificar);
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regresar = new Intent(Consultar.this, MainActivity.class);
                startActivity(regresar);
            }
        });
        Id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnModificar.setEnabled(false);
            }
        });
    }

    public void ConsultarClientes(){
        Insertar a = new Insertar();
        try{
            if(a.vacio(Id)){
                Toast.makeText(this, "Introduzca el id del paciente", Toast.LENGTH_SHORT).show();
            }
            else{
                SQLiteDatabase databse = bd.getReadableDatabase();
                String SQL = "SELECT * FROM PACIENTES WHERE IDPACIENTE = "+a.cad(Id)+"";
                Cursor c = databse.rawQuery(SQL,null);
                if(c.moveToFirst()){
                    txtNOMBRE.setText("NOMBRE: "+c.getString(1));
                    txtRFC.setText("RFC: "+c.getString(2));
                    txtCEL.setText("CEL: "+c.getString(3));
                    txtMAIL.setText("MAIL: "+c.getString(4));
                    txtFECHA.setText("FECHA: "+c.getString(5));
                    btnModificar.setEnabled(true);
                }
                else{
                    Toast.makeText(this, "No existe registro", Toast.LENGTH_SHORT).show();
                    btnModificar.setEnabled(false);
                    txtNOMBRE.setText("NOMBRE: ");
                    txtRFC.setText("RFC: ");
                    txtCEL.setText("CEL: ");
                    txtMAIL.setText("MAIL: ");
                    txtFECHA.setText("FECHA: ");
                    btnModificar.setEnabled(false);
                }
            }

        }   catch (SQLiteException e){
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }
    }

}