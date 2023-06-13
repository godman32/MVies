package com.gm.mvies.feature.listener

import com.gm.mvies.feature.category.Genre


/**
 * Created by @godman on 13/06/23.
 */

interface OnScrollFullListener {
    fun onScrollToPage(page:Int) {}
    fun onScrollFull() {}
}