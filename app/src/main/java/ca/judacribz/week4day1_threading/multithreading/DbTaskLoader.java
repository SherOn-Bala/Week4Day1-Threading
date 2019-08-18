package ca.judacribz.week4day1_threading.multithreading;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.ArrayList;

import ca.judacribz.week4day1_threading.models.Celebrity;
import ca.judacribz.week4day1_threading.models.CelebrityHelper;

public class DbTaskLoader extends AsyncTaskLoader<ArrayList<Celebrity>> {

    public enum CRUD {
        CREATE,
        RETRIEVE,
        UPDATE,
        DELETE
    }

    CRUD crudType;
    String query;
    Object obj;

    public DbTaskLoader(@NonNull Context context, CRUD crudType, @Nullable Object obj) {
        super(context);
        this.query = query;
        this.crudType = crudType;
        this.obj = obj;
    }

    @Nullable
    @Override
    public ArrayList<Celebrity> loadInBackground() {
        ArrayList<Celebrity> celebrities = null;
        CelebrityHelper celebrityHelper = new CelebrityHelper(getContext());

        switch (crudType) {
            case CREATE:
                for (Celebrity celebrity : (ArrayList<Celebrity>) obj) {
                    celebrityHelper.insertCelebrity(celebrity);
                }
                break;
            case RETRIEVE:
                celebrities = celebrityHelper.getAllCelebrities();
                break;
            case UPDATE:
                celebrityHelper.updateCelebrity((Celebrity) obj);
                break;
            case DELETE:
                celebrityHelper.deleteAll();
                break;
        }
        return celebrities;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        forceLoad();
    }
}
