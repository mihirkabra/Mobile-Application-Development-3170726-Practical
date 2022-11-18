package com.example.practical1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    CheckBox rememberMe;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        rememberMe = findViewById(R.id.checkbox);

        button = findViewById(R.id.btn);

        button.setOnClickListener(view -> {
            String name, pass;
            name = username.getText().toString();
            pass = password.getText().toString();

            if (name.equals("") || pass.equals("")) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Username: " + name + " Password: " + pass, Toast.LENGTH_SHORT).show();
            }
        });
    }
}