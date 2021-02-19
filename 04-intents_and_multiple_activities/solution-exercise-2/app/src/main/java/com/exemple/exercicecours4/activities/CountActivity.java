package com.exemple.exercicecours4.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.exemple.exercicecours4.R;
import com.exemple.exercicecours4.models.Counter;
import com.exemple.exercicecours4.models.DataStore;

public class CountActivity extends AppCompatActivity {

    public static final String PREF_COUNTER = "counter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);

        setupCounter();
    }

    private void updateDisplay() {
        Counter counter = DataStore.getInstance().getCounter();
        final TextView clicksView = findViewById(R.id.numberOfClicks);
        clicksView.setText(String.format(getString(R.string.counter_count_format), counter.getCount()));
    }

    private void setupCounter() {
        // initialize model
        Counter counter = DataStore.getInstance().getCounter();

        // restore UI state from SharedPreferences
        int savedNbClicks = getSharedPreferences("ui-state", MODE_PRIVATE).getInt(PREF_COUNTER, 0);
        counter.increment(savedNbClicks);

        // link to UI
        final Button btn = findViewById(R.id.btnCount);
        updateDisplay(); // called at least once to show the latest state

        btn.setOnClickListener((v) -> {
            counter.increment();
            updateDisplay();

            // save the state
            getSharedPreferences("ui-state", MODE_PRIVATE)
                    .edit()
                    .putInt(PREF_COUNTER, counter.getCount())
                    .apply();
        });
    }

}