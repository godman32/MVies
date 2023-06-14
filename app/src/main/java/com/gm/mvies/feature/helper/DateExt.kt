package com.gm.mvies.feature.helper

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
fun Date.format(format: String):String{
    val df = SimpleDateFormat(format)

    return df.format(this)
}