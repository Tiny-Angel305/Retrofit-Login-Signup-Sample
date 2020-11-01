package com.jlynn.psychic.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GetAuthUserResponse(

    @Expose
    @SerializedName("id") internal val id: Int,
    @Expose
    @SerializedName("type") internal val type: String,
    @Expose
    @SerializedName("registration_date") internal val registrationDate: String,
    @Expose
    @SerializedName("last_login_date") internal val lastLoginDate: String,
    @Expose
    @SerializedName("email") internal val email: String,
    @Expose
    @SerializedName("username") internal val username: String,
    @Expose
    @SerializedName("password") internal val password: String,
    @Expose
    @SerializedName("first_name") internal val firstName: String,
    @Expose
    @SerializedName("last_name") internal val lastName: String,
    @Expose
    @SerializedName("gender") internal val gender: String,
    @Expose
    @SerializedName("dob") internal val dob: String,
    @Expose
    @SerializedName("country") internal val country: String,
    @Expose
    @SerializedName("profile_image") internal val profileImage: String,
    @Expose
    @SerializedName("newsletter") internal val newsletter: Int,
    @Expose
    @SerializedName("validated") internal val validated: Int,
    @Expose
    @SerializedName("received_promo") internal val receivedPromo: Int,
    @Expose
    @SerializedName("banned") internal val banned: String,
    @Expose
    @SerializedName("paypal_email") internal val paypalEmail: String,
    @Expose
    @SerializedName("active") internal val active: Int,
    @Expose
    @SerializedName("chat_notify_sound") internal val chatNotifySound: String,
    @Expose
    @SerializedName("deleted") internal val deleted: Int,
    @Expose
    @SerializedName("chat_try_sound") internal val chatTrySound: String,
    @Expose
    @SerializedName("annonymous") internal val annonymous: Int,
    @Expose
    @SerializedName("font_color") internal val fontColor: String,
    @Expose
    @SerializedName("font_size") internal val fontSize: Int,
    @Expose
    @SerializedName("pause_timer") internal val pauseTimer: Int,
    @Expose
    @SerializedName("pause_timer_size") internal val pauseTimerSize: Int,
    @Expose
    @SerializedName("pause_timer_flag") internal val pauseTimerFlag: Int,
    @Expose
    @SerializedName("repeat_client") internal val repeatClient: String,
    @Expose
    @SerializedName("week_day") internal val weekDay: Int,
    @Expose
    @SerializedName("giveback_created_at") internal val givebackCreatedAt: String,
    @Expose
    @SerializedName("member_fund_type") internal val memberFundType: String,
    @Expose
    @SerializedName("fund_amount_day") internal val fundAmountDay: String,
    @Expose
    @SerializedName("fund_amount_month") internal val fundAmountMonth: String,
    @Expose
    @SerializedName("auto_updated") internal val autoUpdated: Int,
    @Expose
    @SerializedName("created_at") internal val createdAt: String,
    @Expose
    @SerializedName("updated_at") internal val updatedAt: String
)
