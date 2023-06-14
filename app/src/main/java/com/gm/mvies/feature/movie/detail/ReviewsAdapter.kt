package com.gm.mvies.feature.movie.detail

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.gm.mvies.R
import com.gm.mvies.databinding.ItemReviewBinding
import com.gm.mvies.feature.helper.TimeFormat
import com.gm.mvies.feature.helper.format
import com.gm.mvies.feature.helper.toDate
import com.gm.mvies.feature.listener.OnScrollFullListener
import com.gm.mvies.feature.review.Review
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by @godman on 13/06/23.
 */

class ReviewsAdapter : RecyclerView.Adapter<ReviewsAdapter.ViewHolder>() {
    private var reviews = ArrayList<Review>()

    fun add(reviews: List<Review>) {
        this.reviews.addAll(reviews)
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
        holder.binding.date.text= reviews[position].createdAt.toString().toDate(TimeFormat.SYSTEM).format(TimeFormat.DD_MMM_YYYY)
        holder.binding.desc.originalText= reviews[position].content.toString()

        if(reviews[position].authorDetails?.avatarPath.toString().contains("http")){
            Glide.with(holder.itemView).load(reviews[position].authorDetails?.avatarPath!!.removeRange(0,1))
                .error(R.mipmap.ic_user)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .circleCrop()
                .into(holder.binding.image)
        } else{
            Glide.with(holder.itemView).load("https://image.tmdb.org/t/p/w92"+reviews[position].authorDetails?.avatarPath)
                .error(R.mipmap.ic_user)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .circleCrop()
                .into(holder.binding.image)
        }


    }

    override fun getItemCount(): Int {
        return reviews.size
    }
}