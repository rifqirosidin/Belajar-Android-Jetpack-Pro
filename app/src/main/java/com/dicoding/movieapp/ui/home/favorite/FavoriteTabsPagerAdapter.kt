package com.dicoding.movieapp.ui.home.favorite

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dicoding.movieapp.R
import com.dicoding.movieapp.ui.home.favorite.movie.FavoriteMovieFragment
import com.dicoding.movieapp.ui.home.favorite.tvshow.FavoriteTvShowFragment

class FavoriteSectionPagerAdapter (private val mContext: Context, fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)  {

    companion object {
        @StringRes
        private val TAB_MENU = intArrayOf(R.string.movie, R.string.tv_show)
    }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> FavoriteMovieFragment()
            1 -> FavoriteTvShowFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence? =
        mContext.resources.getString(FavoriteSectionPagerAdapter.TAB_MENU[position])

    override fun getCount(): Int {
        return 2;
    }
}