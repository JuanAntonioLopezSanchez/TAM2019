package mx.edu.ittepic.enviarubicacion;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button mapa, contacto;
    EditText editText, nNombre, nNumero, mensaje;
    static final int PINK_CONTACT_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mapa=findViewById(R.id.button);
        contacto=findViewById(R.id.contacto);
        editText=findViewById(R.id.ETubicacion);
        mensaje=findViewById(R.id.mensaje);
        nNombre=findViewById(R.id.nombreId);
        nNumero=findViewById(R.id.numeroId);
        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MapsActivity.class);
                startActivityForResult(intent,1);
            }
        });
        contacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarContacto();
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarMensaje();
            }
        });

    }

    private void enviarMensaje() {
        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(nNumero.getText().toString(),null, "TDM#"+editText.getText().toString()+"#"+mensaje.getText().toString(),null,null);
            Toast.makeText(getApplicationContext(), "Mensaje Enviado a "+ nNombre.getText().toString() + ".....", Toast.LENGTH_LONG).show();
            mensaje.setText("");
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), ""+e, Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void seleccionarContacto() {
        Intent seleccionarContactoIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
        seleccionarContactoIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(seleccionarContactoIntent, PINK_CONTACT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==6) {
            String latlong = data.getStringExtra("latLng");
            String[] primero = latlong.split("\\(");
            String parte1 = primero[1];
            String[] segundo = parte1.split("\\)");
            String parte2 = segundo[0];
            editText.setText(parte2);
        }
        if(requestCode == PINK_CONTACT_REQUEST){
            if (resultCode == RESULT_OK){
                Uri uri = data.getData();
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);

                if (cursor.moveToFirst()){
                    int columnaNombre = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                    int columnaNumero = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

                    String nombre = cursor.getString(columnaNombre);
                    String numero = cursor.getString(columnaNumero);

                    nNombre.setText(nombre);
                    nNumero.setText(numero);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
