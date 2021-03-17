package com.exemple.simplechat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    RequestQueue queue;
    ArrayAdapter<Message> messagesAdapter;
    Timer refreshTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureAdapter();

        queue = Volley.newRequestQueue(this);

        refreshTimer = new Timer();
        refreshTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                fetchAllMessages();
            }
        }, 0, 1000);

        Button send = findViewById(R.id.button);
        send.setOnClickListener(v -> sendMessage());
    }

    void sendMessage() {
        EditText name = findViewById(R.id.nameField);
        EditText body = findViewById(R.id.bodyField);

        JSONObject payload = new JSONObject();
        try {
            payload.put("from", name.getText().toString());
            payload.put("body", body.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest("https://grabolosa.fr/simplechat/", payload, response -> {
            // upon receiving a response refresh the messages
            fetchAllMessages();
        }, this::networkError);
        queue.add(request);
    }

    void configureAdapter() {
        messagesAdapter = new ArrayAdapter<Message>(this, android.R.layout.simple_list_item_2, android.R.id.text1) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                Message item = getItem(position);

                TextView text1 = view.findViewById(android.R.id.text1);
                TextView text2 = view.findViewById(android.R.id.text2);

                text1.setText(item.from);
                text2.setText(item.body);

                return view;
            }
        };

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(messagesAdapter);
    }

    void fetchAllMessages() {
        queue.add(new JsonObjectRequest("https://grabolosa.fr/simplechat/", null, this::fetchedAllMessages, this::networkError));
    }

    void fetchedAllMessages(JSONObject root) {
        try {
            JSONArray messages = root.getJSONArray("messages");

            messagesAdapter.clear();
            for(int i = 0; i < messages.length(); i++) {
                messagesAdapter.add(new Message(messages.getJSONObject(i)));
            }
        } catch(JSONException exception) {
            Log.e("DEMO", exception.toString());
        }
    }

    void networkError(VolleyError error) {
        if (error.networkResponse != null && error.networkResponse.data != null) {
            Log.e("DEMO", error.toString() + "\nResponse =\n" + new String(error.networkResponse.data));
        } else {
            Log.e("DEMO", error.toString());
        }

    }
}