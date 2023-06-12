package com.gm.mvies.feature.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gm.mvies.R
import com.gm.mvies.databinding.FragmentCategoryBinding
import com.gm.mvies.feature.listener.OnCategoryListener


/**
 * Created by @godman on 12/06/23.
 */

class CategoryFragment (
    private val onCategoryListener: OnCategoryListener,
    private val categoryAdapter: CategoryAdapter) : DialogFragment() {

    private val binding by lazy { FragmentCategoryBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    private fun setData() {
        binding.rvGenres.apply {
            adapter = categoryAdapter
        }
    }

    override fun getTheme(): Int {
        return R.style.AppTheme_FullScreenDialog
    }
}