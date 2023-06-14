package com.kdt.skinmate.data.network

import com.kdt.skinmate.data.LoginResponse
import com.kdt.skinmate.data.UserResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
        @FormUrlEncoded
        @POST("api/register")
        fun userRegister(
            @Field("email") email: String,
            @Field("password") password: String,
            @Field("displayName") displayName: String,
            @Field("description") description: String
        ): Call<UserResponse>

        @FormUrlEncoded
        @POST("api/login")
        fun userLogin(
            @Field("email") email: String,
            @Field("password") password: String
        ): Call<LoginResponse>

//        @GET("api/profile/{uid}")
//        fun getProfile(
//            @Path("uid") uid: String,
//            @Header("Authorization") token: String
//        ): Call<UserProfile>

//        @GET("api/products")
//        fun getProducts(): Call<List<Product>>

//        @GET("api/products/byTag")
//        fun getProductsByTag(
//            @Header("Authorization") token: String,
//            @Query("tag") tag: String
//        ): Call<List<Product>>

//        @PUT("api/profile/{uid}")
//        fun updateProfile(
//            @Header("Authorization") token: String,
//            @Query("name") name: String,
//            @Query("gender") gender: String,
//            @Query("birthdate") birthdate: String,
//            @Query("skintype") skintype: String
//        ): Call<UpdateProfileResponse>

        @Multipart
        @POST("scan")
        fun postUserScan(
            @Part photo: MultipartBody.Part,
            @Part("description") description: RequestBody,
            @Part("tags") tags: List<String>
        ): Call<UserResponse>


//        @POST("api/profile/{uid}/upload")
//        fun uploadProfilePhoto(
//            @Header("Authorization") token: String,
//            @Path("uid") uid: String
//        ): Call<UploadPhotoResponse>
}