package kocev.nenad.lab2;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesAPIService {
    @GET("/")
    Call<MoviesResponse> listMovies(@Query("apikey") String apiKey, @Query("s") String queryString);

    @GET("/")
    Call<MovieBulk> findMovie(@Query("apikey") String apiKey, @Query("i") String movieId);
}
