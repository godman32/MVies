package com.gm.mvies.feature.movie.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gm.mvies.feature.movie.Movie
import com.gm.mvies.feature.movie.Movies
import com.gm.mvies.feature.movie.Trailer
import com.gm.mvies.feature.movie.Trailers
import com.gm.mvies.feature.review.Review
import com.gm.mvies.feature.review.Reviews
import com.zym.mvies.conn.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by @godman on 13/06/23.
 */

class MovieViewModel : ViewModel() {
    private var moviesByGenre = MutableLiveData<Movie>()
    private var trailers = MutableLiveData<Trailer>()
    private var reviews = MutableLiveData<List<Review>>()

    var page= 1

    fun getMovie(movieId:Int) {
        ApiClient.api.detailMovie(movieId).enqueue(object  : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.body()!=null){
                    moviesByGenre.value = response.body()!!
                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d("TAG",t.message.toString())
            }
        })
    }

    fun observeMovie() : LiveData<Movie> {
        return moviesByGenre
    }


    fun getTrailers(movieId:Int) {
        ApiClient.api.getTrailer(movieId).enqueue(object  : Callback<Trailers> {
            override fun onResponse(call: Call<Trailers>, response: Response<Trailers>) {
                if (response.body()!=null && response.body()!!.trailers.size > 0){
                    trailers.value = response.body()!!.trailers.get(0)
                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<Trailers>, t: Throwable) {
                Log.d("TAG",t.message.toString())
            }
        })
    }

    fun observeTrailers() : LiveData<Trailer> {
        return trailers
    }

    fun getReviws(id:Int) {
        ApiClient.api.getReviews(id, page).enqueue(object  : Callback<Reviews> {
            override fun onResponse(call: Call<Reviews>, response: Response<Reviews>) {
                if (response.body()!=null){
                    reviews.value = response.body()!!.reviews
                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<Reviews>, t: Throwable) {
                Log.d("TAG",t.message.toString())
                return
            }
        })
    }

    fun observeReviews() : LiveData<List<Review>> {
        return reviews
    }
}