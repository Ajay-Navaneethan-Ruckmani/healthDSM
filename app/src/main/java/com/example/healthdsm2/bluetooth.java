package com.example.healthdsm2;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Set;

public class bluetooth extends AppCompatActivity {

    private BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter == null){
            Button text = findViewById(R.id.source);
            text.setText("Bluetooth not supported by device");
        }
        else{
            Button text = findViewById(R.id.source);
            text.setText("Bluetooth  supported by device. Go to settings->Connections->Bluetooth to pair a device");
        }
    }
}