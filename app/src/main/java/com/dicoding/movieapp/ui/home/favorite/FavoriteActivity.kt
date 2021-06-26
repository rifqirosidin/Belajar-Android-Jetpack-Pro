package com.dicoding.movieapp.ui.home.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.movieapp.R
import com.dicoding.movieapp.databinding.ActivityFavBinding
import com.dicoding.movieapp.databinding.ActivityFavoriteBinding

class FavActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fav)

        val activityFavoriteBinding = ActivityFavBinding.inflate(layoutInflater)
        setContentView(activityFavoriteBinding.root)

        val favoriteTabsPagerAdapter = FavoriteTabsPagerAdapter(this, supportFragmentManager)
        activityFavoriteBinding.viewPagerFavorite.adapter = favoriteTabsPagerAdapter
        activityFavoriteBinding.tabsFavorite.setupWithViewPager(activityFavoriteBinding.viewPagerFavorite)

        supportActionBar?.elevation = 0f
    }
}