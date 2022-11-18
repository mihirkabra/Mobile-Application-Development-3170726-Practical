package com.example.practical11;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditActivity extends AppCompatActivity {

    EditText editName, editCity;
    Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editName = findViewById(R.id.studentName);
        editCity = findViewById(R.id.studentCity);
        editButton = findViewById(R.id.buttonAdd);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String city = intent.getStringExtra("city");
        String id = intent.getStringExtra("id");

        editCity.setText(city);
        editName.setText(name);

        editButton.setOnClickListener(view -> {
            String c = editCity.getText().toString();
            String n = editName.getText().toString();
            if (c.equals("") || n.equals("")) {
                Toast.makeText(this, "Please enter name and city.", Toast.LENGTH_SHORT).show();
            } else {
                update(n, c, id);
                onBackPressed();
            }
        });
    }

    public void update(String name, String city, String id) {
        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("city", city);
        FirebaseFirestore.getInstance().collection("students").document(id).update(data)
                .addOnSuccessListener(aVoid -> Toast.makeText(EditActivity.this, "Data Updated!", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(EditActivity.this, "Error while updating the data : " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }
}