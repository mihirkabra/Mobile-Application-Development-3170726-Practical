package com.example.practical10;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView fact;
    EditText number;
    Button submit;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fact = findViewById(R.id.fact);
        fact.setVisibility(View.GONE);
        number = findViewById(R.id.number);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(view -> {
            fact.setVisibility(View.GONE);
            if (number.getText().toString().equals("")) {
                Toast.makeText(this, "Please enter a number.", Toast.LENGTH_SHORT).show();
            } else {
                retrofit = new Retrofit.Builder()
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .baseUrl("http://numbersapi.com/")
                        .build();
                WebService service = retrofit.create(WebService.class);
                Call<String> stringCall = service.getFact(number.getText().toString());
                stringCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                        if (response.isSuccessful()) {
                            String responseString = response.body();
                            fact.setText(responseString);
                            fact.setVisibility(View.VISIBLE);
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                        Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}