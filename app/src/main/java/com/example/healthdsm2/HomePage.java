package com.example.healthdsm2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    private Button Measurement;
    private Button History;
    private Button Education;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Measurement = (Button)findViewById(R.id.btMeasurement);
        History = (Button)findViewById(R.id.btHistory);
        Education = (Button)findViewById(R.id.btEducation);

        Measurement.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               Intent intent = new Intent(HomePage.this, Measurement.class);
                                               startActivity(intent);

                                           }
                                       }
        );
        History.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Intent intent = new Intent(HomePage.this, History.class);
                                           startActivity(intent);

                                       }
                                   }
        );
    }
}
