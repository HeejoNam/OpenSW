package com.google.firebase.codelab.friendlychat;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.google.checkout.MapsActivity;

public class DistanceActivity extends AppCompatActivity {
    public static final String DISTANCE = "com.google.firebase.codelab.friendlychat.DISTANCE";

    EditText distance_button;
    Button button;
    String dist;
    int distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dist);

        distance_button=(EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DistanceActivity.this, MapsActivity.class);
                dist = distance_button.getText().toString();
                intent.putExtra(DISTANCE, dist);
                startActivity(intent);
            }
        });

    }

}
