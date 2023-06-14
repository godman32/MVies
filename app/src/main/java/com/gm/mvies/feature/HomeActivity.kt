package com.gm.mvies.feature

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.gm.mvies.databinding.ActivityHomeBinding
import com.gm.mvies.feature.genre.Genre
import com.gm.mvies.feature.genre.GenresFragment
import com.gm.mvies.feature.helper.Status
import com.gm.mvies.feature.helper.setHidden
import com.gm.mvies.feature.helper.setVisible
import com.gm.mvies.feature.listener.OnGenreListener
import com.gm.mvies.feature.listener.OnLoadDataListener
import com.gm.mvies.feature.listener.OnMovieListener
import com.gm.mvies.feature.listener.OnScrollFullListener
import com.gm.mvies.feature.movie.Movie
import com.gm.mvies.feature.movie.MoviesAdapter
import com.gm.mvies.feature.movie.detail.MovieFragment

/**
 * Created by @godman on 12/06/23.
 */

class HomeActivity : AppCompatActivity(), OnGenreListener, OnScrollFullListener, OnMovieListener, OnLoadDataListener {

    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }
    private val moviesAdapter by lazy { MoviesAdapter( this, this) }

    private val viewModel by lazy { ViewModelProvider(this)[HomeViewModel::class.java] }

    private var status= Status.FREE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
        onClick()
    }

    fun init(){
        viewModel.getGenre()
        viewModel.getPopularMovies()
        status= Status.LOAD
        showMovie()
    }

    fun onClick(){
        binding.category.setOnClickListener {
            showCategory()
        }

        binding.reset.setOnClickListener {
            binding.lPopular.visibility= View.VISIBLE
            binding.lGenre.visibility= View.GONE
            binding.reset.visibility= View.GONE
            moviesAdapter.clearData()

            viewModel.page= 1
            viewModel.getPopularMovies()
        }
    }

    override fun onGenreSelected(data: Genre?) {
        super.onGenreSelected(data)

        binding.lPopular.visibility= View.GONE
        binding.lGenre.visibility= View.VISIBLE
        binding.reset.visibility= View.VISIBLE

        binding.genre.text= data?.name.toString()
        binding.lGenre.visibility= View.VISIBLE

        moviesAdapter.clearData()
        viewModel.page= 1
        viewModel.getMovies(data?.id!!.toInt())

    }

    override fun onMovieSelecter(data: Movie?) {
        super.onMovieSelecter(data)

        data?.let { MovieFragment(it).show(this.supportFragmentManager, "M") }
    }

    override fun onScrollFull() {
        super.onScrollFull()
        viewModel.page++
        binding.load.setVisible()
        status= Status.LOAD

        if(binding.reset.visibility == View.VISIBLE){
            viewModel.getMovies(viewModel.genreID)
        } else
            viewModel.getPopularMovies()

    }

    private fun showCategory() {
        viewModel.observeGenres().observe(this, Observer { genres ->
            GenresFragment(this@HomeActivity, genres
            ).show(this.supportFragmentManager, "G")
        })
    }

    private fun showMovie(){
        viewModel.observeMovies().observe(this, Observer { movies ->
            binding.load.setHidden()
            if(movies.size > 0){
                status= Status.FREE
                moviesAdapter.add(movies)
                if(binding.rvMovies.adapter == null){
                    binding.rvMovies.apply {
                        layoutManager = GridLayoutManager(this@HomeActivity,2)
                        adapter = moviesAdapter
                    }
                }
                binding.rvMovies.post(Runnable { moviesAdapter.notifyItemInserted(moviesAdapter.itemCount)})
            } else
                status= Status.CLEAR
        })
    }
}