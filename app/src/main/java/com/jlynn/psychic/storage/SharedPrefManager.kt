package com.jlynn.psychic.storage

import android.content.Context
import com.jlynn.psychic.AppConstants.SHARED_PREF_NAME

class SharedPrefManager private constructor(private val mCtx: Context) {
    fun saveAccessToken(token: String) {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(PREF_KEY_ACCESS_TOKEN, token)
        editor.apply()
    }

    val accessToken: String?
        get() {
            val sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, "")
        }

    fun clear() {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {

        private var mInstance: SharedPrefManager? = null

        private const val PREF_KEY_ACCESS_TOKEN: String = "token"

        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager? {
            if (mInstance == null) {
                mInstance = SharedPrefManager(mCtx)
            }
            return mInstance
        }
    }
}