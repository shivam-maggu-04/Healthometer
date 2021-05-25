package com.shivam.healthometer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class dietCategory extends AppCompatActivity {

//    TextView bmiValue;

    Button vegdiet,logout,calculator,nonvegdiet,exercises;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_category);

        getSupportActionBar().hide();
        auth =FirebaseAuth.getInstance();

        logout = findViewById(R.id.btnlogout);
        calculator = findViewById(R.id.btncal);

        vegdiet =  findViewById(R.id.btnveg);
        nonvegdiet = findViewById(R.id.btnnonveg);
        exercises = findViewById(R.id.btnexercise);

        vegdiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent veg = new Intent(dietCategory.this,diettype.class);
                veg.putExtra("vegDiet","vegDiet");
                startActivity(veg);
            }
        });

        nonvegdiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nonveg = new Intent(dietCategory.this,diettype1.class);
                nonveg.putExtra("nonvegDiet","nonvegDiet");
                startActivity(nonveg);
            }
        });

        exercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent exe = new Intent(dietCategory.this,exercise.class);
                exe.putExtra("exercise","exercise");
                startActivity(exe);
            }
        });

//        float bmi = getIntent().getFloatExtra("result", Float.parseFloat("0.0f"));
//        bmiValue.setText(String.format("%.2f", bmi));



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    auth.signOut();
                    startActivity(new Intent(dietCategory.this,signIn.class));
            }
        });

        calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dietCategory.this,dashboard.class));
            }
        });


    }
}