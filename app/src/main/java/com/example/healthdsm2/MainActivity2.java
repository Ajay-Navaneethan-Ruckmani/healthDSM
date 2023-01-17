package com.example.healthdsm2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Executor;

public class MainActivity2 extends AppCompatActivity {

    private TextView textView;
    private BiometricPrompt biometric_Prompt;
    private BiometricPrompt.PromptInfo prompt_info;
    private Executor exe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView = findViewById(R.id.textView);
        exe = ContextCompat.getMainExecutor(this);

        biometric_Prompt = new BiometricPrompt(this, exe, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                textView.setText("error biometric not registered or not recognized");
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                textView.setText("success");
                Toast.makeText(MainActivity2.this,"successful biometric authentication ", Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity2.this, HomePage.class);
                startActivity(i); //jump to this activity

            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(MainActivity2.this,"Biometric not recognised", Toast.LENGTH_LONG).show();
                finishAffinity();
                textView.setText("failure");
            }
        });
        prompt_info = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Please use any one biometric to authenticate")
                .setNegativeButtonText("cancel") //can also use as a calling function
                .setConfirmationRequired(false)
                .build();
    }

    public void buttonAuthenticate(View view){
        BiometricManager biometricManager = BiometricManager.from(this);
        if( BiometricManager.BIOMETRIC_SUCCESS == 1){ //checks if biometric is present
            textView.setText(("Biometric is supported. Please wait for authenticator"));
            return;
        }
        biometric_Prompt.authenticate(prompt_info);
    }
}