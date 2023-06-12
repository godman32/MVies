package com.gm.mvies.feature

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zym.zymresseler.conn.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeViewModel : ViewModel() {
    private var genreLiveData = MutableLiveData<List<Genre>>()

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

    fun observeGenreLiveData() : LiveData<List<Genre>> {
        return genreLiveData
    }
}