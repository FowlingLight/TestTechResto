package com.example.testtechniqueresto.utils

import android.content.Context
import android.view.View
import com.example.testtechniqueresto.R
import com.example.testtechniqueresto.models.Restaurant

const val RESTAURANT = "RESTAURANT"

fun getRestaurantDistanceAndTime(context: Context, restaurant: Restaurant): String {
    val stringBuilder = StringBuilder()

    if (restaurant.distance > 1) {
        stringBuilder.append("%.2f".format(restaurant.distance))
        stringBuilder.append(" km")
    } else {
        stringBuilder.append((restaurant.distance * 100).toInt())
        stringBuilder.append(" m")
    }

    return context.getString(
        R.string.distance,
        stringBuilder.toString(),
        (13 / restaurant.distance).toInt().toString()
    )
}

fun getRestaurantAvailableTime(time: String): String {
    return time.substringAfter("T").substring(0, 5)
}