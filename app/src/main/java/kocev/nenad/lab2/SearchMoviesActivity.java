package kocev.nenad.lab2;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchMoviesActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://www.omdbapi.com";
    private static final String API_KEY = "c623d8ce";
    private Retrofit retrofit;
    private MoviesAPIService moviesAPIService;

    private MovieRepository repository;

    private ImageView poster;
    private TextView title;
    private TextView year;
    private TextView plot;
    private TextView genre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movies);


        init();
        handleIntent(getIntent());
    }

    private void init(){

        repository = new MovieRepository(getApplication());

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        moviesAPIService = retrofit.create(MoviesAPIService.class);

        poster = findViewById(R.id.imageViewPicture);
        title = findViewById(R.id.textViewTitle);
        year = findViewById(R.id.textViewYear);
        plot = findViewById(R.id.textViewPlot);
        genre = findViewById(R.id.textViewGenre);
    }

    @Override
    protected void onNewIntent(Intent intent){
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);
            searchMovies(query);
        }
        else if(intent.getSerializableExtra("movie") != null){
            Movie movie = (Movie)intent.getSerializableExtra("movie");
            showSearchedMovie(movie);
        }
    }

    private void searchMovies(String query) {
        Call<Movie> call = moviesAPIService.listMovies(API_KEY,"short",query);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movie = response.body();
                if(movie == null)
                    //napraj nesto
                    return;
                repository.insert(movie);
                showSearchedMovie(movie);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showSearchedMovie(Movie movie){
        Picasso.get().load(movie.getPoster()).into(poster);
        title.setText(movie.getTitle());
        genre.setText(movie.getGenre());
        plot.setText(movie.getPlot());
        year.setText(movie.getYear());
    }
}
