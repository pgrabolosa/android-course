package com.exemple.exercicecours4.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.exemple.exercicecours4.R;
import com.exemple.exercicecours4.models.Counter;
import com.exemple.exercicecours4.models.TemperatureConverter;
import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTemperature = findViewById(R.id.btnTemperature);
        btnTemperature.setOnClickListener((v) -> {
            Intent intent = new Intent(this, TempConverterActivity.class);
            startActivity(intent);
        });

        Button btnCounter = findViewById(R.id.btnCounter);
        btnCounter.setOnClickListener((v) -> {
            Intent intent = new Intent(this, CountActivity.class);
            startActivity(intent);
        });

        Button btnEmail = findViewById(R.id.btnEmail);
        btnEmail.setOnClickListener((v) -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.putExtra(Intent.EXTRA_EMAIL, "pierre.grabolosa@univ-perp.fr");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Hello");
            if (intent.resolveActivity(getPackageManager()) == null) {
                Snackbar.make(findViewById(android.R.id.content), "No Activity found to handle Intent", Snackbar.LENGTH_LONG).show();
            } else {
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = getSharedPreferences("ui-state", MODE_PRIVATE);

        TextView infoLabel = findViewById(R.id.infoLabel);
        infoLabel.setText(
                prefs.getInt(CountActivity.PREF_COUNTER, 0)
                + " / " +
                prefs.getString(TempConverterActivity.PREF_TEMP_OUTPUT, "--")
        );
    }
}