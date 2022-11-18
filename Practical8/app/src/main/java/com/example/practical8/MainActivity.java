package com.example.practical8;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText name, city;

    @Override
    protected void onCreate(Bundle readInstanceState) {
        super.onCreate(readInstanceState);
        setContentView(R.layout.activity_main);

        final DatabaseHelper helper = new DatabaseHelper(this);
        name = findViewById(R.id.name);
        city = findViewById(R.id.city);
        button = findViewById(R.id.btn);

        button.setOnClickListener(v -> {
            if (!name.getText().toString().isEmpty() && !city.getText().toString().isEmpty()) {
                if (helper.insert(name.getText().toString(), city.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Not Inserted", Toast.LENGTH_LONG).show();
                }
            } else {
                name.setError("Enter Name");
                city.setError("Enter City");
            }
        });
    }
}