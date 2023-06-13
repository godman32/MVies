package com.zym.zymresseler.conn

import com.gm.mvies.feature.category.Genres
import com.gm.mvies.feature.movie.Movie
import com.gm.mvies.feature.movie.Movies
import com.gm.mvies.feature.movie.Trailers
import com.gm.mvies.feature.review.Reviews
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @GET("genre/movie/list?language=en")
    fun getGenres() : Call<Genres>

    @GET("movie/popular?language=en-US&api_key=892a167d747e8e87a7bdeca77fe036e3")
    fun getPopularMovie(@Query("page") page: Int) : Call<Movies>

    @GET("discover/movie?language=en&api_key=892a167d747e8e87a7bdeca77fe036e3&sort_by=release_date.desc")
    fun getMoviesByGenre(@Query("page") page: Int, @Query("with_genres") genres: Int) : Call<Movies>

    @GET("movie/{id}?language=en-US&api_key=892a167d747e8e87a7bdeca77fe036e3")
    fun detailMovie(@Path("id") id: Int): Call<Movie>

    @GET("movie/{id}/videos?api_key=892a167d747e8e87a7bdeca77fe036e3")
    fun getTrailer(@Path("id") id: Int) : Call<Trailers>


    @GET("movie/{id}}/reviews?language=en-US")
    fun getReviews(@Path("id") id: Int, @Query("page") page: Int) : Call<Reviews>

}