package com.gm.mvies.feature

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gm.mvies.feature.category.Genre
import com.gm.mvies.feature.category.Genres
import com.gm.mvies.feature.listener.OnLoadDataListener
import com.gm.mvies.feature.movie.Movie
import com.gm.mvies.feature.movie.Movies
import com.zym.zymresseler.conn.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by @godman on 13/06/23.
 */

class HomeViewModel : ViewModel() {
    private var genreLiveData = MutableLiveData<List<Genre>>()
    private var moviesByGenre = MutableLiveData<List<Movie>>()

    var page=1
    var genreID=0

    var load= false


    fun getGenre() {
        ApiClient.api.getGenres().enqueue(object  : Callback<Genres> {
            override fun onResponse(call: Call<Genres>, response: Response<Genres>) {
                if (response.body()!=null){
                    genreLiveData.value = response.body()!!.genres
                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<Genres>, t: Throwable) {
                Log.d("TAG",t.message.toString())
            }
        })
    }

    fun observeGenres() : LiveData<List<Genre>> {
        return genreLiveData
    }

    fun getPopularMovies() {
        if(!load){
            load= true
            ApiClient.api.getPopularMovie(page).enqueue(object  : Callback<Movies> {
                override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                    load= false
                    if (response.body()!=null){
                        moviesByGenre.value = response.body()!!.movies
                    }
                    else{
                        return
                    }
                }
                override fun onFailure(call: Call<Movies>, t: Throwable) {
                    load= false
                    Log.d("TAG",t.message.toString())
                }
            })
        }
    }

    fun getMovies(genre:Int) {
        genreID= genre
        if(!load){
            load= true
            ApiClient.api.getMoviesByGenre(page, genre).enqueue(object  : Callback<Movies> {
                override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                    load= false
                    if (response.body()!=null){
                        moviesByGenre.value = response.body()!!.movies
                    }
                    else{
                        return
                    }
                }
                override fun onFailure(call: Call<Movies>, t: Throwable) {
                    load= false
                    Log.d("TAG",t.message.toString())
                }
            })
        }
    }

    fun observeMovies() : LiveData<List<Movie>> {
        return moviesByGenre
    }


}