package com.gm.mvies.feature.movie.detail

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gm.mvies.databinding.ItemCategoryBinding
import com.gm.mvies.databinding.ItemTrailerBinding
import com.gm.mvies.feature.listener.OnGenreListener
import com.gm.mvies.feature.movie.Trailer
import com.gm.mvies.feature.movie.Trailers
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class TrailerAdapter(private var trailers: List<Trailer>) : RecyclerView.Adapter<TrailerAdapter.ViewHolder>() {
//    private var trailers = ArrayList<Trailer>()

//    @SuppressLint("NotifyDataSetChanged")
//    fun setGenres(genres: List<Trailer>) {
//        this.trailers = genres as ArrayList<Trailer>
//        notifyDataSetChanged()
//    }

    class ViewHolder(val binding: ItemTrailerBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTrailerBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
//        Log.e("CEK", trailers[position].key.toString())
        holder.binding.trailer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                // loading the selected video
                // into the YouTube Player
                youTubePlayer.cueVideo(trailers[position].key.toString(), 0f)
            }
            override fun onStateChange(
                youTubePlayer: YouTubePlayer,
                state: PlayerConstants.PlayerState
            ) {
                super.onStateChange(youTubePlayer, state)
            }
        })
    }

    override fun getItemCount(): Int {
        return trailers.size
    }
}