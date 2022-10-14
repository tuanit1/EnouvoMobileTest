package com.example.enouvomobiletest.extension

import android.content.Context
import com.example.enouvomobiletest.R
import java.text.SimpleDateFormat
import java.util.*

fun Context.getFormatDateString(time: String): String {
    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.US)
    val dateSdf = SimpleDateFormat("dd/M/yy", Locale.US)
    val timeSdf = SimpleDateFormat("hh:mm", Locale.US)
    val dateString = sdf.parse(time)?.let { dateSdf.format(it) }
    val timeString = sdf.parse(time)?.let { timeSdf.format(it) }

    return resources.getString(R.string.itemTimeString, timeString, dateString)
}
