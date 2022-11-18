package com.example.practical11;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db;
    ArrayList<Student> studentsList;

    EditText editName, editCity;
    Button addButton;
    RecyclerView recyclerView;
    RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.studentName);
        editCity = findViewById(R.id.studentCity);
        addButton = findViewById(R.id.buttonAdd);
        recyclerView = findViewById(R.id.recyclerView);

        studentsList = new ArrayList<>();

        addButton.setOnClickListener(view -> {
            String city = editCity.getText().toString();
            String name = editName.getText().toString();
            if (city.equals("") || name.equals("")) {
                Toast.makeText(this, "Please enter name and city.", Toast.LENGTH_SHORT).show();
            } else {
                create(name, city);
            }
        });
    }

    public void create(String name, String city) {
        db = FirebaseFirestore.getInstance();
        Map<String, Object> student = new HashMap<>();
        student.put("name", name);
        student.put("city", city);
        db.collection("students")
                .add(student)
                .addOnSuccessListener(documentReference -> {
                    Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    read();
                })
                .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));
    }

    public void read() {
        studentsList.clear();
        db = FirebaseFirestore.getInstance();
        db.collection("students").addSnapshotListener((documentSnapshots, e) -> {
            for (DocumentSnapshot doc : documentSnapshots) {
                studentsList.add(new Student(
                        doc.getString("name"),
                        doc.getString("city"),
                        doc.getId()
                ));
            }
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setHasFixedSize(true);
            adapter = new RecyclerAdapter(this, studentsList);
            recyclerView.setAdapter(adapter);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        studentsList.clear();
        read();
    }
}