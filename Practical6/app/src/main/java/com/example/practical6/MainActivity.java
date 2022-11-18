package com.example.practical6;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Jackey", "Rajkot", "@drawable/a"));
        students.add(new Student("Rockey", "Gandhinagar", "@drawable/b"));
        students.add(new Student("Happy", "Bharuch", "@drawable/c"));
        students.add(new Student("Rajesh", "Ahmedabad", "@drawable/d"));
        students.add(new Student("Mahesh", "Vadodara", "@drawable/e"));
        students.add(new Student("Siddharth", "Jammnagar", "@drawable/f"));
        students.add(new Student("Harish", "Surat", "@drawable/g"));
        students.add(new Student("Tina", "Gandhinagar", "@drawable/h"));

        adapter = new RecyclerAdapter(this, students);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
}