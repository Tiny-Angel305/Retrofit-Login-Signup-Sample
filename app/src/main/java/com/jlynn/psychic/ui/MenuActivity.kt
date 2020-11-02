package com.jlynn.psychic.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.text.HtmlCompat
import com.jlynn.psychic.R
import com.jlynn.psychic.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_menu.*


class MenuActivity : BaseActivity(), View.OnClickListener {

    override fun getLayoutId(): Int {
        return R.layout.activity_menu
    }

    override fun getTitleGravity(): Int {
        return 0
    }

    override fun isMenuVisible(): Boolean {
        return true
    }

    override fun onMenuClicked() {
        finish()
    }

    override fun getMenuDrawable(): Int {
        return R.drawable.ic_close
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val suggestionText = "<b>" + "Questions about Love,\nRelations, Money?" + "</b>" +
                "\nCall our Psychics " + "<b>" + "24/7 1-888-573-9239" + "</b>" +
                "\n1st time callers only " + "<b>" + "99¢" + "</b>" + " minute!"

        tv_contact_suggestion.text =
            HtmlCompat.fromHtml(suggestionText, HtmlCompat.FROM_HTML_MODE_COMPACT)

        menu_btn_home.setOnClickListener(this)
        menu_btn_register.setOnClickListener(this)
        menu_btn_logout.setOnClickListener(this)

        iv_facebook.setOnClickListener(this)
        iv_twitter.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val id: Int = p0!!.id
        when (id) {
            R.id.menu_btn_home -> {
                finish()
            }
            R.id.menu_btn_register -> {
                val intent = Intent(this, SignupActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.menu_btn_logout -> run {
                SharedPrefManager.getInstance(this@MenuActivity)
                    ?.clear()
                val intent = Intent(this, SplashActivity::class.java)
                startActivity(intent)
                finishAffinity()
            }
            R.id.iv_facebook -> {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/Psychic.Contact")
                )
                startActivity(browserIntent)
            }
            R.id.iv_twitter -> {
                val browserIntent =
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/Psychic_Contact"))
                startActivity(browserIntent)
            }
        }
    }

}
