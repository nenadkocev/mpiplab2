package kocev.nenad.lab2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SearchMoviesActivity extends AppCompatActivity {

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
        Movie movie = (Movie)intent.getSerializableExtra("movie");
        showMovie(movie);
    }


    public void showMovie(Movie movie){
        Picasso.get().load(movie.getPoster()).into(poster);
        title.setText(movie.getTitle());
        genre.setText(movie.getGenre());
        plot.setText(movie.getPlot());
        year.setText(movie.getYear());
    }
}
