package com.example.healthdsm2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class Education extends AppCompatActivity {
    TextView linkTextView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        linkTextView1 = findViewById(R.id.textView1);
        linkTextView1.setMovementMethod(LinkMovementMethod.getInstance());

    }
}