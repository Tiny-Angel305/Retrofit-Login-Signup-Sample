package com.jlynn.psychic.ui

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import com.jlynn.psychic.R
import kotlinx.android.synthetic.main.tool_bar_layout.*

abstract class BaseActivity : AppCompatActivity() {

    protected var progressDialog: ProgressDialog? = null

    protected abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        setSupportActionBar(tool_bar)

        if (TextUtils.isEmpty(title))
            toolbar_title.setText(R.string.app_name)
        else
            toolbar_title.text = title

//        supportActionBar!!.setDisplayShowTitleEnabled(false)

        iv_left?.setOnClickListener { v: View? -> onBackPressed() }

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