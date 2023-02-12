package cat.dam.ivan.sensorgiroscope;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity
{

    //variables del sensor
    private SensorManager sensorManager;
    private Sensor proximitySensor;
    //variables del textview i la imatge
    private TextView textView;
    private ImageView imageView;
    //sensibilitat del sensor al que es restara el valor maxim del sensor
    private static final float SENSOR_SENSITIVITY = 4.5f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //agafem els elements per id
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);

        //inicialitzem el sensor
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //agafem el sensor de proximitat
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    //metode per registrar el sensor
    private SensorEventListener proximitySensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            //creem un objecte de la classe MediaPlayer per reproduir el so
            MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.kame);
            //si el valor del event del sensor es menor que el valor maxim del sensor menys la sensibilitat del sensor
            // llavors se executa el soroll i es canvia el color de fons i el text
            if (event.values[0] < proximitySensor.getMaximumRange() - SENSOR_SENSITIVITY) {

                getWindow().getDecorView().setBackgroundColor(Color.RED);
                textView.setText("MASSA A PROP, ALLUNYA'T");
                textView.setTextSize(30);
                imageView.setImageResource(R.drawable.kameha);
                mp.start(); //si hi ha algun objecte a prop, reproduim el so

            }
            else
            {
                getWindow().getDecorView().setBackgroundColor(Color.CYAN);
                textView.setText("NO HI HA RES A PROP, TOT CORRECTE!");
                textView.setTextSize(30);
                imageView.setImageResource(R.drawable.polzeamunt);

                mp.stop(); //si no hi ha objecte a prop, parem el so
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    /**
     * Funcio sobreescrita per registrar el sensor al iniciar l'activitat amb un delay normal
     */
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(proximitySensorListener, proximitySensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    /**
     * Funcio sobreescrita per desregistrar el sensor al pausar l'activitat
     */
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(proximitySensorListener);
    }
}
