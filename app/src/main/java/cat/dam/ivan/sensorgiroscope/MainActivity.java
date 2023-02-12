package cat.dam.ivan.sensorgiroscope;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    //variable del botó
    private Button btnActivar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paginainicial);

        //agafem el botó per id
        btnActivar = findViewById(R.id.btn_activar);

        //afegim un listener al botó per cambiar de activity
        btnActivar.setOnClickListener(v -> {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        });
    }
}