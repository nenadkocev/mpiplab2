package kocev.nenad.lab2;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MovieRepository {
    private MovieDao movieDao;

    public MovieRepository(Application application){
        AppDatabase database = AppDatabase.getDatabaseConnection(application.getApplicationContext());
        movieDao = database.MovieDao();
    }

    @SuppressLint("StaticFieldLeak")
    public void insert(final Movie... movies){
        new AsyncTask<Void,Void,Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                movieDao.insert(movies);
                return null;
            }

        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void deleteAll(){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                movieDao.deleteAll();
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public LiveData<List<Movie>> getAllMovies(){
        return movieDao.getAllMovies();
    }
}
