package com.gm.mvies.feature.movie

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.module.AppGlideModule
import com.gm.mvies.R
import com.gm.mvies.databinding.ItemMovieBinding
import com.gm.mvies.feature.listener.OnMovieListener
import com.gm.mvies.feature.listener.OnScrollFullListener
import com.squareup.picasso.Picasso


/**
 * Created by @godman on 13/06/23.
 */

class MoviesAdapter(
    private val onScrollFullListener: OnScrollFullListener,
    private val onMovieListener: OnMovieListener) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    private var movies = ArrayList<Movie>()

    private var load= false
    var clear= false

    fun add(movies: List<Movie>) {
        this.movies.addAll(movies)
        load= false
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearData(){
        movies.clear()
        notifyDataSetChanged()
        load= false
    }

    class ViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if(movies[position].posterPath.toString().equals("null")){
            holder.binding.image.setImageResource(R.mipmap.no_image)
        } else {
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w300" + movies[position].posterPath)
                .into(holder.binding.image)
        }

        if(position > itemCount - 6 && !load && itemCount > 0){
            load= true
            onScrollFullListener.onScrollFull()
        }

        holder.binding.image.setOnClickListener { onMovieListener.onMovieSelecter(movies[position]) }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}