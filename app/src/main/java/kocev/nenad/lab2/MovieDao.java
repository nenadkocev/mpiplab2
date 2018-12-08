package kocev.nenad.lab2;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MovieDao {
    @Insert
    void insert(Movie... movies);

    @Query("DELETE FROM movie_table")
    void deleteAll();

    @Query("SELECT * FROM MOVIE_TABLE")
    List<Movie> getAllMovies();
}
