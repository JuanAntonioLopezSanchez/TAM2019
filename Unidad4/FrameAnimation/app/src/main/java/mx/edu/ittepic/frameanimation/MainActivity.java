package mx.edu.ittepic.frameanimation;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button incio,pausa;
    ImageView metal;
    AnimationDrawable animador;
    MediaPlayer sonido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        incio=findViewById(R.id.btnRun);
        pausa=findViewById(R.id.btnCancel);
        sonido=MediaPlayer.create(this,R.raw.ntro);
        metal=findViewById(R.id.imagenes);
        if(metal==null)throw new AssertionError();
        metal.setBackgroundResource(R.drawable.metal_animacion);
        metal.setVisibility(View.INVISIBLE);
        animador=(AnimationDrawable)metal.getBackground();

        incio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metal.setVisibility(View.VISIBLE);
                if(animador.isRunning())
                    animador.stop();
                animador.start();
                sonido=MediaPlayer.create(MainActivity.this,R.raw.ntro);
                sonido.start();
            }
        });
        pausa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animador.stop();
                sonido.stop();

            }
        });
    }
}
