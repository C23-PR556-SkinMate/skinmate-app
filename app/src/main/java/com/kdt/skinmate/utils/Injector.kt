package com.kdt.skinmate.utils

import android.content.Context
import com.kdt.skinmate.data.database.Repository
import com.kdt.skinmate.data.network.ApiConfig
import com.kdt.skinmate.data.ui.dataStore


object Injector {

    fun provideUserRepository(context: Context): Repository {
        val appExecutors = AppExecutors()
        val pref = SettingPreferences.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService()


        return Repository.getInstance(pref,apiService,appExecutors)

    }
}