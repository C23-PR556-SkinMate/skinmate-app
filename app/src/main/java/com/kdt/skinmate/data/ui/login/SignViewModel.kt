package com.kdt.skinmate.data.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kdt.skinmate.data.LoginResponse
import com.kdt.skinmate.data.User
import com.kdt.skinmate.data.UserResponse
import com.kdt.skinmate.data.database.Repository
import com.kdt.skinmate.utils.Event
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignViewModel constructor (
    private val repository: Repository
    ) : ViewModel() {
    private var user_ = MutableLiveData<Event<User>>()
    val user: LiveData<Event<User>> = user_

    private var isLoading_ = MutableLiveData<Event<Boolean>>()
    val isLoading: LiveData<Event<Boolean>> = isLoading_

    private var message_ = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>> = message_

    private var error_ = MutableLiveData<Event<Boolean>>()
    val error: LiveData<Event<Boolean>> = error_

    fun login(email: String, password: String) {
        isLoading_.value = Event(true)
        val client = repository.login(email, password)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                isLoading_.value = Event(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()?.data!!
                    val responseUser = User(
                        userId = responseBody.uid,
                        token = responseBody.token
                    )
                    Log.d("ON RESPONSE LOGIN", "token: ${responseBody.toString()}")
                        error_.value = Event(false)
                        repository.appExecutors.networkIO.execute {
                            user_.postValue(Event(responseUser))
                        }
//                    if (response.body()?.loginResult != null){
//                        Log.d("ON RESPONSE LOGIN", "token: ${responseBody.toString()}")
//                        error_.value = Event(false)
//                        repository.appExecutors.networkIO.execute {
//                            user_.postValue(Event(responseBody!!))
//                        }
//                    } else {
//                        Log.d("ON RESPONSE LOGIN", "token is null")
//                    }
                } else {
                    message_.value = Event(response.message())
                    error_.value = Event(true)
                    Log.d("ON RESPONSE FAILED", "token is null")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                isLoading_.value = Event(false)
                message_.value = Event(t.message.toString())
                error_.value = Event(true)
            }
        })
    }

    fun register( email: String, password: String, displayName: String,description: String) {
        isLoading_.value = Event(true)
        val client = repository.register(email, password,displayName,description)
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                isLoading_.value = Event(false)
                if (response.isSuccessful){
                    /*TODO
                    *  Buat Snack Bar Ketika Sukses Login*/
                    error_.value = Event(false)
                } else {
                    message_.value = Event(response.message())
                    error_.value = Event(true)
                }

            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                isLoading_.value = Event(false)
                message_.value = Event(t.message.toString())
                error_.value = Event(true)
            }
        })
    }

    fun getUserToken() = repository.getToken()

    fun setUserToken(token: String?) {
        viewModelScope.launch {
            if (token != null) {
                repository.setToken(token)
            }
        }
    }

//    fun setUserName(name: String?) {
//        viewModelScope.launch {
//            if (name != null) {
//                repository.setUserName(name)
//            }
//        }
//    }

    fun setUserEmail(email: String) {
        viewModelScope.launch {
            repository.setUserEmail(email)
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.clearCache()
        }
    }
}
