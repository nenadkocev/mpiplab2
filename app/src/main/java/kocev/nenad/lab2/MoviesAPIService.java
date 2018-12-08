package kocev.nenad.lab2;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesAPIService {
    @GET("/")
    Call<Movie> listMovies(@Query("apikey") String apiKey, @Query("plot") String plot, @Query("t") String queryString);
}
