package com.example.practical14;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText name, password, email;
    Button button;

    public static final String MyPREFERENCES = "MyData";
    public static final String Name = "userName";
    public static final String Password = "userPass";
    public static final String Email = "userEmail";

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);

        button = findViewById(R.id.button);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        button.setOnClickListener(v -> {
            String NAME = name.getText().toString();
            String PASS = password.getText().toString();
            String EMAIL = email.getText().toString();

            if (NAME.equals("") || PASS.equals("") || EMAIL.equals("")) {
                Toast.makeText(this, "Please fill all the fields.", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(Name, NAME);
                editor.putString(Password, PASS);
                editor.putString(Email, EMAIL);

                name.setText("");
                password.setText("");
                email.setText("");

                editor.apply();
                Toast.makeText(MainActivity.this, "Data Saved!", Toast.LENGTH_LONG).show();
            }
        });
    }
}