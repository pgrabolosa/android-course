package com.exemple.exercice1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    BlankFragment frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frag = BlankFragment.newInstance("Toto", "Babar");

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, frag)
                .commit();
    }
}