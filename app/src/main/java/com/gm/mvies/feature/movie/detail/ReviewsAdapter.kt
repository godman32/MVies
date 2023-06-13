package com.gm.mvies.feature.movie.detail

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
import com.gm.mvies.databinding.ItemReviewBinding
import com.gm.mvies.feature.helper.RoundedTransformation
import com.gm.mvies.feature.listener.OnMovieListener
import com.gm.mvies.feature.listener.OnScrollFullListener
import com.gm.mvies.feature.review.Review
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation


/**
 * Created by @godman on 13/06/23.
 */

class ReviewsAdapter(
    private val onScrollFullListener: OnScrollFullListener) : RecyclerView.Adapter<ReviewsAdapter.ViewHolder>() {
    private var reviews = ArrayList<Review>()

    private var load= false
    var clear= false

    fun add(reviews: List<Review>) {
        this.reviews.addAll(reviews)
        load= false
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearData(){
        reviews.clear()
        notifyDataSetChanged()
        load= false
    }

    class ViewHolder(val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemReviewBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.name.text= reviews[position].author
        holder.binding.movieRating.rating = (reviews[position].authorDetails?.rating?.toFloat() ?: 0f) /2
        holder.binding.desc.text= reviews[position].content


        Log.e("CEK","https://image.tmdb.org/t/p/w92" + reviews[position].authorDetails?.avatarPath )


        if(reviews[position].authorDetails?.avatarPath.toString().equals("null")){
            holder.binding.image.setImageResource(R.mipmap.ic_user)
        } else{
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w92" + reviews[position].authorDetails?.avatarPath)
                .transform(CropCircleTransformation())
                .into(holder.binding.image)
        }



        if(position > itemCount - 6 && !load && itemCount > 0){
            load= true
            onScrollFullListener.onScrollFull()
        }

//        holder.binding.image.setOnClickListener { onMovieListener.onMovieSelecter(movies[position]) }
    }

    override fun getItemCount(): Int {
        return reviews.size
    }
}