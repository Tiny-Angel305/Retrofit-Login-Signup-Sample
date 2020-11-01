package com.jlynn.psychic.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(
        @Expose
        @SerializedName("access_token") internal val accessToken: String,

        @Expose
        @SerializedName("token_type") internal val tokenType: String,

        @Expose
        @SerializedName("expires_at") internal val expiresAt: String
)
