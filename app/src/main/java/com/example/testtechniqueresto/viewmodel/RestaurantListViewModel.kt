package com.example.testtechniqueresto.viewmodel

import androidx.lifecycle.ViewModel
import com.example.testtechniqueresto.models.BaseGson
import com.example.testtechniqueresto.models.Restaurant
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.net.URL

class RestaurantListViewModel : ViewModel() {
    private val list = mutableListOf<Restaurant>()

    fun getRestaurantList(): List<Restaurant> {
        try {
            val task = GlobalScope.async {
                val result =
                    URL("https://gist.githubusercontent.com/psicot/ab5fea0e28ceb9ae1420df5cedb32bc9/raw/4b1fc35395f7474a31078ffa2b1203cb243a217c/restaurants.json").readText()
                if (result.isNotBlank()) {
                    val json = Gson().fromJson(result, BaseGson::class.java)

                    json.restaurants?.let {
                        list.clear()
                        list.addAll(it)
                    }
                }
            }

            runBlocking {
                task.await()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }
}