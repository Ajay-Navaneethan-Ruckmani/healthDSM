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

import java.util.concurrent.Executor;

public class MainActivity2 extends AppCompatActivity {

    private TextView textView;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    private Executor executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView = findViewById(R.id.textView);
        executor = ContextCompat.getMainExecutor(this);

        biometricPrompt = new BiometricPrompt(this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                textView.setText("error bio not registered");
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                textView.setText("success");
                Intent i = new Intent(MainActivity2.this, HomePage.class);
                startActivity(i); //jump to this activity

            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                textView.setText("failure");

            }
        });
        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Progrmmer world authentication")
                .setNegativeButtonText("cancel/use pasword ") //can also use as a calling function
                .setConfirmationRequired(false)
                .build();
    }

    public void buttonAuthenticate(View view){
        BiometricManager biometricManager = BiometricManager.from(this);
        if( BiometricManager.BIOMETRIC_SUCCESS == 1){ //checks if biometric is present
            textView.setText(("Biometric is supported. Please wait for authentication"));
            return;
        }
        biometricPrompt.authenticate(promptInfo);
    }
}