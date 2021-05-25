package com.shivam.healthometer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class dashboard extends AppCompatActivity{

    ImageView backArrow;
    EditText weight,height;
    TextView result;
    Button calculate;

     float r,w,h,feets;
    float a = (float) 0.305;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        getSupportActionBar().hide();

        weight = findViewById(R.id.etWeight);
        height = findViewById(R.id.etHeight);
        result = findViewById(R.id.tvResult);
        calculate = findViewById(R.id.btnCalculator);
        backArrow = findViewById(R.id.imgbackArrow);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(dashboard.this,dietCategory.class));
            }
        });


        calculate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                w= Float.parseFloat(weight.getText().toString());
                h= Float.parseFloat(height.getText().toString());
                feets =h*a;
                r = w/(feets*feets);


                if (r < 18.5) {
                    result.setText(String.format("%.2f", r)+"\n Under Weight");
                    result.setBackgroundResource(R.color.blue);
                } else if (r >= 18.5 && r < 25) {
                    result.setText(String.format("%.2f", r)+"\n Normal Weight");
                    result.setBackgroundResource(R.color.green);
                } else if (r >= 25 && r < 30)   {
                    result.setText(String.format("%.2f", r)+"\nOver Weight");
                    result.setBackgroundResource(R.color.orange);
                } else {
                    result.setText(String.format("%.2f", r)+"\n Obese");
                    result.setBackgroundResource(R.color.red);
                }
            }
        });



    }
}