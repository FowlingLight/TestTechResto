package com.example.testtechniqueresto

import android.app.Application
import com.example.testtechniqueresto.viewmodel.RestaurantListViewModel
import org.koin.android.ext.android.startKoin
import org.koin.android.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module

class App: Application() {

    private val dataModule = module {
        viewModel<RestaurantListViewModel>()
    }

    override fun onCreate() {
        super.onCreate()

        startKoin(applicationContext, listOf(dataModule))
    }
}