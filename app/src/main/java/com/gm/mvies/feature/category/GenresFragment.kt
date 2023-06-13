package com.gm.mvies.feature.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.gm.mvies.R
import com.gm.mvies.databinding.FragmentCategoryBinding
import com.gm.mvies.feature.listener.OnGenreListener
import com.gm.mvies.feature.movie.MoviesAdapter


/**
 * Created by @godman on 12/06/23.
 */

class GenresFragment (
    private val onGenreListener: OnGenreListener,
    private val data: List<Genre>) : DialogFragment(), OnGenreListener {

    private val binding by lazy { FragmentCategoryBinding.inflate(layoutInflater) }
    private lateinit var genresAdapter: GenresAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        genresAdapter= GenresAdapter(this)

        setData()
    }

    private fun setData() {
        genresAdapter.setGenres(data)
        binding.rvGenres.apply {
            adapter = genresAdapter
        }
    }

    override fun onGenreSelected(data: Genre?) {
        super.onGenreSelected(data)

        onGenreListener.onGenreSelected(data)
        dismissAllowingStateLoss()
    }

    override fun getTheme(): Int {
        return R.style.AppTheme_FullScreenDialog
    }
}