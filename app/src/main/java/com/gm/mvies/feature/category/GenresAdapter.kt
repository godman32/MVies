package com.gm.mvies.feature.category

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gm.mvies.databinding.ItemCategoryBinding
import com.gm.mvies.feature.listener.OnGenreListener

class GenresAdapter(private val onGenreListener: OnGenreListener) : RecyclerView.Adapter<GenresAdapter.ViewHolder>() {
    private var genres = ArrayList<Genre>()

    @SuppressLint("NotifyDataSetChanged")
    fun setGenres(genres: List<Genre>) {
        this.genres = genres as ArrayList<Genre>
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        Glide.with(holder.itemView)
//            .load("https://image.tmdb.org/t/p/w500" + movieList[position].poster_path)
//            .into(holder.binding.movieImage)
        holder.binding.genre.text = genres[position].name

        holder.binding.genre.setOnClickListener {
            onGenreListener.onGenreSelected(genres[position])
        }
    }



    override fun getItemCount(): Int {
        return genres.size
    }
}