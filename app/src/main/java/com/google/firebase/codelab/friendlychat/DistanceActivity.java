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
    public static final String LAT = "com.google.firebase.codelab.friendlychat.LAT";
    public static final String LONG = "com.google.firebase.codelab.friendlychat.LONG";
    public static final String NAME = "com.google.firebase.codelab.friendlychat.NAME";


    EditText distance_button;
    Button button;
    String dist;
    int distance;
    float p_latitude;
    float p_longitude;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dist);

        Intent intent = getIntent();
        p_latitude = Float.parseFloat(intent.getStringExtra("p_latitude"));
        p_latitude = Float.parseFloat(intent.getStringExtra("p_longitude"));
        name =intent.getStringExtra("name");

        distance_button=(EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DistanceActivity.this, MapsActivity.class);
                dist = distance_button.getText().toString();
                intent.putExtra(DISTANCE, dist);
                intent.putExtra(LAT, Float.toString(p_latitude));
                intent.putExtra(LONG, Float.toString(p_longitude));
                intent.putExtra(NAME, name);

                startActivity(intent);
            }
        });

    }

}
