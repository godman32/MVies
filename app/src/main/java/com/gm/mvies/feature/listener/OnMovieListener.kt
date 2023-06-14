package com.gm.mvies.feature.listener

import com.gm.mvies.feature.movie.Movie


/**
 * Created by @godman on 12/06/23.
 */

interface OnMovieListener {
    fun onMovieSelecter(data: Movie?) {}
}