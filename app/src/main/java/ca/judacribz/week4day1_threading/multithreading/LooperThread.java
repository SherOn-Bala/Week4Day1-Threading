package ca.judacribz.week4day1_threading.multithreading;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import ca.judacribz.week4day1_threading.Algorithms;

import static android.os.Looper.loop;
import static android.os.Looper.myLooper;
import static android.os.Looper.prepare;

public class LooperThread extends Thread {

    public static final String EXTRA_FIBONACCI =
            "ca.judacribz.week4day1_threading.EXTRA_FIBONACCI";

    private Handler workerThreadHandler;

    public LooperThread(final Handler handler) {
        super();
        Looper looper = myLooper();
        if (looper != null) {
            workerThreadHandler = new Handler(looper) {
                @Override
                public void handleMessage(Message msg) {
                    Message message = new Message();
                    message.what = msg.what;

                    Bundle bundle = new Bundle();
                    bundle.putString(EXTRA_FIBONACCI, String.valueOf(Algorithms.fibonacci(20)));
                    message.setData(bundle);

                    handler.sendMessage(message);
                }
            };
        }
    }

    @Override
    public void run() {
        super.run();
        prepare();
    }

    public Handler getWorkerThreadHandler() {
        return workerThreadHandler;
    }
}
