package kocev.nenad.lab2;

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
    private static final String API_KEY = "bd67919d";

    private ImageView poster;
    private TextView title;
    private TextView year;
    private TextView plot;
    private TextView genre;
    private Retrofit retrofit;
    private MoviesAPIService service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movies);
        init();
        handleIntent(getIntent());
    }

    private void init(){
        poster = findViewById(R.id.imageViewPicture);
        title = findViewById(R.id.textViewTitle);
        year = findViewById(R.id.textViewYear);
        plot = findViewById(R.id.textViewPlot);
        genre = findViewById(R.id.textViewGenre);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(MoviesAPIService.class);
    }

    @Override
    protected void onNewIntent(Intent intent){
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        String movieId = intent.getStringExtra("movieId");
        findMovie(movieId);
    }

    private void findMovie(String movieId) {
        Call<MovieBulk> call = service.findMovie(API_KEY, movieId);

        call.enqueue(new Callback<MovieBulk>() {
            @Override
            public void onResponse(Call<MovieBulk> call, Response<MovieBulk> response) {
                if(response.body() != null)
                    showMovie(response.body());
            }

            @Override
            public void onFailure(Call<MovieBulk> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void showMovie(MovieBulk movie){
        genre.setText(movie.getGenre());
        plot.setText(movie.getPlot());
        Picasso.get().load(movie.getPoster()).into(poster);
        title.setText(movie.getTitle());
        year.setText(movie.getYear());
    }
}
