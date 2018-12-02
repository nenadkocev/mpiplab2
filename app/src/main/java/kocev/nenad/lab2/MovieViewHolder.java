package kocev.nenad.lab2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView year, rating, title;
    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageViewR);
        year = itemView.findViewById(R.id.textViewYearR);
        rating = itemView.findViewById(R.id.textViewRatingR);
        title = itemView.findViewById(R.id.textViewTitle);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getYear() {
        return year;
    }

    public TextView getRating() {
        return rating;
    }

    public TextView getTitle() {
        return title;
    }
}
