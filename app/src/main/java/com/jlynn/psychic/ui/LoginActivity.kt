package com.jlynn.psychic.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.jlynn.psychic.R
import com.jlynn.psychic.api.ApiInterface
import com.jlynn.psychic.api.RetrofitInstance
import com.jlynn.psychic.api.model.LoginRequest
import com.jlynn.psychic.api.model.LoginResponse
import com.jlynn.psychic.helper.NetworkHelper
import com.jlynn.psychic.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tv_sign_up.setOnClickListener(this)
        tv_forgot_password.setOnClickListener(this)

        btn_login.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val id: Int = p0!!.id
        when (id) {
            R.id.tv_sign_up -> {
                val intent = Intent(this, SignupActivity::class.java)
                startActivity(intent)
            }
            R.id.tv_forgot_password -> {
                val intent = Intent(this, ForgotPasswordActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_login -> {
                if (NetworkHelper().isNetworkAvailable(this)) run {

                    login(
                            username = input_username.text.toString(),
                            password = input_password.text.toString()
                    )
                } else {
                    Snackbar.make(
                            fields_container,
                            R.string.no_network_error,
                            Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun login(username: String, password: String) {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        retrofitInstance.login(LoginRequest(username, password)).enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Snackbar.make(
                        fields_container,
                        t.message.toString(),
                        Snackbar.LENGTH_SHORT
                ).show()
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.code() == 200) {
                    Toast.makeText(this@LoginActivity, "Login success!", Toast.LENGTH_SHORT).show()

                    val loginResponse: LoginResponse? = response.body()

                    if (loginResponse != null) {
                        SharedPrefManager.getInstance(this@LoginActivity)
                                ?.saveAccessToken(loginResponse.accessToken)

                        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        finish()
                    }
                }

                Snackbar.make(fields_container, "Login failed!", Snackbar.LENGTH_SHORT).show()
            }
        })
    }

}
