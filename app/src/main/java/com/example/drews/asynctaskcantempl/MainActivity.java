package com.example.drews.asynctaskcantempl;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    Button btnStart;
    Button btnCancel;
    TextView txtState;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        btnCancel = findViewById(R.id.btnCancel);
        progressBar = findViewById(R.id.progressBar);
        txtState = findViewById(R.id.txtState);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyAsyncTask().execute();
                Toast.makeText(MainActivity.this, "Task has been started", Toast.LENGTH_SHORT).show();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyAsyncTask().cancel(false);
                Toast.makeText(MainActivity.this, "Task has been stopped", Toast.LENGTH_SHORT).show();
            }
        });

    }



        public class MyAsyncTask extends AsyncTask<Void, Integer, Void> {
            private int progressBarValue = 0;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                progressBar.setProgress(values[0]);
                txtState.setText(values[0] + "%");
                super.onProgressUpdate(values);
            }

            @Override
            protected Void doInBackground(Void... voids) {

                try {
                   for (progressBarValue = 0; progressBarValue < 100 ; progressBarValue++){

                       publishProgress(progressBarValue);
                       TimeUnit.SECONDS.sleep(1);
                       if (isCancelled()) {
                           return null;
                       }

                   }
               } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return null;
            }


        }
    }


