package com.gm.mvies.feature.helper

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


fun String.toDate(format:String):Date{
    val inputFormatter: DateFormat = SimpleDateFormat(format)
    inputFormatter.setTimeZone(TimeZone.getTimeZone("UTC"))

    return inputFormatter.parse(this) as Date
}