package com.exemple.listexample1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        adapter.addAll("Abricot", "Brugnon", "Cerise", "Datte", "Figue", "Fraise", "Groseille", "Kiwi", "Mandarine", "Melon", "Myrtille", "Noix", "Olive", "Orange", "Pamplemousse", "Pêche", "Poire", "Pomme", "Prune", "Raisin");

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((adapterView, view, position, rowId) -> {
            String fruit = adapter.getItem(position);
            Snackbar.make(view, "Vous avez choisi " + fruit, Snackbar.LENGTH_LONG).show();
        });
    }

}