package com.exemple.recyclerdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    class FruitViewHolder extends RecyclerView.ViewHolder {
        TextView text1;

        public FruitViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(android.R.id.text1);
        }

        public void setTitle(String text) {
            text1.setText(text);
        }
    }

    class FruitDataSource extends RecyclerView.Adapter {
        List<String> fruits;

        FruitDataSource() {
            fruits = Arrays.asList("Abricot", "Brugnon", "Cerise", "Datte", "Figue", "Fraise", "Groseille", "Kiwi", "Mandarine", "Melon", "Myrtille", "Noix", "Olive", "Orange", "Pamplemousse", "Pêche", "Poire", "Pomme", "Prune", "Raisin");
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, parent, false);
            return new FruitViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            String fruit = fruits.get(position);
            ((FruitViewHolder)holder).setTitle(fruit);
        }

        @Override
        public int getItemCount() {
            return fruits.size();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new FruitDataSource());
    }
}