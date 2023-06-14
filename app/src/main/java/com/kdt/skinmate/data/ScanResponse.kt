package com.kdt.skinmate.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScanResponse(
    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("loginResult")
    val loginResult: User? = null,

    @field:SerializedName("listStory")
    val listScan: ArrayList<ScanModel>? = null,
) : Parcelable

@Entity(tableName = "scanTable")
@Parcelize
data class ScanModel(
    @PrimaryKey
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("photoUrl")
    val image: String? = null,

    @field:SerializedName("description")
    val description: String? = null,
): Parcelable