package com.jlynn.psychic.ui

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.jlynn.psychic.R
import com.jlynn.psychic.api.ApiInterface
import com.jlynn.psychic.api.RetrofitInstance
import com.jlynn.psychic.api.model.GetAuthUserResponse
import com.jlynn.psychic.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun getTitleGravity(): Int {
        return RelativeLayout.ALIGN_PARENT_START
    }

    override fun isMenuVisible(): Boolean {
        return true
    }

    override fun getMenuDrawable(): Int {
        return R.drawable.ic_menu
    }

    override fun onMenuClicked() {
        startActivity(Intent(this, MenuActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getUserDetail()

    }


    private fun getUserDetail() {

        val token = SharedPrefManager.getInstance(this@HomeActivity)
            ?.accessToken

        val retrofitInstance =
            RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)

        retrofitInstance.getAuthenticatedUser("Bearer $token")
            .enqueue(object : Callback<GetAuthUserResponse> {
                override fun onFailure(call: Call<GetAuthUserResponse>, t: Throwable) {
                    Snackbar.make(
                        fields_container,
                        t.message.toString(),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }

                override fun onResponse(
                    call: Call<GetAuthUserResponse>,
                    response: Response<GetAuthUserResponse>
                ) {
                    if (response.code() == 200) {
                        Toast.makeText(this@HomeActivity, "Get Auth Info Successfully!", Toast.LENGTH_SHORT)
                            .show()

                        val getAuthResponse: GetAuthUserResponse? = response.body()

                        if (getAuthResponse != null) {
                            val userInfo = "ID : " + getAuthResponse.id +
                                    "\nType : " + getAuthResponse.type +
                                    "\nRegistration Date : " + getAuthResponse.registrationDate +
                                    "\nLast Login Date : " + getAuthResponse.lastLoginDate +
                                    "\nEmail : " + getAuthResponse.email +
                                    "\nUsername : " + getAuthResponse.username +
                                    "\nFirst Name : " + getAuthResponse.firstName +
                                    "\nLast Name : " + getAuthResponse.lastName +
                                    "\nGender : " + getAuthResponse.gender +
                                    "\nDate of Birth : " + getAuthResponse.dob +
                                    "\nCountry : " + getAuthResponse.country +
                                    "\nNewsletter : " + getAuthResponse.newsletter +
                                    "\nActive : " + getAuthResponse.active +
                                    "\nCreated At : " + getAuthResponse.createdAt +
                                    "\nUpdated At : " + getAuthResponse.updatedAt

                            val dlg: AlertDialog.Builder = AlertDialog.Builder(this@HomeActivity)
                            dlg.setMessage(userInfo)
                            dlg.setPositiveButton("OK", null)
                            dlg.setCancelable(false)
                            dlg.show()
                        }
                    }

                    Snackbar.make(
                        top,
                        "Failed to Get Authenticated User Info",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            })
    }
}
