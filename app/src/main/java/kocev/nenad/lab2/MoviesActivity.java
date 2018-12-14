package kocev.nenad.lab2;

import android.arch.lifecycle.LiveData;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private List<Movie> movieList;
    private MovieRepository repository;
    private SearchView searchView;

    private static final String BASE_URL = "http://www.omdbapi.com";
    private static final String API_KEY = "bd67919d";
    private Retrofit retrofit;
    private MoviesAPIService moviesAPIService;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moviesactivity_layout);
        init();
    }

    private void init() {
        repository = new MovieRepository(getApplication());
        recyclerView = findViewById(R.id.recyclerViewSearchActivity);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        movieList = new ArrayList<>();
        adapter = new MovieAdapter(this, movieList);
        recyclerView.setAdapter(adapter);

        LiveData<List<Movie>> liveData = repository.getAllMovies();
        liveData.observe(MoviesActivity.this, (List<Movie> movieList) -> {
            adapter.update(movieList);
        });

        repository.deleteAll();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        moviesAPIService = retrofit.create(MoviesAPIService.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        searchView = (SearchView)menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchMovies(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

    private void searchMovies(String query) {
        Call<MoviesResponse> call = moviesAPIService.listMovies(API_KEY,query);

        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                List<Movie> movie = response.body().getSearch();

                if(movie != null)
                    movie.forEach(m -> {repository.insert(m);});

            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
