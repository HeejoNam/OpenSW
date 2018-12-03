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

    TextView distance_button;
    Button button;
    String dist;
    int distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dist);

        distance_button=(TextView) findViewById(R.id.textView3);
        distance_button.setText("0");
        button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DistanceActivity.this, MapsActivity.class);
                dist = "0";
                intent.putExtra(DISTANCE, dist);
                startActivity(intent);
            }
        });

    }

}
