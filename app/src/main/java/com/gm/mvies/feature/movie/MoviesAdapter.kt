package com.gm.mvies.feature.movie

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.gm.mvies.R
import com.gm.mvies.databinding.ItemMovieBinding
import com.gm.mvies.feature.helper.setHidden
import com.gm.mvies.feature.helper.setVisible
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

        holder.binding.title.text= movies[position].title
        holder.binding.movieRating.rating = (movies[position].popularity!!.toFloat()) /2

        Glide.with(holder.itemView).load("https://image.tmdb.org/t/p/w300"+ movies[position].posterPath)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?,
                                          isFirstResource: Boolean): Boolean {
                    holder.binding.image.scaleType= ImageView.ScaleType.CENTER_INSIDE
                    holder.binding.load.setHidden()
                    return false
                }
                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?,
                                             dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    holder.binding.load.setHidden()
                    return false
                }

            })
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(R.mipmap.logo_black)
            .into(holder.binding.image)

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