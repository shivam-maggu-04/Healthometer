package com.shivam.healthometer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.shivam.healthometer.Models.Users;

public class signUp extends AppCompatActivity {

    Button account,signup;
    TextView name,email,password;

    private FirebaseAuth auth;
    private FirebaseDatabase database;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().setTitle("SignUp");

        account = findViewById(R.id.btnalreadyacc);
        signup= findViewById(R.id.btnsignUp);
        name= findViewById(R.id.etname);
        email= findViewById(R.id.etemail);
        password= findViewById(R.id.etpassword);

        auth =FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        progressDialog = new ProgressDialog(signUp.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("We're creating your account");


//        Already account go to singIn page
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signUp.this,signIn.class));
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                final String userName = name.getText().toString();
                final String userEmail = email.getText().toString();
                final String userPassword = password.getText().toString();

                auth.createUserWithEmailAndPassword(userEmail,userPassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                progressDialog.dismiss();

                                if(task.isSuccessful())
                                {
                                    Users user = new Users(userName,userEmail,userPassword);
                                    String id = task.getResult().getUser().getUid();
                                    database.getReference().child("Users").child(id).setValue(user);
                                    startActivity(new Intent(signUp.this,dietCategory.class));
                                    Toast.makeText(signUp.this, "User Created Successful", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(signUp.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}