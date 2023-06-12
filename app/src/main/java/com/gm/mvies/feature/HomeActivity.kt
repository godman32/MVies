package com.gm.mvies.feature

import android.content.res.Resources
import android.content.res.Resources.Theme
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gm.mvies.R
import com.gm.mvies.databinding.ActivityHomeBinding
import com.gm.mvies.feature.category.CategoryAdapter
import com.gm.mvies.feature.category.CategoryFragment
import com.gm.mvies.feature.listener.OnCategoryListener

/**
 * Created by @godman on 12/06/23.
 */

class HomeActivity : AppCompatActivity(), OnCategoryListener {

    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }
    private lateinit var viewModel: HomeViewModel

    private lateinit var categoryAdapter : CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        categoryAdapter= CategoryAdapter()

        init()
        onClick()
    }

    fun init(){
        viewModel.getGenre()
        viewModel.observeGenreLiveData().observe(this, Observer { genres ->
            categoryAdapter.setGenres(genres)
        })
    }

    fun onClick(){
        binding.category.setOnClickListener {
            showCategory()
        }
    }

    override fun onCategorySelected(data: Genre) {
        super.onCategorySelected(data)

    }

    private fun showCategory() {
        CategoryFragment(this, categoryAdapter
        ).show(this.supportFragmentManager, "")
    }
}