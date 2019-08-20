package ca.judacribz.week4day1_threading.list;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ca.judacribz.week4day1_threading.R;
import ca.judacribz.week4day1_threading.models.Celebrity;
import ca.judacribz.week4day1_threading.multithreading.DbTaskLoader;

import static ca.judacribz.week4day1_threading.multithreading.DbTaskLoader.CRUD.CREATE;
import static ca.judacribz.week4day1_threading.multithreading.DbTaskLoader.CRUD.DELETE;
import static ca.judacribz.week4day1_threading.multithreading.DbTaskLoader.CRUD.RETRIEVE;

public class ViewCelebrities extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<ArrayList<Celebrity>>,
        CelebrityAdapter.ItemClickedListener {

    final int LOADER_ID_DELETE = 1;
    final int LOADER_ID_CREATE = 2;
    final int LOADER_ID_RETRIEVE = 3;
    CelebrityAdapter celebrityAdapter;
    RecyclerView rvCelebrities;
    ArrayList<Celebrity> celebrities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_celebrities);

        rvCelebrities = findViewById(R.id.rvCelebrities);

        celebrities = new ArrayList<>();
        celebrityAdapter = new CelebrityAdapter(this, celebrities);
        rvCelebrities.setLayoutManager(new LinearLayoutManager(this));
        rvCelebrities.setAdapter(celebrityAdapter);

        getSupportLoaderManager().initLoader(LOADER_ID_DELETE, null, this);
        getSupportLoaderManager().initLoader(LOADER_ID_CREATE, null, this);
        getSupportLoaderManager().initLoader(LOADER_ID_RETRIEVE, null, this);
    }

    @NonNull
    @Override
    public Loader<ArrayList<Celebrity>> onCreateLoader(int id, @Nullable Bundle args) {

        switch (id) {
            case LOADER_ID_DELETE:
                return new DbTaskLoader(this, DELETE, null);
            case LOADER_ID_CREATE:
                ArrayList<Celebrity> cs = new ArrayList<>();
                cs.add(new Celebrity("Johsdfn", "Don", 56));
                cs.add(new Celebrity("John", "Don", 53));
                cs.add(new Celebrity("John", "Don", 53));
                cs.add(new Celebrity("John", "Don", 53));
                cs.add(new Celebrity("qwqwe", "Don", 23));
                cs.add(new Celebrity("sdffgf", "Don", 23));
                cs.add(new Celebrity("asdas", "Don", 12));
                cs.add(new Celebrity("fsdf", "Don", 34));

                return new DbTaskLoader(this, CREATE, cs);
            case LOADER_ID_RETRIEVE:
                return new DbTaskLoader(this, RETRIEVE, null);
        }
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Celebrity>> loader, ArrayList<Celebrity> celebrities) {

        Toast.makeText(this, "YOOO", Toast.LENGTH_SHORT).show();
        if (celebrities != null && celebrities.size() != 0) {
            celebrityAdapter.setCelebrityList(celebrities);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Celebrity>> loader) {
    }

    @Override
    public void onItemClicked(Celebrity celebrity) {
    }
}
