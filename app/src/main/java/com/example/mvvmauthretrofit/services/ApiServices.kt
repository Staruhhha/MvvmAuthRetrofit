package com.example.mvvmauthretrofit.services

import com.example.mvvmauthretrofit.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {

    @GET("user/searcg?user_Login={userLogin}")
    fun getUserByEmail(@Path("user_Login") user_Login : String) : Call<User>


}