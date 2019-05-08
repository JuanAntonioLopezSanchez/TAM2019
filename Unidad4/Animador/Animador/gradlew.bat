package mx.edu.ittepic.relogcanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

public class RelogActivty extends AppCompatActivity {


    String mensaje,mensaje1,mensaje2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Relog(this));



    }
    public class Relog extends View {
        private  final long TIEMPO_REFRESCO = 20; // 20 o 1000 velocidad de refresco
        private Paint ColorFondoPintura;
        private Paint ManesillaPintura;
        private Paint Letrass;

        public Relog(Context context) {
            super(context);
            init(context, null);
            mensaje="12";

        }

        public Relog(Context context, AttributeSet attrs) {
            super(context, attrs);
            init(context, attrs);
        }

        public Relog(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init(context, attrs);
        }

        private void init(Context context, AttributeSet attrs) {
            ColorFondoPintura = new Paint();
            ColorFondoPintura.setColor(Color.	rgb(107, 50, 180));
            ColorFondoPintura.setAntiAlias(true);
            ManesillaPintura = new Paint();
            ManesillaPintura.setColor(Color.BLACK);
            ManesillaPintura.setAntiAlias(true);
            Letrass=new Paint();
            Letrass.setTextSize(50);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { //codigo por si se cambia de tamaño
            int largo = MeasureSpec.getSize(widthMeasureSpec);
            int alto = MeasureSpec.getSize(heightMeasureSpec);
            int measuredSize = Math.min(largo, alto);
            setMeasuredDimension(measuredSize, measuredSize);
        }
        private Runnable invalidator = new Runnable() {
            @Override
  