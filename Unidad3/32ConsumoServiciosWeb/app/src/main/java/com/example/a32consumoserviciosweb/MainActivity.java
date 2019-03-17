package com.example.a32consumoserviciosweb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements AsyncResponse {

    ConexionWeb conexionWeb;
    TextView clima1,clima2,climat;
    EditText pelicula;
    Button buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clima1=findViewById(R.id.Clima1);
        clima2=findViewById(R.id.Clima2);
        climat=findViewById(R.id.climat);
        pelicula=findViewById(R.id.editText);
        buscar=findViewById(R.id.button);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CargarClima(pelicula.getText().toString());
            }
        });

    }

    public void CargarClima(String pelicula) {
        try {
            conexionWeb = new ConexionWeb(MainActivity.this);
            //URL direcciopn = new URL("http://api.openweathermap.org/data/2.5/weather?q=Tepic,mx&APPID=5a8ab31167d55aa3d4970b6ea03aa2bd");
            URL direcciopn = new URL("http://www.omdbapi.com/?t="+pelicula+"&apikey=5df61721");
            conexionWeb.execute(direcciopn);
        } catch (MalformedURLException e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void procesarRespuesta(String r) {
        try {

            JSONObject object = new JSONObject(r);

            clima1.setText("Nombre : "+object.getString("Title") + "\nAño: "+object.getString("Year")+"\nDuracion: "+object.getString("Runtime"));

            clima2.setText("\n"+"Director: "+object.getString("Director")+"\nActores:"+object.getString("Actors")+"\nTrama: "+object.getString("Plot")+"\nLenguaje: "+object.getString("Language"));

        }catch (JSONException e){
            System.out.println(""+e);
        }


    }
}
