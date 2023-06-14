package com.kdt.skinmate.data.ui.camera

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kdt.skinmate.data.UserResponse
import com.kdt.skinmate.data.database.Repository
import com.kdt.skinmate.utils.Event
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddScanViewModel (private val repository: Repository) : ViewModel() {
    private var error_ = MutableLiveData<Event<Boolean>>()
    val error: LiveData<Event<Boolean>> = error_
    private var message_ = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>> = message_
    private var isLoading_ = MutableLiveData<Event<Boolean>>()
    val isLoading: LiveData<Event<Boolean>> = isLoading_

    // Function to upload a scan
    fun uploadScan(
        photo: MultipartBody.Part,
        description: RequestBody,
        tags: List<String>,
        token: String
    ) {
        isLoading_.value = Event(true)
        val client = repository.addScan(photo, description, tags, token)
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                repository.appExecutors.networkIO.execute {
                    if (response.isSuccessful) {
                        error_.postValue(Event(false))
                        isLoading_.postValue(Event(false))
                    } else {
                        error_.postValue(Event(true))
                        message_.postValue(Event(response.message()))
                        isLoading_.postValue(Event(false))
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                error_.value = Event(true)
                message_.value = Event(t.message.toString())
            }
        })
    }
}
