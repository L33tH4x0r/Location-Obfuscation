package com.example.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import static com.example.firebase.R.layout.activity_registration;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
}
