package com.example.testtechniqueresto.views

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.testtechniqueresto.R
import com.example.testtechniqueresto.databinding.ActivityRestaurantDetailsBinding
import com.example.testtechniqueresto.models.Restaurant
import com.example.testtechniqueresto.utils.RESTAURANT
import com.example.testtechniqueresto.utils.getRestaurantDistanceAndTime
import com.example.testtechniqueresto.utils.setSharedElementEnterTransition
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.squareup.picasso.Picasso


class RestaurantDetailsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityRestaurantDetailsBinding
    private lateinit var restaurant: Restaurant

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant_details)

        val json = intent.getStringExtra(RESTAURANT)

        json?.let {
            if (json.isNotBlank()) {
                restaurant = Gson().fromJson(json, Restaurant::class.java)

                binding.restaurant = restaurant
                binding.distanceTextView.text = getRestaurantDistanceAndTime(
                    binding.distanceTextView.context,
                    restaurant
                )

                val picasso = Picasso.Builder(this).build()

                picasso.load(restaurant.logo.url)
                    .into(binding.logoImageView)
                picasso.load(restaurant.mainPicture.url)
                    .into(binding.restaurantImageView)

                (supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment).getMapAsync(
                    this
                )

                binding.phoneLinearLayout.apply {
                    visibility = if (restaurant.phone.isBlank()) View.GONE else View.VISIBLE
                    setOnClickListener {
                        if (ContextCompat.checkSelfPermission(
                                this@RestaurantDetailsActivity,
                                Manifest.permission.CALL_PHONE
                            ) == PackageManager.PERMISSION_GRANTED
                        )
                            startActivity(
                                Intent(
                                    Intent.ACTION_CALL,
                                    Uri.parse("tel:${restaurant.phone}")
                                )
                            )
                        else
                            requestPermissions(arrayOf(Manifest.permission.CALL_PHONE), 1)
                    }
                }

                binding.addressLinearLayout.setOnClickListener {
                    val gmmIntentUri =
                        Uri.parse("geo:0,0?q=${restaurant.address.formated}")

                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                    mapIntent.setPackage("com.google.android.apps.maps")
                    mapIntent.resolveActivity(packageManager)?.let {
                        startActivity(mapIntent)
                    }
                }

            }

            setSharedElementEnterTransition(this, R.transition.detail_activity_enter_transition)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            startActivity(
                Intent(
                    Intent.ACTION_CALL,
                    Uri.parse("tel:${restaurant.phone}")
                )
            )
    }

    override fun onMapReady(map: GoogleMap) {
        if (::restaurant.isInitialized) {
            restaurant.apply {
                val position = LatLng(
                    address.coordinates.latitude,
                    address.coordinates.longitude
                )

                map.apply {
                    addMarker(
                        MarkerOptions().position(position)
                            .title(name)
                    )
                    moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15.0f))
                }
            }
        }
    }
}