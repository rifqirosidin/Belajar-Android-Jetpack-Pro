package com.dicoding.movieapp.ui.home.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.movieapp.R
import com.dicoding.movieapp.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        val activityFavoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(activityFavoriteBinding.root)

        val favoriteTabsPagerAdapter = FavoriteTabsPagerAdapter(this, supportFragmentManager)
        activityFavoriteBinding.viewPagerFav.adapter = favoriteTabsPagerAdapter
        activityFavoriteBinding.tabsFav.setupWithViewPager(activityFavoriteBinding.viewPagerFav)

        supportActionBar?.elevation = 0f
    }
}