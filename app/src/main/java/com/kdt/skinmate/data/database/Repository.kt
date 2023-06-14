package com.kdt.skinmate.data.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.kdt.skinmate.data.LoginResponse
import com.kdt.skinmate.data.UserResponse
import com.kdt.skinmate.data.network.ApiInterceptor
import com.kdt.skinmate.data.network.ApiService
import com.kdt.skinmate.utils.AppExecutors
import com.kdt.skinmate.utils.SettingPreferences
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository private constructor(
    private val pref: SettingPreferences,
    private val apiService: ApiService,
    val appExecutors: AppExecutors
) {
    fun getToken(): LiveData<String> = pref.getUserToken().asLiveData()
    suspend fun setToken(value: String) = pref.setUserToken(value)

    fun getUserName(): LiveData<String> = pref.getUserName().asLiveData()
    suspend fun setUserName(value: String) = pref.setUserName(value)

    fun getUserEmail(): LiveData<String> = pref.getUserEmail().asLiveData()
    suspend fun setUserEmail(value: String) = pref.setUserEmail(value)

    suspend fun clearCache() = pref.clearCache()

    fun login(email: String, password: String): Call<LoginResponse> {
        return apiService.userLogin(email, password)
    }

    fun register(
        email: String,
        password: String,
        diplayName: String,
        description: String
    ): Call<UserResponse> {
        return apiService.userRegister(email, password, diplayName, description)
    }

    fun addScan(
        photo: MultipartBody.Part,
        description: RequestBody,
        tags: List<String>,
        token: String
    ): Call<UserResponse> {
        val client = OkHttpClient.Builder()
            .addInterceptor(ApiInterceptor(token))
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://skinmate-backend-api-vle27crhfa-et.a.run.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        val mApiService = retrofit.create(ApiService::class.java)
        return mApiService.postUserScan(photo, description, tags)
    }

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(
            pref: SettingPreferences,
            apiService: ApiService,
            appExecutors: AppExecutors
        ): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(pref, apiService, appExecutors)
            }.also { instance = it }
    }
}