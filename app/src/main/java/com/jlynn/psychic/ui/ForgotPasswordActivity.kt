package com.jlynn.psychic.ui

import android.widget.RelativeLayout
import com.jlynn.psychic.R

class ForgotPasswordActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_forgot_password
    }

    override fun getTitleGravity(): Int {
        return RelativeLayout.CENTER_IN_PARENT
    }

    override fun isMenuVisible(): Boolean {
        return false
    }

    override fun onMenuClicked() {

    }
}
