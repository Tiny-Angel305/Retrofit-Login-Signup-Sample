package com.jlynn.psychic.api

import com.jlynn.psychic.api.model.GetAuthUserResponse
import com.jlynn.psychic.api.model.LoginRequest
import com.jlynn.psychic.api.model.LoginResponse
import com.jlynn.psychic.api.model.RegisterRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @Headers("Content-Type:application/json")
    @POST("login")
    fun login(
        @Body info: LoginRequest
    ): Call<LoginResponse>

    @Headers("Content-Type:application/json")
    @POST("register")
    fun registerUser(
        @Body info: RegisterRequest
    ): Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @GET("user")
    fun getAuthenticatedUser(
        @Header("Authorization") token: String
    ): Call<GetAuthUserResponse>
}