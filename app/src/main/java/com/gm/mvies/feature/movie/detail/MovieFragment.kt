package com.gm.mvies.feature.movie.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.gm.mvies.R
import com.gm.mvies.databinding.FragmentMovieBinding
import com.gm.mvies.feature.helper.Status
import com.gm.mvies.feature.helper.setHidden
import com.gm.mvies.feature.helper.setVisible
import com.gm.mvies.feature.movie.Movie
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


/**
 * Created by @godman on 13/06/23.
 */

class MovieFragment(private var movie: Movie) : DialogFragment() {


    private val binding by lazy { FragmentMovieBinding.inflate(layoutInflater) }
    private val viewModel by lazy {ViewModelProvider(this)[MovieViewModel::class.java]}
    private val reviewsAdapter by lazy { ReviewsAdapter( ) }

    private var status= Status.FREE


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setView()
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        onClick()
        onScroll()
    }

    fun init(){

        Glide.with(this).load("https://image.tmdb.org/t/p/w154"+movie.posterPath)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?,
                                          isFirstResource: Boolean): Boolean {
                    binding.image.scaleType= ImageView.ScaleType.CENTER_INSIDE
                    return false
                }
                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?,
                                             dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    return false
                }

            })
            .error(R.mipmap.logo_black)
            .into(binding.image)

        binding.title.text= movie.title
        binding.desc.text= movie.overview
        binding.movieReleaseDate.text= movie.releaseDate
        binding.movieRating.rating = (movie.popularity?.toFloat() ?: 0f) /2

        viewModel.getMovie(movie.id!!.toInt())
        viewModel.getTrailers(movie.id!!.toInt())
        viewModel.getReviws(movie.id!!.toInt())
        status= Status.LOAD
        binding.load.setVisible()
    }

    fun setView(){
        viewModel.observeMovie().observe(this, Observer { data ->
            movie= data

            var url= "";
            if(movie.backdropPath.toString().equals("null")){
                url= "https://image.tmdb.org/t/p/w780"+movie.posterPath
            } else{
                url= "https://image.tmdb.org/t/p/w780"+movie.backdropPath
            }

            Glide.with(this).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?,
                                              isFirstResource: Boolean): Boolean {
                        binding.movieBackdrop.scaleType= ImageView.ScaleType.CENTER_INSIDE
                        return false
                    }
                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?,
                                                 dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        return false
                    }

                })
                .error(R.mipmap.logo_white)
                .into(binding.movieBackdrop)

        })

        viewModel.observeTrailers().observe(this, Observer { trailers ->
            status= Status.FREE
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
            binding.load.setHidden()
            if(reviews.size > 0){
                status= Status.FREE
                binding.lReviews.setVisible()
                reviewsAdapter.add(reviews)
                if(binding.rvReviews.adapter == null){
                    binding.rvReviews.apply {
                        adapter = reviewsAdapter
                    }
                }
                binding.rvReviews.post(Runnable { reviewsAdapter.notifyItemInserted(reviewsAdapter.itemCount)})
            } else{
                status= Status.CLEAR
            }
        })
    }


    fun onClick(){
        binding.back.setOnClickListener { dismissAllowingStateLoss() }
    }

    fun onScroll(){
        binding.scroll.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            val scrollViewHeight: Int = binding.scroll.getChildAt(0).getBottom() - binding.scroll.getHeight()

            if(status == Status.FREE && scrollY > scrollViewHeight- 2000){
                status= Status.LOAD
                viewModel.page++
                viewModel.getReviws(movie.id!!.toInt())
                binding.load.setVisible()
            }
        }
    }

    override fun getTheme(): Int {
        return R.style.AppTheme_FullScreenDialogSolid
    }
}