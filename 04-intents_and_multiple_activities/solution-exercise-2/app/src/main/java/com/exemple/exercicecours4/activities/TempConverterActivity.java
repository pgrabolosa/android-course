package com.exemple.exercicecours4.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.exemple.exercicecours4.R;
import com.exemple.exercicecours4.models.Counter;
import com.exemple.exercicecours4.models.DataStore;
import com.exemple.exercicecours4.models.TemperatureConverter;

import java.util.Locale;

public class TempConverterActivity extends AppCompatActivity {

    public static final String PREF_TEMP_INPUT = "temp.input";
    public static final String PREF_TEMP_OUTPUT = "temp.output";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_converter);

        setupTemperatureConverter();
    }

    private void setupTemperatureConverter() {
        // initialize model
        TemperatureConverter temperatureConverter = DataStore.getInstance().getTemperatureConverter();

        // fetch UI views
        final Button btn = findViewById(R.id.btnConvert);
        final EditText input = findViewById(R.id.celsiusInput);
        final TextView output = findViewById(R.id.fahrenheitOutput);

        // restore UI state from SharedPreferences
        SharedPreferences preferences = getSharedPreferences("ui-state", MODE_PRIVATE);
        input.setText(preferences.getString(PREF_TEMP_INPUT, input.getText().toString()));
        output.setText(preferences.getString(PREF_TEMP_OUTPUT, output.getText().toString()));

        // setup UI interactions
        btn.setOnClickListener((v) -> {
            try {
                float inputValue = Float.parseFloat(input.getText().toString());
                float outputValue = temperatureConverter.convertFromCelsius(inputValue).fahrenheit;

                output.setText(String.format(Locale.getDefault(), getString(R.string.temp_fahreneiht_format), outputValue));
            } catch (Exception e) {
                output.setText(R.string.invalid_input);
            }

            // save the state
            getSharedPreferences("ui-state", MODE_PRIVATE)
                    .edit()
                    .putString(PREF_TEMP_INPUT, input.getText().toString())
                    .putString(PREF_TEMP_OUTPUT, output.getText().toString())
                    .apply();
        });
    }
}