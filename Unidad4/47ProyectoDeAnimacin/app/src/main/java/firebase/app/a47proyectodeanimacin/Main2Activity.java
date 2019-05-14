package firebase.app.a47proyectodeanimacin;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity {

    private ImageView imageView;
    private AnimationDrawable blackMen;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageView = findViewById(R.id.imageView);

        if (imageView == null) throw new AssertionError();

        imageView.setBackgroundResource(R.drawable.animation_list);

        mediaPlayer = new MediaPlayer();

        blackMen = (AnimationDrawable) imageView.getBackground();
        blackMen.setOneShot(true);

    }

    public void onStartBtnClick(View v) {

        if (blackMen.isRunning()) {
            blackMen.stop();
        }

        reproducirBotella();
        blackMen.start();
    }

    private void reproducirBotella() {
        mediaPlayer = MediaPlayer.create(Main2Activity.this, R.raw.botella);
        mediaPlayer.start();
    }

    public void onStopBtnClick(View v) {
        blackMen.stop();
        mediaPlayer.stop();
    }
}
