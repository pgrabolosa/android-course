package com.exemple.listexample1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.title1) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                final String fruit = getItem(position);

                TextView title = view.findViewById(R.id.title1);
                TextView subtitle = view.findViewById(R.id.subtitle);
                ImageView imageView = view.findViewById(R.id.imageView);
                Button button = view.findViewById(R.id.button);

                title.setText(fruit);
                subtitle.setText("Exemple de fruit");
                imageView.setImageDrawable(getDrawable(R.drawable.fruits));

                button.setOnClickListener((v) -> {
                    Snackbar.make(view, "Vous avez choisi " + fruit, Snackbar.LENGTH_LONG).show();
                });

                return view;
            }
        };
        adapter.addAll("Abricot", "Brugnon", "Cerise", "Datte", "Figue", "Fraise", "Groseille", "Kiwi", "Mandarine", "Melon", "Myrtille", "Noix", "Olive", "Orange", "Pamplemousse", "Pêche", "Poire", "Pomme", "Prune", "Raisin");

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

}