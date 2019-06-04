package mx.edu.ittepic.recibrubicacion;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int LOCATION_REQUEST_CODE = 1;
    private GoogleMap mMap;
    String [] contenido;
    String contenido2;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        boton=findViewById(R.id.button1);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cur = getContentResolver().query(Uri.parse("content://sms/"), null, null, null, null);

                if (cur.moveToFirst()) { /* false = no sms */
                    do {
                        String msgInfo = "";

                        for (int i = 0; i < cur.getColumnCount(); i++) {
                            if (i==13) {
                                msgInfo += "" +  cur.getString(i);
                                contenido = msgInfo.split("#");
                                contenido2=contenido[0];
                                if ("TDM".equals(contenido2)){
                                    crearMarcador(msgInfo);
                                }
                            }
                        }

                        //Toast.makeText(this, contenido2, Toast.LENGTH_SHORT).show();
                    } while (cur.moveToNext());
                }
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Controles UI
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Mostrar diálogo explicativo
            } else {
                // Solicitar permiso
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        LOCATION_REQUEST_CODE);
            }
        }
        Cursor cur = getContentResolver().query(Uri.parse("content://sms/"), null, null, null, null);

        if (cur.moveToFirst()) { /* false = no sms */
            do {
                String msgInfo = "";

                for (int i = 0; i < cur.getColumnCount(); i++) {
                    if (i==13) {
                        msgInfo += "" +  cur.getString(i);
                        contenido = msgInfo.split("#");
                        contenido2=contenido[0];
                        if ("TDM".equals(contenido2)){
                            crearMarcador(msgInfo);
                        }
                    }
                }

                //Toast.makeText(this, contenido2, Toast.LENGTH_SHORT).show();
            } while (cur.moveToNext());
        }
    }

    private void crearMarcador(String cont) {
        String [] datos = cont.split("#");
        String latylong = datos[1];
        String comentario = datos[2];
        String [] localizacion = latylong.split(",");
        Double latitud = Double.parseDouble(localizacion[0]);
        Double longitud = Double.parseDouble(localizacion[1]);
        LatLng ubicacion = new LatLng(latitud,longitud);
        mMap.addMarker(new MarkerOptions().position(ubicacion)
                .title(comentario));
    }

}
