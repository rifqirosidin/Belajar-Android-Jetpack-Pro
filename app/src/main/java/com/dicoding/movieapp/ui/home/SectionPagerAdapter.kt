package com.dicoding.movieapp.ui.home

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dicoding.movieapp.R
import com.dicoding.movieapp.ui.home.fragment.movie.MovieFragment
import com.dicoding.movieapp.ui.home.fragment.tv_show.TvShowFragment

class SectionPagerAdapter(private val mContext: Context, fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_MENU = intArrayOf(R.string.movie, R.string.tv_show)
    }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> MovieFragment()
            1 -> TvShowFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence? = mContext.resources.getString(TAB_MENU[position])

    override fun getCount(): Int {
        return 2
    }
}