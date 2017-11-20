package com.example.firebase;

import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {
    private Button resetButton;

    private EditText emailField;

    private FirebaseAuth auth;

    private String emailResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        auth = FirebaseAuth.getInstance();

        resetButton = (Button) findViewById(R.id.resetButton);
        emailField = (EditText) findViewById(R.id.emailField);

        resetButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == resetButton){
            resetPassword();
        }
    }

    public void resetPassword(){
        emailResponse = emailField.getText().toString();

        auth.sendPasswordResetEmail(emailResponse)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Reset instructions sent", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
