package com.kdt.skinmate.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserResponse(
    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("loginResult")
    val loginResult: User? = null,

    @field:SerializedName("listScan")
    val listScan: ArrayList<ScanModel>? = null,

) : Parcelable

@Parcelize
data class User(
    @field:SerializedName("userId")
    val userId: String? = null,

    @field:SerializedName("token")
    val token: String? = null,

) : Parcelable