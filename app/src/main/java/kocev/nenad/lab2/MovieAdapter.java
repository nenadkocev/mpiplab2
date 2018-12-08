package kocev.nenad.lab2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.xml.datatype.Duration;

import retrofit2.Retrofit;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private Context context;
    private List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movieitem, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        final Movie movie = movies.get(i);
        Picasso.get().load(movie.getPoster()).into(movieViewHolder.imageView);
        movieViewHolder.rating.setText(movie.getImdbRating());
        movieViewHolder.title.setText(movie.getTitle());
        movieViewHolder.year.setText(movie.getYear());

        movieViewHolder.parentLayout.setOnClickListener((view) -> {
            Intent intent = new Intent(context, SearchMoviesActivity.class);
            intent.putExtra("movie", movie);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if(movies == null)
            return 0;
        return movies.size();
    }

    public void update(List<Movie> movies){
        this.movies = movies;
        this.notifyDataSetChanged();
    }
}
