package com.example.firebasetests;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowNumbers extends AppCompatActivity {

    private ListView lst;
    private ArrayList<Object> numberslist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_numbers);
        ArrayAdapter<Object> numbersadapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, numberslist);

        lst = findViewById(R.id.lst_shownumbers);
        lst.setAdapter(numbersadapter);
    }
}