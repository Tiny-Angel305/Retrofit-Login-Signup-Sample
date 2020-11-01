package com.jlynn.psychic.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginRequest internal constructor(

    @Expose
    @SerializedName("username") internal val username: String,

    @Expose
    @SerializedName("password") internal val password: String
)

