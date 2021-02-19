package com.exemple.exercicecours3.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.exemple.exercicecours3.R;
import com.exemple.exercicecours3.models.Counter;
import com.exemple.exercicecours3.models.TemperatureConverter;

public class MainActivity extends AppCompatActivity {

    Counter counter;
    TemperatureConverter temperatureConverter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counter = new Counter();
        temperatureConverter = new TemperatureConverter();

        setupCounter();
        setupTemperatureConverter();
    }

    void setupCounter() {
        final Button btn = findViewById(R.id.btnCount);
        final TextView clicksView = findViewById(R.id.numberOfClicks);

        btn.setOnClickListener((v) -> {
            counter.increment();
            clicksView.setText("Number of clicks: " + counter.getCount());
        });
    }

    void setupTemperatureConverter() {
        final Button btn = findViewById(R.id.btnConvert);
        final EditText input = findViewById(R.id.celsiusInput);
        final TextView output = findViewById(R.id.fahrenheitOutput);

        btn.setOnClickListener((v) -> {
            try {
                float inputValue = Float.parseFloat(input.getText().toString());
                float outputValue = temperatureConverter.convertFromCelsius(inputValue).fahrenheit;

                output.setText(String.format("%.2f ºF", outputValue));
            } catch (Exception e) {
                output.setText("Invalid input");
            }
        });
    }
}