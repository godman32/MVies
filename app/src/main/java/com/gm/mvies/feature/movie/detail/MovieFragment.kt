package com.gm.mvies.feature.movie.detail

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings.PluginState
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.gm.mvies.R
import com.gm.mvies.databinding.FragmentMovieBinding
import com.gm.mvies.feature.listener.OnScrollFullListener
import com.gm.mvies.feature.movie.Movie
import com.gm.mvies.feature.movie.MoviesAdapter
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


/**
 * Created by @godman on 13/06/23.
 */

class MovieFragment(private var movie: Movie) : DialogFragment(), OnScrollFullListener {

    private val binding by lazy { FragmentMovieBinding.inflate(layoutInflater) }
    private val viewModel by lazy {ViewModelProvider(this)[MovieViewModel::class.java]}
    private val reviewsAdapter by lazy { ReviewsAdapter( this) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        onClick()
    }

    fun init(){

        Glide.with(this).load("https://image.tmdb.org/t/p/w154"+movie.posterPath)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(binding.image)

        binding.title.text= movie.title
        binding.desc.text= movie.overview
        binding.movieReleaseDate.text= movie.releaseDate
        binding.movieRating.rating = (movie.popularity?.toFloat() ?: 0f) /2

        viewModel.getMovie(movie.id!!.toInt())
        viewModel.getTrailers(movie.id!!.toInt())
        viewModel.getReviws(movie.id!!.toInt())
        setView()
    }

    fun setView(){
        viewModel.observeMovie().observe(this, Observer { data ->
            movie= data

            if(movie.backdropPath.toString().equals("null")){
                Log.e("CEK", "load poster")
                Glide.with(this).load("https://image.tmdb.org/t/p/w780"+movie.posterPath)
                    .into(binding.movieBackdrop)
            } else{
                Log.e("CEK", "load poster")
                Glide.with(this).load("https://image.tmdb.org/t/p/w780"+movie.backdropPath)
                    .into(binding.movieBackdrop)
            }

        })

        viewModel.observeTrailers().observe(this, Observer { trailers ->
            if(trailers != null){
                binding.lTrailer.visibility= View.VISIBLE
                lifecycle.addObserver(binding.trailer)
                binding.trailer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        // loading the selected video
                        // into the YouTube Player
                        youTubePlayer.cueVideo(trailers.key.toString(), 0f)
                    }

                    override fun onStateChange(
                        youTubePlayer: YouTubePlayer,
                        state: PlayerConstants.PlayerState
                    ) {
                        super.onStateChange(youTubePlayer, state)
                    }
                })
            } else
                binding.lTrailer.visibility= View.GONE
        })

        viewModel.observeReviews().observe(this, Observer { reviews ->
            reviewsAdapter.add(reviews)
            if(binding.rvReviews.adapter == null){
                binding.rvReviews.apply {
                    adapter = reviewsAdapter
                }
            }
            binding.rvReviews.post(Runnable { reviewsAdapter.notifyItemInserted(reviewsAdapter.itemCount)})
        })
    }


    fun onClick(){
        binding.back.setOnClickListener { dismissAllowingStateLoss() }
    }

    override fun getTheme(): Int {
        return R.style.AppTheme_FullScreenDialogSolid
    }

}