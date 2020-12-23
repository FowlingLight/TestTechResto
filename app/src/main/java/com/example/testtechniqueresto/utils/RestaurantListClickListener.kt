package com.example.testtechniqueresto.utils

import android.view.View
import com.example.testtechniqueresto.adapters.RestaurantListAdapter

interface RestaurantListClickListener {
    fun onClick(view: View, position: Int, adapter: RestaurantListAdapter)
}