package kocev.nenad.lab2;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "movie_table")
public class Movie implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "title")
    private String Title;

    @ColumnInfo(name = "year")
    private String Year;

    @ColumnInfo(name = "released")
    private String Released;

    @ColumnInfo(name = "genre")
    private String Genre;

    @ColumnInfo(name = "director")
    private String Director;

    @ColumnInfo(name = "plot")
    private String Plot;

    @ColumnInfo(name = "poster")
    private String Poster;

    @ColumnInfo(name = "imdbRating")
    private String imdbRating;

    @ColumnInfo(name = "imdbId")
    private String imdbID;

    public Movie(String Title, String Year, String Released, String Genre, String Director, String Plot, String Poster, String imdbRating, String imdbID) {
        this.Title = Title;
        this.Year = Year;
        this.Released = Released;
        this.Genre = Genre;
        this.Director = Director;
        this.Plot = Plot;
        this.Poster = Poster;
        this.imdbRating = imdbRating;
        this.imdbID = imdbID;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return Title;
    }

    public String getYear() {
        return Year;
    }

    public String getReleased() {
        return Released;
    }

    public String getGenre() {
        return Genre;
    }

    public String getDirector() {
        return Director;
    }

    public String getPlot() {
        return Plot;
    }

    public String getPoster() {
        return Poster;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getImdbID() {
        return imdbID;
    }
}

