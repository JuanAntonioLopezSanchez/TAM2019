package com.example.a12juegotimer;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tiempoEstable, cronometro;
    Button parar, nuevo;
    CountDownTimer timer;
    double incremento=0.0, contador, numPrincipal = 1.0, numFinal = 3.0, n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tiempoEstable = findViewById(R.id.tiempoEstable);
        cronometro = findViewById(R.id.cronometro);
        parar = findViewById(R.id.parar);
        nuevo = findViewById(R.id.nuevo);


        double numRandom =(Math.random()*(numFinal-numPrincipal)+numPrincipal);

        final DecimalFormat formatoDec = new DecimalFormat("#.#");
        formatoDec.setRoundingMode(RoundingMode.FLOOR);

        n = new Double(formatoDec.format(numRandom));
        tiempoEstable.setText(""+n);

        timer = new CountDownTimer(180000, 400) {
            @Override
            public void onTick(long millisUntilFinished) {
                contador = new Double(formatoDec.format(incremento));
                cronometro.setText(""+contador);
                incremento+=0.1;

                if (contador==3.0){
                    Toast.makeText(MainActivity.this, "Perdiste", Toast.LENGTH_LONG).show();
                    timer.cancel();
                }

            }

            @Override
            public void onFinish() {
                cronometro.setText(""+contador);
            }
        };

        timer.start();

        parar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (n==contador){
                    Toast.makeText(MainActivity.this, "Bien hecho Ganaste", Toast.LENGTH_LONG).show();
                }
                if (contador<n || contador>n){
                    Toast.makeText(MainActivity.this, "Perdiste :(", Toast.LENGTH_LONG).show();
                }
                timer.cancel();
            }
        });

        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevaPantalla = new Intent(MainActivity.this, MainActivity.class);
                startActivity(nuevaPantalla);
            }
        });

    }
}
