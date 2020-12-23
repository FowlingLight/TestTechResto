package com.example.testtechniqueresto.views

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import com.example.testtechniqueresto.R
import com.example.testtechniqueresto.adapters.RestaurantListAdapter
import com.example.testtechniqueresto.models.Restaurant
import com.example.testtechniqueresto.utils.RESTAURANT
import com.example.testtechniqueresto.utils.RestaurantListClickListener
import com.example.testtechniqueresto.viewmodel.RestaurantListViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_restaurant_list.*
import org.koin.android.viewmodel.ext.android.viewModel


class RestaurantListActivity : AppCompatActivity(), RestaurantListClickListener,
    SearchView.OnQueryTextListener {

    private val restaurantListViewModel by viewModel<RestaurantListViewModel>()

    private lateinit var adapter: RestaurantListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_list)
        supportActionBar?.title = ""

        adapter = RestaurantListAdapter(restaurantListViewModel.getRestaurantList(), this)

        restaurant_recycler_view.adapter = adapter
    }

    private fun startTransition(view: View, restaurant: Restaurant) {
        val intent = Intent(this, RestaurantDetailsActivity::class.java)
        intent.putExtra(RESTAURANT, Gson().toJson(restaurant))

        val transitionPairs: Array<Pair<View, String>?> = arrayOfNulls(5)
        transitionPairs[0] = Pair(view.findViewById(R.id.restaurant_image_view), "mainImage")
        transitionPairs[1] = Pair(view.findViewById(R.id.available_time_linear_layout), "logo")
        transitionPairs[2] = Pair(view.findViewById(R.id.name_text_view), "name")
        transitionPairs[3] = Pair(view.findViewById(R.id.distance_text_view), "distance")

        transitionPairs[4] = Pair(
            findViewById(android.R.id.statusBarBackground),
            Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME
        )

        val bundle =
            ActivityOptionsCompat.makeSceneTransitionAnimation(this, *transitionPairs).toBundle()
        ActivityCompat.startActivity(this, intent, bundle)
    }

    override fun onClick(view: View, position: Int, adapter: RestaurantListAdapter) {
        startTransition(
            view,
            adapter.restaurantList[position]
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchItem: MenuItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.apply {
            queryHint = getString(R.string.search)
            setOnQueryTextListener(this@RestaurantListActivity)
        }

        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        adapter.filter.filter(query)
        return false
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }
}