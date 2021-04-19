package com.example.firebasetests;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowNumbers extends AppCompatActivity {

    private ListView lst;

    private FirebaseDatabase mdata = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_numbers);


        DatabaseReference readRef = mdata.getReference("numbers");
        readRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final ArrayList<String> keyslist = new ArrayList<>();
                final ArrayList<Object> numberslist = new ArrayList<>();
                ArrayAdapter<Object> numbersadapter =
                        new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, numberslist);

                lst = findViewById(R.id.lst_shownumbers);

                for (DataSnapshot ds: snapshot.getChildren()) {
                    keyslist.add(ds.getKey());
                    int num = Integer.parseInt(String.valueOf(ds.getValue()));
                    if(num < 10) numberslist.add("0" + ds.getValue());
                    else numberslist.add(ds.getValue());
                }
                lst.setAdapter(numbersadapter);

                lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ShowNumbers.this);
                        builder.setMessage("Delete number?");
                        builder.setCancelable(true);

                        builder.setPositiveButton(
                                "Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int wich) {
                                        readRef.child(keyslist.get((int) id)).removeValue();
                                    }
                                });

                        builder.setNegativeButton(
                                "No",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alert1 = builder.create();
                        alert1.show();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}