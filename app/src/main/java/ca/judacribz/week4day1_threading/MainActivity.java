package ca.judacribz.week4day1_threading;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import ca.judacribz.week4day1_threading.list.ViewCelebrities;
import ca.judacribz.week4day1_threading.multithreading.LooperThread;

import static ca.judacribz.week4day1_threading.multithreading.LooperThread.EXTRA_FIBONACCI;


public class MainActivity extends AppCompatActivity {

    TextView
            tvPiTitle,
            tvPi,
            tvFib;
    double actualPi = Math.PI,
            acceptableDiff = 0.00000000001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPiTitle = findViewById(R.id.tvPiTitle);
        tvPiTitle.setText(String.format(
                Locale.US,
                getString(R.string.estimate_pi),
                actualPi
        ));
        tvPi = findViewById(R.id.tvPi);
        tvFib = findViewById(R.id.tvFib);
    }

    public void calculatePi(View view) {
        tvPi.setText(Algorithms.calculatePi());
    }

    public void getFibonacci(View view) {
        LooperThread looperThread;

        looperThread = new LooperThread(new Handler(getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Bundle bundle = msg.getData();
                tvFib.setText(bundle.getString(EXTRA_FIBONACCI));
            }
        });
        looperThread.start();
        looperThread.getWorkerThreadHandler().sendMessage(new Message());
    }

    public void viewCelebrities(View view) {
        startActivity(new Intent(this, ViewCelebrities.class));
    }
}
