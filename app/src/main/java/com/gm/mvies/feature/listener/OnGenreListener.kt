package com.gm.mvies.feature.listener

import com.gm.mvies.feature.category.Genre


/**
 * Created by @godman on 12/06/23.
 */

interface OnGenreListener {
    fun onGenreSelected(data: Genre?) {}
}