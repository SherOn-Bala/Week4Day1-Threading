package ca.judacribz.week4day1_threading.multithreading;

import android.os.AsyncTask;

import java.util.Random;

import static java.util.Arrays.sort;

public class RandomNumberTask extends AsyncTask<Void, Void, int[]> {

    private SortedListener sortedListener;

    public interface SortedListener {
        void onRandomNumbersSorted(int[] randomNumbers);
    }

    public RandomNumberTask(SortedListener sortedListener) {
        this.sortedListener = sortedListener;
    }

    @Override
    protected int[] doInBackground(Void... voids) {
        Random random = new Random();
        int[] nums = new int[1000];
        for (int i = 0; i < 1000; i++) {
            nums[i] = random.nextInt(100) + 1;
        }
        sort(nums);

        return nums;
    }

    @Override
    protected void onPostExecute(int[] ints) {
        sortedListener.onRandomNumbersSorted(ints);
    }
}
