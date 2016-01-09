package govinda.practic.com.layoutapp.controller;

import govinda.practic.com.layoutapp.model.ItemImages;
import govinda.practic.com.layoutapp.model.ItemMovieDetail;
import govinda.practic.com.layoutapp.model.ItemMovies;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by admin on 09-01-2016.
 */
/* Retrofit 2.0 */

public interface ApiService {

    @GET("/3/movie/upcoming?api_key=Your_API_KEY")
    Call<ItemMovies> loadMovie();

    //https://api.themoviedb.org/3/movie/<movie-id>
    @GET("/3/movie/{id}?api_key=Your_API_KEY")
    Call<ItemMovieDetail> getDetailsByID(@Path("id") String id);

    @GET("/3/movie/{id}/images?api_key=Your_API_KEY")
    Call<ItemImages> getImagesByMovieID(@Path("id") String id);

    /*
    Movie Details API
https://api.themoviedb.org/3/movie/<movie-id>
Params:
api_key - 

Get Images API
https://api.themoviedb.org/3/movie/<movie-id>/images
     */
}