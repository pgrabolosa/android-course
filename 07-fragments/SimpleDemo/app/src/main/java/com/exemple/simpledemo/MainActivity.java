package com.exemple.simpledemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.addButton).setOnClickListener(this::add);
        findViewById(R.id.popButton).setOnClickListener(this::pop);
        findViewById(R.id.alertButton).setOnClickListener(this::showAlert);
    }

    void add(View view) {
        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.container_view, new DemoFragment())
                .addToBackStack(null)
                .commit();
    }

    void pop(View view) {
        getSupportFragmentManager().popBackStack();
    }

    void showAlert(View view) {
        new DemoDialog().show(getSupportFragmentManager(), "DEMO");
    }
}