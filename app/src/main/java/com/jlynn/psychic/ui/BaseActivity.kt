package com.jlynn.psychic.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.jlynn.psychic.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.tool_bar_layout.*

abstract class BaseActivity : AppCompatActivity() {

    protected abstract fun getLayoutId(): Int

    protected abstract fun getTitleGravity(): Int

    protected abstract fun isMenuVisible(): Boolean

    protected abstract fun getMenuDrawable(): Int

    protected abstract fun onMenuClicked()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        if (toolbar_title != null) {

            if (getTitleGravity() != 0) {
                val lp: RelativeLayout.LayoutParams =
                    toolbar_title.layoutParams as RelativeLayout.LayoutParams
                lp.addRule(getTitleGravity())
                toolbar_title.layoutParams = lp
            }

            if (TextUtils.isEmpty(title))
                toolbar_title.setText(R.string.app_name)
            else
                toolbar_title.text = title

            if (isMenuVisible()) {
                iv_right.visibility = View.VISIBLE
                iv_right.setImageResource(getMenuDrawable())
                iv_right.setOnClickListener { onMenuClicked() }
            }
        }
    }

    protected fun showProgressBar() {
        if (pb_bar == null)
            return

        pb_bar.visibility = View.VISIBLE
    }

    protected fun hideProgressBar() {
        if (pb_bar == null)
            return

        pb_bar.visibility = View.GONE
    }

}