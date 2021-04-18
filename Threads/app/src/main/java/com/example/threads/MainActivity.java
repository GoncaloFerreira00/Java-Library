package com.example.threads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.time.chrono.ThaiBuddhistEra;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Bravo";
    private Button btnstart, btnstop;
    private Switch ctrlswitch;
    private TextView txt;
    Handler myHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnstart = findViewById(R.id.btn_main_start);
        btnstop = findViewById(R.id.btn_main_stop);
        txt = findViewById(R.id.txt_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnstart.setEnabled(false);
                txt.setText("Start");
//                T thread = new T(10);
//                thread.start();
                tr runnable = new tr(10);
                new Thread(runnable).start();
                Toast.makeText(MainActivity.this, "Start", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void demora(int voltas) {
        for (int i = 0; i < voltas; i++) {
            Log.d(TAG, "I -> " + String.valueOf(i));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class T extends Thread {
        int voltas;

        public T(int voltas) {
            this.voltas = voltas;
        }

        @Override
        public void run() {
            //super.run();
            demora(voltas);
        }
    }

    public class tr implements Runnable {
        int voltas;

        public tr(int voltas) {
            this.voltas = voltas;
        }

        @Override
        public void run() {
            demora(voltas);
            //outra forma
            //Handler myHandler = new Handler(Looper.getMainLooper());
            
            myHandler.post(new Runnable() {
                @Override
                public void run() {
                    txt.setText("Sim");
                    btnstart.setEnabled(true);
                }
            });
        }

    }
}