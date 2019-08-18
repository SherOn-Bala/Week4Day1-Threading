package ca.judacribz.week4day1_threading;

import ca.judacribz.week4day1_threading.multithreading.PiThread;

public class Algorithms {

    static String calculatePi() {
        int threadCount = 4;
        int N = 99999999;
        PiThread[] threads = new PiThread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new PiThread(threadCount, i, N);
            threads[i].start();
        }
        for (int i = 0; i < threadCount; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        double pi = 0;
        for (int i = 0; i < threadCount; i++) {
            pi += threads[i].getSum();
        }
        return String.valueOf(4 * pi);
    }

    public static int fibonacci(int n) {
        int
                num0 = 0,
                num1 = 1;

        if (n <= 0) {
            return num1;
        } else if (n == 1) {
            return num1;
        }

        int temp;
        for (int i = 2; i <= n; i++) {
            temp = num1;
            num1 += num0;
            num0 = temp;
        }

        return num1;
    }
}
