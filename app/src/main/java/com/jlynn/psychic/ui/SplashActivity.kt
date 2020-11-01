package com.jlynn.psychic.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.jlynn.psychic.R
import com.jlynn.psychic.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        animateLogo { goAhead() }
    }

    private fun goAhead() {
        val token = SharedPrefManager.getInstance(this@SplashActivity)?.accessToken
        if (TextUtils.isEmpty(token)) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    private fun animateLogo(onDone: () -> Unit) {
        val animFadeIn: Animation = AnimationUtils.loadAnimation(this, R.anim.welcome_fade_in)
        animFadeIn.duration = 1000L
        val animFadeInScale: Animation = AnimationUtils.loadAnimation(this,
                R.anim.welcome_fade_in_scale
        )
        animFadeInScale.duration = 2000L

        ivLogo.startAnimation(animFadeIn)

        animFadeIn.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                ivLogo.startAnimation(animFadeInScale)
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }
        })

        animFadeInScale.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                onDone()
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }

        })

    }
}