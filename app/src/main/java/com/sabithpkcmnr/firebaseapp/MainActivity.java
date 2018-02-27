package com.sabithpkcmnr.firebaseapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button signUp;
    EditText etEmail, etPassword;
    String myEmail, myPassword;
    FirebaseAuth myAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myAuth = FirebaseAuth.getInstance();

        if (myAuth.getCurrentUser() != null){
            Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(homeIntent);
            finish();
        }

        signUp = findViewById(R.id.myButton);
        etEmail = findViewById(R.id.myEmail);
        etPassword = findViewById(R.id.myPassword);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myEmail = etEmail.getText().toString().trim();
                myPassword = etPassword.getText().toString().trim();
                CreateUser();
            }
        });
    }

    private void CreateUser () {
        myAuth.createUserWithEmailAndPassword(myEmail, myPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(homeIntent);
                    finish();

                } else {
                    Toast.makeText(MainActivity.this, "Error is "+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}