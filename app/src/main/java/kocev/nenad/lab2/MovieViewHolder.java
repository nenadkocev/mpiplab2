package kocev.nenad.lab2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView year, rating, title;
    LinearLayout parentLayout;
    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageViewR);
        year = itemView.findViewById(R.id.textViewYearR);
        rating = itemView.findViewById(R.id.textViewRatingR);
        title = itemView.findViewById(R.id.textViewTitleR);
        parentLayout = itemView.findViewById(R.id.parent_layout);
    }
}
