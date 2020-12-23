package com.example.testtechniqueresto.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import com.example.testtechniqueresto.databinding.ViewRestaurantListBinding
import com.example.testtechniqueresto.models.Restaurant
import com.example.testtechniqueresto.utils.RestaurantListClickListener
import com.example.testtechniqueresto.utils.getRestaurantAvailableTime
import com.example.testtechniqueresto.utils.getRestaurantDistanceAndTime
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class RestaurantListAdapter(
    val restaurantList: List<Restaurant>,
    private val restaurantListClickListener: RestaurantListClickListener
) :
    RecyclerView.Adapter<RestaurantListAdapter.ViewHolder>(), Filterable {

    private val restaurantListFiltered = mutableListOf<Restaurant>()

    init {
        restaurantListFiltered.addAll(restaurantList)
    }

    class ViewHolder(private val binding: ViewRestaurantListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(restaurant: Restaurant) {
            binding.apply {
                val context = root.context

                this.restaurant = restaurant

                val picasso = Picasso.Builder(context).build()
                picasso.load(restaurant.mainPicture.url)
                    .into(restaurantImageView)

                availableTimeTextView.text = getRestaurantAvailableTime(restaurant.availableAt)
                distanceTextView.text =
                    getRestaurantDistanceAndTime(distanceTextView.context, restaurant)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ViewRestaurantListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount() = restaurantListFiltered.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(restaurantListFiltered[position])
        holder.itemView.setOnClickListener {
            restaurantListClickListener.onClick(it, position, this)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()

                restaurantListFiltered.clear()
                if (charSearch.isEmpty())
                    restaurantListFiltered.addAll(restaurantList)
                else {
                    for (restaurant in restaurantList)
                        if (restaurant.name.toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT))
                        )
                            restaurantListFiltered.add(restaurant)
                }

                val filterResults = FilterResults()
                filterResults.values = restaurantListFiltered
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                notifyDataSetChanged()
            }

        }
    }

}