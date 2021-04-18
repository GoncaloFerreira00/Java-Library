package com.example.firebasetests;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btn_insert;
    private Object num = null;
    FirebaseDatabase mdatabase =  FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_insert = findViewById(R.id.btn_insert);

    }

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseReference myreference = mdatabase.getReference("Numbers").push();
        myreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()) {
                    num = ds.getValue();
                }
                btn_insert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int numbers=0;
                        btn_insert.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                myreference.setValue(Integer.parseInt(num.toString()) + 1);
                            }
                        });
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}