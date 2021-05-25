package com.shivam.healthometer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shivam.healthometer.Adapter.mealAdapter;
import com.shivam.healthometer.Models.mealModel;

import java.util.ArrayList;

public class mealPlan extends AppCompatActivity {

    RecyclerView recyclerView;
    private ArrayList<mealModel> list;
    TextView name;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_plan);

         getSupportActionBar().hide();
         back = findViewById(R.id.backArrow);
         name = findViewById(R.id.titles);

         String title = getIntent().getStringExtra("title");
         name.setText(title);

         back.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startActivity(new Intent(mealPlan.this,dietCategory.class));
             }
         });

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        recyclerView = findViewById(R.id.mealtype);
        list= new ArrayList<>();

        final mealAdapter a = new mealAdapter(list,this);
        recyclerView.setAdapter(a);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);




        myRef.child("Items").child(getIntent().getStringExtra("title")).
                addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    list.add(dataSnapshot1.getValue(mealModel.class));
                }
                a.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(mealPlan.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}