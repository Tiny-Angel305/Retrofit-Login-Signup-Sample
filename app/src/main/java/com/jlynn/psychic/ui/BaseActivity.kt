package com.jlynn.psychic.ui

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import com.jlynn.psychic.R
import kotlinx.android.synthetic.main.tool_bar_layout.*

abstract class BaseActivity : AppCompatActivity() {

    protected var progressDialog: ProgressDialog? = null

    protected abstract fun getLayoutId(): Int

    protected abstract fun getTitleGravity(): Int

    abstract fun isMenuVisible(): Boolean

    protected abstract fun onMenuClicked()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        val lp: RelativeLayout.LayoutParams =
            toolbar_title.layoutParams as RelativeLayout.LayoutParams
        lp.addRule(getTitleGravity())
        toolbar_title.layoutParams = lp

//        setSupportActionBar(tool_bar)

        if (TextUtils.isEmpty(title))
            toolbar_title.setText(R.string.app_name)
        else
            toolbar_title.text = title

//        supportActionBar!!.setDisplayShowTitleEnabled(false)

        if (isMenuVisible()) {
            iv_right.visibility = View.VISIBLE
            iv_right.setOnClickListener { onMenuClicked() }
        }
    }

    protected fun hideProgress() {
        progressDialog?.let { if (it.isShowing) it.dismiss() }
    }

    protected fun showProgress() {
        hideProgress()
        progressDialog = showLoadingDialog(this)
    }

    private fun showLoadingDialog(context: Context?): ProgressDialog {
        val progressDialog = ProgressDialog(context)
        progressDialog.let {
            it.show()
            it.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
            it.setContentView(R.layout.progress_dialog_layout)
            it.isIndeterminate = true
            it.setCancelable(false)
            it.setCanceledOnTouchOutside(false)
            return it
        }
    }
}