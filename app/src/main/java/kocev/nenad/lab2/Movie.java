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

    @ColumnInfo(name = "poster")
    private String Poster;

    @ColumnInfo(name = "type")
    private String Type;

    @ColumnInfo(name = "imdbId")
    private String imdbID;

    public Movie(String Title, String Year, String Poster,String imdbID, String Type) {
        this.Title = Title;
        this.Year = Year;
        this.Poster = Poster;
        this.imdbID = imdbID;
        this.Type = Type;
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


    public String getPoster() {
        return Poster;
    }


    public String getImdbID() {
        return imdbID;
    }

    public String getType() {
        return Type;
    }
}

