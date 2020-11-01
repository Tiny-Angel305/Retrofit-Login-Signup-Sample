package com.jlynn.psychic.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class RegisterRequest internal constructor(
    @Expose
    @SerializedName("email") internal val email: String,

    @Expose
    @SerializedName("username") internal val username: String,

    @Expose
    @SerializedName("password") internal val password: String,

    @Expose
    @SerializedName("password_confirmation") internal val passwordConfirmation: String,

    @Expose
    @SerializedName("first_name") internal val firstName: String,

    @Expose
    @SerializedName("last_name") internal val lastName: String,

    @Expose
    @SerializedName("gender") internal val gender: String,

    @Expose
    @SerializedName("dob") internal val dob: String,

    @Expose
    @SerializedName("country") internal val country: String
)