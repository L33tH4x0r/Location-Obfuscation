package com.example.firebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.firebase.R.layout.activity_registration;

import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private  Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignin;
    private Button signInButton;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        signInButton = (Button) findViewById(R.id.signInButton);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        textViewSignin = (TextView) findViewById(R.id.textViewSignin);


        buttonRegister.setOnClickListener(this);
        signInButton.setOnClickListener(this);
    }

    private void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            // email is empty, can't go any further
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();

            // stop function execution
            return;
        }
        if(TextUtils.isEmpty(password)){
            // password is empty, can't go any further
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();

            // stop function execution
            return;
        }
        // if validations are okay
        // show progress dialog while registering user
        Log.w("Testing", "Outside conCompleteListener");
        progressDialog.setMessage("Registering User...");
        progressDialog.show();


        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            //user is successfully Registered and logged in
                            // we'll start the profile activity here
                            // temporary toast display
                            Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                        }
                        else{
                            // Registration attempt failed
                            Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    @Override
    public void onClick(View view){

        if(view==buttonRegister){

            registerUser();
        }

        if(view == signInButton){
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        }
    }

}





/*public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button signInButton;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signInButton = (Button) findViewById(R.id.signIn);
        signInButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                
            }
        });
        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Registration.class);
                startActivity(intent);
            }
        });

    }
    public void onClick(View arg0) {
        Toast.makeText(getApplicationContext(), "Button pressed", Toast.LENGTH_SHORT).show();
    }
}*/
