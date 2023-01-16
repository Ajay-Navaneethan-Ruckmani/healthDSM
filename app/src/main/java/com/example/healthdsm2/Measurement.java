package com.example.healthdsm2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Measurement extends AppCompatActivity {
    private EditText MeasurementET;
    private Button SendBT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement);
        MeasurementET = (EditText)findViewById(R.id.etMeasurement);
        SendBT = (Button)findViewById(R.id.btMeasubmit);

    }

    public void OnSend(View view) {

        String Measurement = MeasurementET.getText().toString();
        String type = "send";
        // String type = "receive";
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mPreferences.edit();
        String id = mPreferences.getString("key", "");

        Log.e("OnSend", "inside of OnSend" + type + id + Measurement);
        backgroundWorker BackgroundWorker = new backgroundWorker(this);
        BackgroundWorker.execute(type, id, Measurement);
    }

}

