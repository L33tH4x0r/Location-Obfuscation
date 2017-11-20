package com.example.firebase;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private Button signInButton;
    private Button forgotButton;

    private EditText emailField;
    private EditText passwordField;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;

    private String emailResponse;
    private String passwordResponse;

    private Uri uriUrl;

    private Intent launchBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

        signInButton = (Button) findViewById(R.id.signInButton);
        forgotButton = (Button) findViewById(R.id.forgotButton);

        emailField = (EditText) findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);

        uriUrl = Uri.parse("http://unr.edu/");
        launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);

        signInButton.setOnClickListener(this);
        forgotButton.setOnClickListener(this);
    }

    public void onClick(View view){
        if(view == signInButton){
            signInUser();
        }
        if (view == forgotButton){
            Intent intent = new Intent(Login.this, ForgotPassword.class);
            startActivity(intent);
        }
    }
    public void signInUser(){
        if (currentUser != null){
            emailResponse = emailField.getText().toString();
            passwordResponse = passwordField.getText().toString();

            if (emailResponse == "")
            {
                Toast.makeText(getApplicationContext(), "Need to enter an email", Toast.LENGTH_SHORT).show();

            }else if (passwordResponse == ""){
                Toast.makeText(getApplicationContext(), "Need to enter a password", Toast.LENGTH_SHORT).show();

            }else {
                firebaseAuth.signInWithEmailAndPassword(emailResponse, passwordResponse)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    currentUser = firebaseAuth.getCurrentUser();
                                    Toast.makeText(getApplicationContext(), "Authentication Success", Toast.LENGTH_SHORT).show();
                                    startActivity(launchBrowser);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Authentication Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }
    }

}
