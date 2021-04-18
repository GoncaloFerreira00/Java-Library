package com.example.asynctaskproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnprimos, btntoast;
    private TextView txt;
    private ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnprimos = findViewById(R.id.btn_primos);
        btntoast = findViewById(R.id.btn_toast);
        progressbar = findViewById(R.id.progressBar);
        txt = findViewById(R.id.txt_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        btnprimos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTask task = new MyTask();
                task.execute(100);
            }
        });

        btntoast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Toast", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean ePrimo(int num){
        if(num<2)return false;

        int r, quoc, divisor=2;
        do{
            r= num % divisor;
            quoc = num / divisor;
            divisor++;
        }while (r != 0 && divisor <= quoc);
        return (r != 0 || num == 2)?true:false;
    }

    public class MyTask extends AsyncTask<Integer, Integer, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressbar.setVisibility(View.VISIBLE);
            progressbar.setProgress(0);
            btnprimos.setEnabled(false);
        }

        @Override
        protected String doInBackground(Integer... params) {
            int rate;
            for(int i = 2; i<params[0]; i++){
                if(ePrimo(i)){
                    rate = (i*100)/params[0];
                    publishProgress(i, rate);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            return "Fim";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            txt.setText(String.valueOf(values[0]));
            progressbar.setProgress(values[1]);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            txt.setText(s);
            progressbar.setVisibility(View.INVISIBLE);
            progressbar.setProgress(0);
            btnprimos.setEnabled(true);
        }



    }

}