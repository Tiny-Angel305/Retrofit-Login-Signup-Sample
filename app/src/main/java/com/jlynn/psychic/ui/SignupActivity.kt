package com.jlynn.psychic.ui

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.RelativeLayout
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.jlynn.psychic.R
import com.jlynn.psychic.api.ApiInterface
import com.jlynn.psychic.api.RetrofitInstance
import com.jlynn.psychic.api.model.RegisterRequest
import kotlinx.android.synthetic.main.activity_signup.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignupActivity : BaseActivity(), View.OnClickListener {

    lateinit var mGenderPopup: PopupWindow

    override fun getLayoutId(): Int {
        return R.layout.activity_signup
    }

    override fun getTitleGravity(): Int {
        return RelativeLayout.CENTER_IN_PARENT
    }

    override fun isMenuVisible(): Boolean {
        return true
    }

    override fun onMenuClicked() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater: LayoutInflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val customView: View = inflater.inflate(R.layout.gender_popup_layout, null)
        mGenderPopup = PopupWindow(
                customView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )

        mGenderPopup.isOutsideTouchable = true

        input_gender.isEnabled = false

        input_gender_container.setEndIconOnClickListener {
            if (mGenderPopup.isShowing)
                mGenderPopup.dismiss()
            else {

                mGenderPopup.showAsDropDown(
                        input_gender,
                        (-250f * resources.displayMetrics.density / 3.0f).toInt(),
                        (-30 * resources.displayMetrics.density / 3.0f).toInt(),
                        Gravity.END
                )
                /*tv_male.setOnClickListener {
                    input_gender.setText(tv_male.text)
                    mGenderPopup.dismiss()
                }
                tv_female.setOnClickListener {
                    input_gender.setText(tv_female.text)
                    mGenderPopup.dismiss()
                }*/
            }
        }

        input_gender_container.setEndIconTintMode(null)

        btn_register.setOnClickListener {
            signup(
                    email = input_email.text.toString(),
                    username = input_username.text.toString(),
                    passwd = input_password.text.toString(),
                    passwdConfirmation = input_retype_pass.text.toString(),
                    firstName = input_first_name.text.toString(),
                    lastName = input_last_name.text.toString(),
                    gender = input_gender.text.toString(),
                    dob = input_dob.text.toString(),
                    country = input_country.text.toString()
            )
        }
    }

    private fun signup(
            email: String,
            username: String,
            passwd: String,
            passwdConfirmation: String,
            firstName: String,
            lastName: String,
            gender: String,
            dob: String,
            country: String
    ) {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)

        retrofitInstance.registerUser(RegisterRequest(
                email,
                username,
                passwd,
                passwdConfirmation,
                firstName,
                lastName,
                gender,
                dob,
                country
        )).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Snackbar.make(
                        fields_container,
                        t.message.toString(),
                        Snackbar.LENGTH_SHORT
                ).show()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.code() == 201) {
                    Toast.makeText(this@SignupActivity, "Registration success! Please log in with your credential.", Toast.LENGTH_SHORT)
                            .show()

                    finish()
                } else {
                    Snackbar.make(fields_container, "Registration failed!", Snackbar.LENGTH_SHORT)
                            .show()
                }
            }
        })
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }

}
