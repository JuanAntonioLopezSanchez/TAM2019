package mx.edu.iitepic.a54giroscopio;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView ejex;
    private Sensor mysensor;
    private SensorManager senman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        senman = (SensorManager) getSystemService(SENSOR_SERVICE);
        mysensor=senman.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        ejex = findViewById(R.id.ejex);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x,y,z;
        x = event.values[0];
        y=  event.values[1];
        z = event.values[2];

        ejex.setText("  ");
        ejex.append("\n "+"el valor de x: "+ x +"\n"+ "el valor de y: "+ y +"\n"+"el valor de z: "+ z);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume (){
        super.onResume();
        senman.registerListener(this,mysensor, SensorManager.SENSOR_DELAY_NORMAL );
    }

    @Override
    protected void onPause (){
        super.onPause();
        senman.unregisterListener(this);
    }
}
