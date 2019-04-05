package com.example.a33laboratoriowbjson_sinahiruiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recy;
    ArrayList<NumerosA> listaAlumnos;
    String cadena = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recy = findViewById(R.id.recy);
        recy.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listaAlumnos = new ArrayList<NumerosA>();
        meter();
        leer();

    }

    private void leer() {
        for (int i=0; i<5; i++)
        {

        }
    }

    private void meter() {
        String url = "https://api.myjson.com/bins/1hhlni";

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray array = response.getJSONArray("alumnos");
                    for (int pos = 0; pos <= 20; pos++) {
                        JSONObject object = array.getJSONObject(pos);
                        String nombre=object.getString("Name");
                        String numero=object.getString("Nctrl");
                        String calificacion1=object.getString("u1");
                        String calificacion2=object.getString("u2");
                        String calificacion3=object.getString("u3");

                        listaAlumnos.add(new NumerosA(nombre, numero, calificacion1, calificacion2, calificacion3));

                    }

                    Adapter adapter=new Adapter(listaAlumnos);
                    recy.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jor);
    }
}

