package com.example.practical9;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    String apiUrl = "http://numbersapi.com/";
    TextView fact;
    EditText number;
    ProgressDialog progressDialog;
    Button submit;

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
                NumbersAsyncTask asyncTask = new NumbersAsyncTask(number.getText().toString());
                asyncTask.execute();
            }
        });
    }

    public class NumbersAsyncTask extends AsyncTask<String, String, String> {

        String n;

        public NumbersAsyncTask(String n) {
            this.n = n;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Please Wait");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            String current = "";
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(apiUrl + n);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader isw = new InputStreamReader(in);

                    int data = isw.read();
                    while (data != -1) {
                        current += (char) data;
                        data = isw.read();
                        System.out.print(current);
                    }
                    return current;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("data", s);
            progressDialog.dismiss();
            fact.setText(s);
            fact.setVisibility(View.VISIBLE);
        }
    }
}