package com.dicoding.movieapp.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.dicoding.movieapp.R
import com.dicoding.movieapp.ui.home.ActivityHome
@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {

    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()

        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, ActivityHome::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}