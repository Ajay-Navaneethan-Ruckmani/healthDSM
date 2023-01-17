package com.example.healthdsm2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.bluetooth.BluetoothAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    private EditText User;
    private EditText Pass;
    private Button Login;

    private TextView textView;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    private Executor executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        User = (EditText)findViewById(R.id.editUser);
        Pass = (EditText)findViewById(R.id.editPass);
        Login = (Button)findViewById(R.id.buttonLogin);


        Login.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         validate(User.getText().toString(), Pass.getText().toString());

                                     }
                                 }
        );
    }

    private void validate(String userName, String userPass){
        Log.e("validate", "before if" + userName);
        Log.e("validate", "before if" + userPass);
        if ((userName.equals("John")) && (userPass.equals("1234"))){
            mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            mEditor = mPreferences.edit();
            mEditor.putString("key", userName);
            mEditor.commit();
            String id = mPreferences.getString("key", "");
            Log.e("validate", "id of sharedpref " + id);

            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);




            Log.e("validate", "inside if");
        }
        else {
//          Login.setEnabled(false);
            Toast.makeText(this, "Username and password do not match ", Toast.LENGTH_LONG).show();
        }
    }
}