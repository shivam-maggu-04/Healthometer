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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shivam.healthometer.Adapter.categorynameAdapter;
import com.shivam.healthometer.Models.categoryname;

import java.util.ArrayList;

public class exercise extends AppCompatActivity {

    RecyclerView recyclerView;
    private ArrayList<categoryname> list;

    ImageView backarrow;
    TextView category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        getSupportActionBar().hide();

        backarrow = findViewById(R.id.backArrow);
        category = findViewById(R.id.tvcategory);

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(exercise.this, dietCategory.class));
            }
        });

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        recyclerView = findViewById(R.id.category);
        list = new ArrayList<>();

        final categorynameAdapter a = new categorynameAdapter(list, this);
        recyclerView.setAdapter(a);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        String exe = getIntent().getStringExtra("exercise");
        category.setText(exe);
        database.getReference().child(exe).
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            list.add(dataSnapshot1.getValue(categoryname.class));
                        }
                        a.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(exercise.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}