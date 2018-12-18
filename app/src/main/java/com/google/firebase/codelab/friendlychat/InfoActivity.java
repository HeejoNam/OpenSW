package com.google.firebase.codelab.friendlychat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.google.checkout.MapsActivity;

public class InfoActivity extends AppCompatActivity {

    EditText name;
    EditText p_latitude;
    EditText p_longitude;
    Button info_button;
    float platitude;
    float plongitude;
    String pname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        name=(EditText) findViewById(R.id.name);
        p_latitude  =(EditText) findViewById(R.id.latitude);
        p_longitude  =(EditText) findViewById(R.id.longitude);
        info_button = (Button) findViewById(R.id.info_button);

        info_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoActivity.this, DistanceActivity.class);
                pname = name.getText().toString();
                platitude = Float.parseFloat(p_latitude.getText().toString());
                plongitude = Float.parseFloat(p_longitude.getText().toString());

                intent.putExtra("p_longitude", Float.toString(plongitude));
                intent.putExtra("p_latitude", Float.toString(platitude));
                intent.putExtra("name",pname);
                startActivity(intent);
            }
        });

    }
}
